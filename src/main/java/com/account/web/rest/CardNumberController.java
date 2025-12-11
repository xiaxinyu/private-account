package com.account.web.rest;

import com.account.persist.model.BankCard;
import com.account.persist.model.KeyValue;
import com.account.service.card.BankCardService;
import com.account.service.card.CardService;
import com.account.web.rest.model.TreeNode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.account.core.tool.StringTool;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cards")
public class CardNumberController {

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private CardService cardService;

    @GetMapping("/numbers")
    public List<KeyValue> list(@RequestParam("bankCode") String bankCode,
                               @RequestParam("cardTypeCode") String cardTypeCode){
        String b = bankCode == null ? "" : bankCode.trim().toUpperCase();
        String t = cardTypeCode == null ? "" : cardTypeCode.trim().toLowerCase();
        if (b.isEmpty() || t.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        List<BankCard> cards = bankCardService.listByBankAndType(b, t);
        return cards.stream().map(c -> {
            KeyValue kv = new KeyValue();
            kv.setKey(c.getCardNo());
            kv.setValue(c.getCardNo());
            return kv;
        }).collect(Collectors.toList());
    }

    @GetMapping("/list")
    public List<KeyValue> allCards(){
        java.util.List<KeyValue> result = new java.util.ArrayList<>();
        KeyValue all = new KeyValue();
        all.setKey("kong");
        all.setValue("All cards");
        result.add(all);
        result.addAll(
            bankCardService.listAllEnabled().stream().map(c -> {
                KeyValue kv = new KeyValue();
                kv.setKey(c.getId());
                String name = c.getCardName();
                if (name == null || name.trim().isEmpty()) {
                    String bank = c.getBankCode();
                    String type = c.getCardTypeCode();
                    String no = c.getCardNo();
                    String masked = (no == null) ? "" : (no.length() > 4 ? ("****" + no.substring(no.length()-4)) : no);
                    String typeText = "credit".equalsIgnoreCase(type) ? "Credit Card" : ("debit".equalsIgnoreCase(type) ? "Debit Card" : type);
                    name = String.join(" ", new String[]{bank == null ? "" : bank, typeText == null ? "" : typeText, masked}).trim();
                }
                kv.setValue(name);
                return kv;
            }).collect(java.util.stream.Collectors.toList())
        );
        return result;
    }

    @GetMapping
    public List<BankCard> listCards(@RequestParam(value = "cardTypeCode", required = false) String cardTypeCode){
        LambdaQueryWrapper<BankCard> qw = Wrappers.lambdaQuery();
        qw.select(BankCard::getId, BankCard::getBankCode, BankCard::getCardTypeCode, BankCard::getCardNo, BankCard::getCardName, BankCard::getDeleted)
          .eq(BankCard::getDeleted, 0);
        if (cardTypeCode != null && !cardTypeCode.trim().isEmpty()){
            qw.eq(BankCard::getCardTypeCode, cardTypeCode.trim().toLowerCase());
        }
        qw.orderByAsc(BankCard::getBankCode).orderByAsc(BankCard::getCardTypeCode).orderByAsc(BankCard::getCardNo);
        return bankCardService.list(qw);
    }

    @PostMapping
    public BankCard add(@RequestBody BankCard card){
        String bankUpper = card.getBankCode() == null ? "" : card.getBankCode().trim().toUpperCase();
        String typeLower = card.getCardTypeCode() == null ? "" : card.getCardTypeCode().trim().toLowerCase();
        card.setBankCode(bankUpper);
        card.setCardTypeCode(typeLower);
        card.setId(generateCardId(bankUpper, typeLower));
        if (card.getDeleted() == null){ card.setDeleted(0); }
        if (card.getCreateUser() == null){ card.setCreateUser("system"); }
        if (card.getUpdateUser() == null){ card.setUpdateUser("system"); }
        if (card.getCreateTime() == null){ card.setCreateTime(new java.util.Date()); }
        card.setUpdateTime(new java.util.Date());
        bankCardService.save(card);
        return card;
    }

    @PutMapping("/{id}")
    public BankCard update(@PathVariable("id") String id, @RequestBody BankCard card){
        card.setId(id);
        if (card.getDeleted() == null){ card.setDeleted(0); }
        if (card.getUpdateUser() == null){ card.setUpdateUser("system"); }
        card.setUpdateTime(new java.util.Date());
        bankCardService.updateById(card);
        return card;
    }

    private String generateCardId(String bankUpper, String typeLower){
        String bankLower = bankUpper == null ? "" : bankUpper.toLowerCase();
        String tShort = "credit".equals(typeLower) ? "c" : ("debit".equals(typeLower) ? "d" : (typeLower == null || typeLower.isEmpty() ? "x" : typeLower.substring(0,1)));
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^id-"+java.util.regex.Pattern.quote(bankLower)+"-"+java.util.regex.Pattern.quote(tShort)+"-(\\d+)$");
        LambdaQueryWrapper<BankCard> qw = Wrappers.lambdaQuery();
        qw.select(BankCard::getId).eq(BankCard::getBankCode, bankUpper).eq(BankCard::getCardTypeCode, typeLower);
        java.util.List<BankCard> rows = bankCardService.list(qw);
        int next = 1;
        for(BankCard r: rows){
            String id = r.getId();
            if (id == null) continue;
            java.util.regex.Matcher m = p.matcher(id.trim());
            if (m.find()){
                try { next = Math.max(next, Integer.parseInt(m.group(1))+1); } catch (Exception ignore) {}
            }
        }
        String candidate;
        do {
            candidate = String.format("id-%s-%s-%03d", bankLower, tShort, next);
            next++;
        } while (bankCardService.getById(candidate) != null);
        return candidate;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        BankCard c = bankCardService.getById(id);
        if (c != null){
            c.setDeleted(1);
            bankCardService.updateById(c);
        }
    }

    @GetMapping("/tree")
    public List<TreeNode> tree(){
        java.util.List<TreeNode> parents = new java.util.ArrayList<>();
        String[] types = new String[]{"credit", "debit"};
        String[] typeTexts = new String[]{"Credit", "Debit"};
        for(int i=0;i<types.length;i++){
            String type = types[i];
            String typeText = typeTexts[i];
            TreeNode p = new TreeNode();
            p.setId(type);
            p.setText(typeText);
            LambdaQueryWrapper<BankCard> qw = Wrappers.lambdaQuery();
            qw.select(BankCard::getId, BankCard::getBankCode, BankCard::getCardTypeCode, BankCard::getCardNo, BankCard::getCardName, BankCard::getDeleted)
              .eq(BankCard::getCardTypeCode, type)
              .eq(BankCard::getDeleted, 0)
              .orderByAsc(BankCard::getBankCode)
              .orderByAsc(BankCard::getCardNo);
            List<BankCard> children = bankCardService.list(qw);
            List<TreeNode> childNodes = children.stream().map(c -> {
                TreeNode n = new TreeNode();
                n.setId(c.getId());
                String name = c.getCardName();
                if (name == null || name.trim().isEmpty()){
                    String bank = c.getBankCode();
                    String no = c.getCardNo();
                    String masked = (no == null) ? "" : (no.length() > 4 ? ("****" + no.substring(no.length()-4)) : no);
                    name = String.join(" ", new String[]{bank == null ? "" : bank, masked}).trim();
                }
                n.setText(name);
                return n;
            }).collect(Collectors.toList());
            p.setChildren(childNodes);
            if (!childNodes.isEmpty()) { p.setState("closed"); }
            parents.add(p);
        }
        return parents;
    }
}
