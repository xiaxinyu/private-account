package com.account.application.consume;

import com.account.domain.model.ConsumeCategory;
import com.account.domain.model.ConsumeRule;
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
    @Autowired
    private DecisionTreeClassifier decisionTreeClassifier;

    private volatile List<ConsumeRule> rules;
    private final Map<String, ConsumeCategory> categoryMap = new ConcurrentHashMap<>();

    public void reload(){
        rules = ruleService.listActive();
        categoryMap.clear();
        for (ConsumeCategory c : categoryService.listAll()){
            if (c == null) continue;
            String idKey = c.getId();
            String codeKey = c.getCode() == null ? null : c.getCode().trim();
            if (idKey != null) { categoryMap.put(idKey, c); }
            if (codeKey != null && !codeKey.isEmpty()) { categoryMap.put(codeKey, c); }
        }
        decisionTreeClassifier.reload();
    }

    public Result classify(String narration, String bankCode, String cardTypeCode){
        if (rules == null) reload();
        DecisionTreeClassifier.Result r = decisionTreeClassifier.classify(narration, bankCode, cardTypeCode);
        if (r == null) return null;
        Result res = new Result();
        res.id = r.id;
        res.name = r.name;
        return res;
    }

    public java.util.List<Result> classifyTopN(String narration, String bankCode, String cardTypeCode, int topN){
        if (rules == null) reload();
        java.util.List<DecisionTreeClassifier.Result> list = decisionTreeClassifier.classifyTopN(narration, bankCode, cardTypeCode, topN);
        java.util.List<Result> out = new java.util.ArrayList<>();
        if(list == null) list = java.util.Collections.emptyList();
        for(DecisionTreeClassifier.Result r : list){
            if(r == null) continue;
            Result res = new Result();
            res.id = r.id;
            res.name = r.name;
            out.add(res);
        }
        if(out.isEmpty()){
            ConsumeCategory fallback = null;
            ConsumeCategory c1 = categoryMap.get("OTHER-01");
            ConsumeCategory c2 = categoryMap.get("OTHER");
            fallback = c1 != null ? c1 : c2;
            if(fallback == null){
                for(ConsumeCategory c : categoryService.listAll()){
                    if(c == null) continue;
                    String code = c.getCode() == null ? null : c.getCode().trim();
                    if("OTHER-01".equalsIgnoreCase(code) || "OTHER".equalsIgnoreCase(code)){ fallback = c; break; }
                }
            }
            if(fallback != null){
                Result fr = new Result();
                fr.id = fallback.getCode();
                fr.name = fallback.getName();
                out.add(fr);
            }
        }
        return out;
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
