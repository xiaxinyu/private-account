package com.account.web.rest;

import com.account.persist.model.ConsumeRule;
import com.account.service.consume.ClassificationService;
import com.account.service.consume.ConsumeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consume/rules")
public class ConsumeRuleController {
    @Autowired
    private ConsumeRuleService ruleService;
    @Autowired
    private ClassificationService classificationService;

    @GetMapping
    public List<ConsumeRule> list(){
        return ruleService.listActive();
    }

    @PostMapping
    public ConsumeRule add(@RequestBody ConsumeRule rule){
        rule.setId(com.account.core.tool.StringTool.generateID());
        ruleService.save(rule);
        return rule;
    }

    @PutMapping("/{id}")
    public ConsumeRule update(@PathVariable("id") String id, @RequestBody ConsumeRule rule){
        rule.setId(id);
        ruleService.updateById(rule);
        return rule;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        ruleService.removeById(id);
    }

    @PostMapping("/reload")
    public String reload(){
        classificationService.reload();
        return "ok";
    }
}
