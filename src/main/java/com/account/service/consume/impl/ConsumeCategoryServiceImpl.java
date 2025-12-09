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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ConsumeCategoryServiceImpl extends ServiceImpl<ConsumeCategoryMapper, ConsumeCategory> implements ConsumeCategoryService {
    @Override
    public List<ConsumeCategory> listAll() {
        LambdaQueryWrapper<ConsumeCategory> qw = Wrappers.lambdaQuery();
        qw.eq(ConsumeCategory::getDeleted, 0).orderByAsc(ConsumeCategory::getLevel).orderByAsc(ConsumeCategory::getSortNo);
        return super.list(qw);
    }

    @Override
    public List<TreeNode> tree() {
        List<ConsumeCategory> list = listAll();
        Map<String, List<ConsumeCategory>> byParent = list.stream().collect(Collectors.groupingBy(c -> c.getParentId() == null ? "" : c.getParentId()));
        List<ConsumeCategory> roots = byParent.getOrDefault("", new ArrayList<>());
        List<TreeNode> result = new ArrayList<>();
        for (ConsumeCategory r : roots) {
            result.add(build(r, byParent));
        }
        return result;
    }

    private TreeNode build(ConsumeCategory cat, Map<String, List<ConsumeCategory>> byParent){
        TreeNode n = new TreeNode();
        n.setId(cat.getId());
        n.setText(cat.getName());
        List<ConsumeCategory> children = byParent.getOrDefault(cat.getId(), new ArrayList<>());
        if (!children.isEmpty()){
            List<TreeNode> cs = new ArrayList<>();
            for (ConsumeCategory c : children){
                cs.add(build(c, byParent));
            }
            n.setChildren(cs);
        }
        return n;
    }
}
