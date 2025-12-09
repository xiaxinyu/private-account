package com.account.service.consume;

import com.account.persist.model.ConsumeCategory;
import com.account.persist.model.ConsumeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Service
public class ClassificationService {
    @Autowired
    private ConsumeRuleService ruleService;
    @Autowired
    private ConsumeCategoryService categoryService;

    private volatile List<ConsumeRule> rules;
    private final Map<String, ConsumeCategory> categoryMap = new ConcurrentHashMap<>();

    public void reload(){
        rules = ruleService.listActive();
        for (ConsumeCategory c : categoryService.listAll()){
            categoryMap.put(c.getId(), c);
        }
    }

    public Result classify(String narration, String bankCode, String cardTypeCode){
        if (rules == null) reload();
        String text = normalize(narration);
        if (!StringUtils.hasText(text)) return null;
        for (ConsumeRule r : rules){
            if (StringUtils.hasText(r.getBankCode())){
                if (!r.getBankCode().equalsIgnoreCase(s(bankCode))) continue;
            }
            if (StringUtils.hasText(r.getCardTypeCode())){
                if (!r.getCardTypeCode().equalsIgnoreCase(s(cardTypeCode))) continue;
            }
            if (match(text, r.getPattern(), r.getPatternType())){
                ConsumeCategory cat = categoryMap.get(r.getCategoryId());
                if (cat == null) continue;
                Result res = new Result();
                res.id = cat.getId();
                res.name = cat.getName();
                return res;
            }
        }
        return null;
    }

    private boolean match(String text, String pattern, String type){
        if (!StringUtils.hasText(pattern)) return false;
        String t = type == null ? "contains" : type.toLowerCase();
        if ("equals".equals(t)) return text.equals(pattern);
        if ("regex".equals(t)) return Pattern.compile(pattern).matcher(text).find();
        return text.contains(pattern);
    }

    private String normalize(String s){
        if (s == null) return null;
        return s.replaceAll("\\s+", "").toLowerCase();
    }

    private String s(String v){
        return v == null ? "" : v.trim();
    }

    public static class Result{
        public String id;
        public String name;
    }
}
