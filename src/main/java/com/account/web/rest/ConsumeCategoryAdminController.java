package com.account.web.rest;

import com.account.core.tool.StringTool;
import com.account.persist.model.ConsumeCategory;
import com.account.persist.model.Credit;
import com.account.persist.mapper.CreditMapper;
import com.account.service.consume.ConsumeCategoryService;
import com.account.web.rest.model.CollectionResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consume/categories")
@Slf4j
public class ConsumeCategoryAdminController {
    @Autowired
    private ConsumeCategoryService categoryService;
    @Autowired
    private CreditMapper creditMapper;

    @GetMapping
    public CollectionResult<ConsumeCategory> list(){
        List<ConsumeCategory> list = categoryService.listAll();
        CollectionResult<ConsumeCategory> r = new CollectionResult<>();
        r.setRows(list);
        r.setTotal(list.size());
        return r;
    }

    @PostMapping
    public ConsumeCategory add(@RequestBody ConsumeCategory cat){
        if (cat.getParentId() == null || cat.getParentId().trim().isEmpty()){
            cat.setLevel(1);
        } else {
            cat.setLevel(2);
        }
        autofillCodeAndSort(cat);
        validateUniqueCode(cat.getCode(), null);
        String genId = buildId(cat.getLevel(), cat.getCode(), cat.getParentId());
        cat.setId(genId);
        categoryService.save(cat);
        return cat;
    }

    @PutMapping("/{id}")
    public ConsumeCategory update(@PathVariable("id") String id,
                                  @RequestBody ConsumeCategory cat,
                                  @RequestParam(value = "cascade", required = false) Boolean cascade){
        ConsumeCategory old = categoryService.getById(id);
        if (cat.getParentId() == null || cat.getParentId().trim().isEmpty()){
            cat.setLevel(1);
        } else {
            cat.setLevel(2);
        }
        autofillCodeAndSort(cat);
        validateUniqueCode(cat.getCode(), id);
        String newId = buildId(cat.getLevel(), cat.getCode(), cat.getParentId());
        // Update current category, allowing primary key change
        com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper<ConsumeCategory> uwCat = Wrappers.lambdaUpdate();
        uwCat.eq(ConsumeCategory::getId, id);
        uwCat.set(ConsumeCategory::getId, newId)
             .set(ConsumeCategory::getCode, cat.getCode())
             .set(ConsumeCategory::getName, cat.getName())
             .set(ConsumeCategory::getLevel, cat.getLevel())
             .set(ConsumeCategory::getParentId, cat.getParentId())
             .set(ConsumeCategory::getSortNo, cat.getSortNo())
             .set(ConsumeCategory::getDeleted, cat.getDeleted());
        categoryService.update(null, uwCat);

        // If parent changed its code, cascade children: update parentId and adjust child code/id if prefixed
        if (old != null && old.getLevel() != null && old.getLevel() == 1) {
            String oldCode = old.getCode();
            String newParentCode = cat.getCode();
            if (oldCode != null && newParentCode != null && !oldCode.equals(newParentCode)){
                LambdaQueryWrapper<ConsumeCategory> qChildren = Wrappers.lambdaQuery();
                qChildren.eq(ConsumeCategory::getParentId, oldCode);
                java.util.List<ConsumeCategory> children = categoryService.list(qChildren);
                int affectedChildren = 0;
                for(ConsumeCategory ch : children){
                    String oldChildId = ch.getId();
                    String childCode = ch.getCode();
                    String updatedChildCode = childCode;
                    if (childCode != null && childCode.startsWith(oldCode + "-")){
                        updatedChildCode = newParentCode + childCode.substring(oldCode.length());
                    }
                    String newChildId = buildId(2, updatedChildCode, newParentCode);
                    LambdaUpdateWrapper<ConsumeCategory> uwChild = Wrappers.lambdaUpdate();
                    uwChild.eq(ConsumeCategory::getId, ch.getId())
                          .set(ConsumeCategory::getId, newChildId)
                          .set(ConsumeCategory::getCode, updatedChildCode)
                          .set(ConsumeCategory::getParentId, newParentCode)
                          .set(ConsumeCategory::getLevel, 2);
                    boolean ok = categoryService.update(null, uwChild);
                    if (ok) affectedChildren++;

                    if (Boolean.TRUE.equals(cascade)){
                        LambdaUpdateWrapper<Credit> uwChildCredit = Wrappers.lambdaUpdate();
                        uwChildCredit.set(Credit::getConsumeCode, updatedChildCode)
                                     .set(Credit::getConsumeName, ch.getName())
                                     .eq(Credit::getConsumeID, oldChildId);
                        creditMapper.update(null, uwChildCredit);
                    }
                }
                log.info("Cascade update child categories: parentCode {} -> {}, affectedChildren={} (child codes adjusted if prefixed)", oldCode, newParentCode, affectedChildren);
            }
        }
        if (Boolean.TRUE.equals(cascade) && old != null){
            String oldCode = old.getCode();
            String newCode = cat.getCode();
            String newName = cat.getName();
            if (oldCode != null && newCode != null){
                // 1) Update credits linked by previous category identifier (path variable id)
                LambdaUpdateWrapper<Credit> uwById = Wrappers.lambdaUpdate();
                uwById.set(Credit::getConsumeCode, newCode)
                      .set(Credit::getConsumeName, newName)
                      .eq(Credit::getConsumeID, id);
                int affectedById = creditMapper.update(null, uwById);

                // 2) Update credits linked by old code
                LambdaUpdateWrapper<Credit> uw = Wrappers.lambdaUpdate();
                uw.set(Credit::getConsumeID, newCode)
                  .set(Credit::getConsumeCode, newCode)
                  .set(Credit::getConsumeName, newName)
                  .eq(Credit::getConsumeID, oldCode);
                int affectedByCode = creditMapper.update(null, uw);

                // 3) Update credits where consume_code equals old code (defensive)
                LambdaUpdateWrapper<Credit> uwByCodeCol = Wrappers.lambdaUpdate();
                uwByCodeCol.set(Credit::getConsumeCode, newCode)
                           .set(Credit::getConsumeName, newName)
                           .eq(Credit::getConsumeCode, oldCode);
                int affectedByCodeCol = creditMapper.update(null, uwByCodeCol);

                log.info("Cascade update credits: id={}, oldCode {} -> newCode {}, affectedById={}, affectedByConsumeId={}, affectedByConsumeCodeCol={}",
                        id, oldCode, newCode, affectedById, affectedByCode, affectedByCodeCol);
            }
            // Global sync after cascade to guarantee credit.consume_code aligns with category.code
            syncAllCreditConsumeCodes();
        }
        cat.setId(newId);
        return cat;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        ConsumeCategory cat = categoryService.getById(id);
        long children = 0;
        if (id != null && !id.trim().isEmpty()){
            LambdaQueryWrapper<ConsumeCategory> qw = Wrappers.lambdaQuery();
            qw.eq(ConsumeCategory::getParentId, cat == null ? null : cat.getCode());
            children = categoryService.count(qw);
        }
        log.info("Delete category id={}, name={}, code={}, children={}",
                cat == null ? id : cat.getId(),
                cat == null ? null : cat.getName(),
                cat == null ? null : cat.getCode(),
                children);
        categoryService.removeById(id);
        if (cat != null && cat.getCode() != null){
            LambdaUpdateWrapper<Credit> uw = Wrappers.lambdaUpdate();
            uw.set(Credit::getConsumeID, (String) null)
              .set(Credit::getConsumeCode, (String) null)
              .set(Credit::getConsumeName, (String) null)
              .eq(Credit::getConsumeID, cat.getCode());
            int affected = creditMapper.update(null, uw);
            log.info("Cascade clear credits after category delete: code {}. affectedRows={}", cat.getCode(), affected);
        }
    }

    private String buildId(Integer level, String code, String parentId){
        String c = (code == null) ? "" : code.trim();
        return c;
    }

    private void validateUniqueCode(String code, String currentId){
        String c = (code == null) ? "" : code.trim();
        if (c.isEmpty()) { return; }
        LambdaQueryWrapper<ConsumeCategory> qw = Wrappers.lambdaQuery();
        qw.eq(ConsumeCategory::getCode, c);
        if (currentId != null && !currentId.trim().isEmpty()){
            qw.ne(ConsumeCategory::getId, currentId.trim());
        }
        long cnt = categoryService.count(qw);
        if (cnt > 0){
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Duplicate code: " + c + ", please choose another code.");
        }
    }

    private void syncAllCreditConsumeCodes(){
        try{
            List<ConsumeCategory> all = categoryService.listAll();
            int total = 0;
            for(ConsumeCategory cc : all){
                if (cc == null || cc.getId() == null || cc.getCode() == null) continue;
                LambdaUpdateWrapper<Credit> uw = Wrappers.lambdaUpdate();
                uw.set(Credit::getConsumeCode, cc.getCode())
                  .eq(Credit::getConsumeID, cc.getId());
                total += creditMapper.update(null, uw);
            }
            log.info("Sync all credits consume_code done. affectedRows={}", total);
        }catch(Exception e){
            log.warn("Sync all credits consume_code failed: {}", e.getMessage());
        }
    }

    private void autofillCodeAndSort(ConsumeCategory cat){
        String parentCode = (cat.getParentId()==null) ? "" : cat.getParentId().trim();
        String code = (cat.getCode()==null) ? "" : cat.getCode().trim();
        Integer sortNo = cat.getSortNo();
        // Compute next sort under same parent
        LambdaQueryWrapper<ConsumeCategory> qwSiblings = Wrappers.lambdaQuery();
        qwSiblings.eq(ConsumeCategory::getParentId, parentCode);
        java.util.List<ConsumeCategory> siblings = categoryService.list(qwSiblings);
        int nextSort = 1;
        for(ConsumeCategory s : siblings){
            if (s.getSortNo()!=null) nextSort = Math.max(nextSort, s.getSortNo()+1);
        }
        if (sortNo == null || sortNo <= 0){ cat.setSortNo(nextSort); }
        // Generate code if missing
        if (code.isEmpty()){
            String basePrefix;
            if (parentCode.isEmpty()){
                // root: derive from name or default 'CAT'
                String name = (cat.getName()==null)?"":cat.getName().trim();
                basePrefix = name.isEmpty()?"CAT":name.toUpperCase().replaceAll("\\s+","_");
            } else {
                basePrefix = parentCode;
            }
            int next = 1;
            for(ConsumeCategory s : siblings){
                String sc = s.getCode();
                if (sc==null) continue;
                if (sc.equals(basePrefix)){
                    next = Math.max(next, 2); // ensure suffix starts at 02 if plain base exists
                }
                if (sc.startsWith(basePrefix+"-")){
                    String suf = sc.substring((basePrefix+"-").length());
                    try{ int n = Integer.parseInt(suf); next = Math.max(next, n+1);}catch(Exception ignore){}
                }
            }
            String generated = basePrefix + (parentCode.isEmpty()?"":"-") + String.format("%02d", next);
            // For roots, we included hyphen; if you prefer plain base for root, set without hyphen
            if (parentCode.isEmpty()){
                generated = basePrefix + "-" + String.format("%02d", next);
            }
            cat.setCode(generated);
        }
    }
}
