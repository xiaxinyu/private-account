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
        int bestScore = Integer.MIN_VALUE;
        ConsumeCategory bestCat = null;
        for (ConsumeRule r : rules){
            if (StringUtils.hasText(r.getBankCode())){
                if (!r.getBankCode().equalsIgnoreCase(s(bankCode))) continue;
            }
            if (StringUtils.hasText(r.getCardTypeCode())){
                if (!r.getCardTypeCode().equalsIgnoreCase(s(cardTypeCode))) continue;
            }
            if (!match(text, r.getPattern(), r.getPatternType())) continue;
            ConsumeCategory cat = categoryMap.get(r.getCategoryId());
            if (cat == null) continue;
            int score = scoreFor(r);
            if (score > bestScore){
                bestScore = score;
                bestCat = cat;
            }
        }
        if (bestCat == null) return null;
        Result res = new Result();
        res.id = bestCat.getId();
        res.name = bestCat.getName();
        return res;
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

    private int scoreFor(ConsumeRule r){
        int base = 0;
        String t = r.getPatternType() == null ? "contains" : r.getPatternType().toLowerCase();
        if ("equals".equals(t)) base = 100;
        else if ("regex".equals(t)) base = 80;
        else base = 60;
        int weight = r.getPriority() == null ? 0 : r.getPriority();
        int score = base + weight;
        return score;
    }

    public static class Result{
        public String id;
        public String name;
    }
}
