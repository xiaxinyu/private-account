package com.account.web.rest;

import com.account.core.tool.StringTool;
import com.account.persist.model.ConsumeCategory;
import com.account.service.consume.ConsumeCategoryService;
import com.account.web.rest.model.CollectionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consume/categories")
public class ConsumeCategoryAdminController {
    @Autowired
    private ConsumeCategoryService categoryService;

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
        if (cat.getId() == null || cat.getId().trim().isEmpty()){
            cat.setId(StringTool.generateID());
        }
        if (cat.getParentId() == null || cat.getParentId().trim().isEmpty()){
            cat.setLevel(1);
        } else {
            cat.setLevel(2);
        }
        categoryService.save(cat);
        return cat;
    }

    @PutMapping("/{id}")
    public ConsumeCategory update(@PathVariable("id") String id, @RequestBody ConsumeCategory cat){
        cat.setId(id);
        if (cat.getParentId() == null || cat.getParentId().trim().isEmpty()){
            cat.setLevel(1);
        } else {
            cat.setLevel(2);
        }
        categoryService.updateById(cat);
        return cat;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        categoryService.removeById(id);
    }
}
