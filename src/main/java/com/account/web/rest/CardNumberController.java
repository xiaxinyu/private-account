package com.account.web.rest;

import com.account.persist.model.BankCard;
import com.account.persist.model.KeyValue;
import com.account.service.card.BankCardService;
import com.account.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
