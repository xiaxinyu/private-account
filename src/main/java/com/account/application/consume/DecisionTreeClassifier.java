package com.account.application.consume;

import com.account.domain.model.ConsumeCategory;
import com.account.domain.model.ConsumeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Service
public class DecisionTreeClassifier {
    @Autowired
    private ConsumeRuleService ruleService;
    @Autowired
    private ConsumeCategoryService categoryService;

    private volatile List<ConsumeRule> rules;
    private final Map<String, ConsumeCategory> categoryMap = new ConcurrentHashMap<>();
    private final Map<String, List<RuleEntry>> equalsIndex = new ConcurrentHashMap<>();
    private final Map<String, List<RuleEntry>> tokenIndex = new ConcurrentHashMap<>();
    private volatile List<RuleEntry> regexEntries = new ArrayList<>();

    public void reload(){
        rules = ruleService.listActive();
        equalsIndex.clear();
        tokenIndex.clear();
        regexEntries = new ArrayList<>();
        categoryMap.clear();
        for (ConsumeCategory c : categoryService.listAll()){
            if (c == null) continue;
            String idKey = c.getId();
            String codeKey = s(c.getCode());
            if (idKey != null) { categoryMap.put(idKey, c); }
            if (StringUtils.hasText(codeKey)) { categoryMap.put(codeKey, c); }
        }
        if (rules == null) return;
        for (ConsumeRule r : rules){
            ConsumeCategory cat = categoryMap.get(r.getCategoryId());
            if (cat == null) continue;
            String type = s(r.getPatternType()).toLowerCase();
            String pat = s(r.getPattern());
            RuleEntry e = new RuleEntry();
            e.categoryId = s(cat.getCode());
            e.categoryName = cat.getName();
            e.priority = r.getPriority() == null ? 0 : r.getPriority();
            e.type = type;
            e.pattern = pat;
            e.bankCode = s(r.getBankCode());
            e.cardTypeCode = s(r.getCardTypeCode());
            if (!StringUtils.hasText(pat)) continue;
            if ("equals".equals(type)){
                equalsIndex.computeIfAbsent(pat, k -> new ArrayList<>()).add(e);
            } else if ("regex".equals(type)){
                try{ e.compiled = Pattern.compile(pat); regexEntries.add(e); }catch(Exception ignore){}
            } else {
                for(String t : splitTokens(pat)){
                    if(StringUtils.hasText(t)){
                        tokenIndex.computeIfAbsent(t, k -> new ArrayList<>()).add(e);
                    }
                }
            }
        }
    }

    public Result classify(String narration, String bankCode, String cardTypeCode){
        if (rules == null) reload();
        String text = normalize(narration);
        if (!StringUtils.hasText(text)) return null;
        String b = s(bankCode).toLowerCase();
        String c = s(cardTypeCode).toLowerCase();

        RuleEntry bestEq = null;
        List<RuleEntry> eqs = equalsIndex.get(text);
        if (eqs != null && !eqs.isEmpty()){
            for(RuleEntry e : eqs){ if(matchBankCard(e,b,c)){ if(bestEq==null || scoreFor(e)>scoreFor(bestEq)) bestEq=e; } }
            if (bestEq != null) return toResult(bestEq);
        }

        RuleEntry bestRegex = null;
        for (RuleEntry e : regexEntries){
            if (!matchBankCard(e,b,c)) continue;
            try{ if(e.compiled.matcher(text).find()){ if(bestRegex==null || scoreFor(e)>scoreFor(bestRegex)) bestRegex=e; } }catch(Exception ignore){}
        }
        if (bestRegex != null) return toResult(bestRegex);

        Map<String, Integer> scores = new HashMap<>();
        Set<String> seen = new HashSet<>();
        for(Map.Entry<String,List<RuleEntry>> en : tokenIndex.entrySet()){
            String token = en.getKey();
            if(seen.contains(token)) continue;
            if(text.contains(token)){
                seen.add(token);
                for(RuleEntry e : en.getValue()){
                    if(!matchBankCard(e,b,c)) continue;
                    int s = scores.getOrDefault(e.categoryId, 0);
                    scores.put(e.categoryId, s + scoreFor(e));
                }
            }
        }
        String bestCatId = null;
        int bestScore = Integer.MIN_VALUE;
        for(Map.Entry<String,Integer> sc : scores.entrySet()){
            if(sc.getValue() > bestScore){ bestScore = sc.getValue(); bestCatId = sc.getKey(); }
        }
        if (bestCatId == null) return null;
        ConsumeCategory cat = categoryMap.get(bestCatId);
        if (cat == null) return null;
        Result r = new Result();
        r.id = cat.getCode();
        r.name = cat.getName();
        r.priority = 0;
        return r;
    }

    private Result toResult(RuleEntry e){
        ConsumeCategory cat = categoryMap.get(e.categoryId);
        if (cat == null) return null;
        Result r = new Result();
        r.id = cat.getCode();
        r.name = cat.getName();
        r.priority = e.priority;
        return r;
    }

    private boolean matchBankCard(RuleEntry e, String b, String c){
        if (StringUtils.hasText(b) && StringUtils.hasText(e.bankCode) && !e.bankCode.equalsIgnoreCase(b)) return false;
        if (StringUtils.hasText(c) && StringUtils.hasText(e.cardTypeCode) && !e.cardTypeCode.equalsIgnoreCase(c)) return false;
        return true;
    }

    private String normalize(String s){
        if (s == null) return null;
        String t = s.toLowerCase();
        t = t.replaceAll("[^\\p{L}\\p{N}\\s]", " ");
        t = t.replaceAll("\\s+"," ");
        return t;
    }

    private String s(String v){ return v == null ? "" : v.trim(); }

    private int scoreFor(RuleEntry e){
        int base = 0;
        String t = e.type == null ? "contains" : e.type.toLowerCase();
        if ("equals".equals(t)) base = 100;
        else if ("regex".equals(t)) base = 80;
        else base = 60;
        return base + e.priority;
    }

    private List<String> splitTokens(String pat){
        List<String> tokens = new ArrayList<>();
        for(String p : pat.split("\\|")){ String tp = p==null?"":p.trim(); if(StringUtils.hasText(tp)) tokens.add(tp.toLowerCase()); }
        return tokens;
    }

    public static class Result{
        public String id;
        public String name;
        public int priority;
    }

    private static class RuleEntry{
        String categoryId;
        String categoryName;
        String type;
        String pattern;
        int priority;
        String bankCode;
        String cardTypeCode;
        Pattern compiled;
    }

    public List<Result> classifyTopN(String narration, String bankCode, String cardTypeCode, int topN){
        if (rules == null) reload();
        String text = normalize(narration);
        if (!StringUtils.hasText(text)) return java.util.Collections.emptyList();
        String b = s(bankCode).toLowerCase();
        String c = s(cardTypeCode).toLowerCase();

        Map<String, Integer> aggScore = new HashMap<>();
        Map<String, Integer> aggPriority = new HashMap<>();

        List<RuleEntry> eqs = equalsIndex.get(text);
        if (eqs != null && !eqs.isEmpty()){
            for(RuleEntry e : eqs){
                if(!matchBankCard(e,b,c)) continue;
                int sc = scoreFor(e);
                int ps = aggScore.getOrDefault(e.categoryId, 0);
                int pp = aggPriority.getOrDefault(e.categoryId, Integer.MIN_VALUE);
                aggScore.put(e.categoryId, Math.max(ps, sc));
                aggPriority.put(e.categoryId, Math.max(pp, e.priority));
            }
        }

        for (RuleEntry e : regexEntries){
            if (!matchBankCard(e,b,c)) continue;
            try{
                if(e.compiled.matcher(text).find()){
                    int sc = scoreFor(e);
                    int ps = aggScore.getOrDefault(e.categoryId, 0);
                    int pp = aggPriority.getOrDefault(e.categoryId, Integer.MIN_VALUE);
                    aggScore.put(e.categoryId, Math.max(ps, sc));
                    aggPriority.put(e.categoryId, Math.max(pp, e.priority));
                }
            }catch(Exception ignore){}
        }

        Set<String> seen = new HashSet<>();
        for(Map.Entry<String,List<RuleEntry>> en : tokenIndex.entrySet()){
            String token = en.getKey();
            if(seen.contains(token)) continue;
            if(text.contains(token)){
                seen.add(token);
                for(RuleEntry e : en.getValue()){
                    if(!matchBankCard(e,b,c)) continue;
                    int s = aggScore.getOrDefault(e.categoryId, 0);
                    aggScore.put(e.categoryId, s + scoreFor(e));
                    int pp = aggPriority.getOrDefault(e.categoryId, Integer.MIN_VALUE);
                    aggPriority.put(e.categoryId, Math.max(pp, e.priority));
                }
            }
        }

        List<Result> results = new ArrayList<>();
        for(String catId : aggScore.keySet()){
            ConsumeCategory cat = categoryMap.get(catId);
            if(cat == null) continue;
            Result r = new Result();
            r.id = cat.getCode();
            r.name = cat.getName();
            r.priority = aggPriority.getOrDefault(catId, 0);
            results.add(r);
        }

        results.sort((a,bRes) -> {
            int cp = Integer.compare(bRes.priority, a.priority);
            if(cp != 0) return cp;
            int as = aggScore.getOrDefault(a.id, 0);
            int bs = aggScore.getOrDefault(bRes.id, 0);
            int cs = Integer.compare(bs, as);
            if(cs != 0) return cs;
            return String.valueOf(a.name).compareToIgnoreCase(String.valueOf(bRes.name));
        });

        if (topN > 0 && results.size() > topN){
            return new ArrayList<>(results.subList(0, topN));
        }
        return results;
    }
}
