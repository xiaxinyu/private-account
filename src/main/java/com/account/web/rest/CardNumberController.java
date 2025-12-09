package com.account.web.rest;

import com.account.persist.model.BankCard;
import com.account.persist.model.KeyValue;
import com.account.service.card.BankCardService;
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

    @GetMapping("/numbers")
    public List<KeyValue> list(@RequestParam("bankCode") String bankCode,
                               @RequestParam("cardTypeCode") String cardTypeCode){
        List<BankCard> cards = bankCardService.listByBankAndType(bankCode, cardTypeCode);
        return cards.stream().map(c -> {
            KeyValue kv = new KeyValue();
            kv.setKey(c.getCardNo());
            kv.setValue(c.getCardNo());
            return kv;
        }).collect(Collectors.toList());
    }
}
