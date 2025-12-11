package com.account.service.consume.impl;

import com.account.persist.mapper.ConsumeCategoryMapper;
import com.account.persist.model.ConsumeCategory;
import com.account.service.consume.ConsumeCategoryService;
import com.account.web.rest.model.TreeNode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ConsumeCategoryServiceImpl extends ServiceImpl<ConsumeCategoryMapper, ConsumeCategory> implements ConsumeCategoryService {
    @Override
    public List<ConsumeCategory> listAll() {
        LambdaQueryWrapper<ConsumeCategory> qw = Wrappers.lambdaQuery();
        qw.ne(ConsumeCategory::getDeleted, 1)
          .orderByAsc(ConsumeCategory::getLevel)
          .orderByAsc(ConsumeCategory::getSortNo);
        return super.list(qw);
    }

    @Override
    public List<TreeNode> tree() {
        List<ConsumeCategory> list = listAll();
        List<ConsumeCategory> roots = list.stream()
                .filter(c -> {
                    Integer lv = c.getLevel();
                    String p = c.getParentId();
                    return (lv != null && lv == 1) || (p == null || p.trim().isEmpty());
                })
                .collect(Collectors.toList());
        Map<String, List<ConsumeCategory>> byParent = list.stream()
                .filter(c -> c.getLevel() != null && c.getLevel() == 2)
                .collect(Collectors.groupingBy(c -> {
                    String p = c.getParentId();
                    return p == null ? "" : p.trim();
                }));
        List<TreeNode> result = new ArrayList<>();
        for (ConsumeCategory r : roots) {
            result.add(build(r, byParent));
        }
        return result;
    }

    private TreeNode build(ConsumeCategory cat, Map<String, List<ConsumeCategory>> byParent){
        TreeNode n = new TreeNode();
        n.setId(cat.getCode());
        String txt = cat.getName();
        if (txt == null || txt.trim().isEmpty()) { txt = cat.getCode(); }
        n.setText(txt);
        String parentKey = cat.getCode() == null ? "" : cat.getCode().trim();
        List<ConsumeCategory> children = new ArrayList<>(byParent.getOrDefault(parentKey, Collections.emptyList()));
        children.sort((a, b) -> {
            Integer sa = a.getSortNo();
            Integer sb = b.getSortNo();
            if (sa == null && sb == null) return 0;
            if (sa == null) return 1;
            if (sb == null) return -1;
            return Integer.compare(sa, sb);
        });
        if (!children.isEmpty()){
            List<TreeNode> cs = new ArrayList<>();
            java.util.Set<String> seen = new java.util.LinkedHashSet<>();
            for (ConsumeCategory c : children){
                if (c == null) continue;
                if (c.getLevel() != null && c.getLevel() != 2) continue; // enforce two levels
                if (cat.getCode() != null && cat.getCode().equals(c.getCode())) continue; // avoid self-loop by duplicate code
                String cid = c.getCode() == null ? null : c.getCode().trim();
                if (cid == null || seen.contains(cid)) continue;
                seen.add(cid);
                TreeNode cn = new TreeNode();
                cn.setId(cid);
                String ct = c.getName();
                if (ct == null || ct.trim().isEmpty()) { ct = cid; }
                cn.setText(ct);
                cs.add(cn);
            }
            if (!cs.isEmpty()){
                n.setChildren(cs);
                n.setState("closed");
            }
        }
        return n;
    }
}
