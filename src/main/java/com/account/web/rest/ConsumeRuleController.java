package com.account.web.rest;

import com.account.persist.model.ConsumeRule;
import com.account.service.consume.ClassificationService;
import com.account.service.consume.ConsumeRuleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consume/rules")
@Slf4j
public class ConsumeRuleController {
    @Autowired
    private ConsumeRuleService ruleService;
    @Autowired
    private ClassificationService classificationService;

    @GetMapping
    public List<ConsumeRule> list(@RequestParam(value = "categoryId", required = false) String categoryId,
                                  @RequestParam(value = "active", required = false) Integer active){
        LambdaQueryWrapper<ConsumeRule> qw = Wrappers.lambdaQuery();
        if (categoryId != null && !categoryId.trim().isEmpty()){
            qw.eq(ConsumeRule::getCategoryId, categoryId);
        }
        if (active != null){
            qw.eq(ConsumeRule::getActive, active);
        } else {
            qw.eq(ConsumeRule::getActive, 1);
        }
        qw.orderByAsc(ConsumeRule::getPriority);
        return ruleService.list(qw);
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
        ConsumeRule r = ruleService.getById(id);
        log.info("Delete rule id={}, categoryId={}, pattern={}, type={}, priority={}",
                r == null ? id : r.getId(),
                r == null ? null : r.getCategoryId(),
                r == null ? null : r.getPattern(),
                r == null ? null : r.getPatternType(),
                r == null ? null : r.getPriority());
        ruleService.removeById(id);
    }

    @PostMapping("/reload")
    public String reload(){
        classificationService.reload();
        return "ok";
    }
}
