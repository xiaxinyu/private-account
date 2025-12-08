package com.account.web.rest;

import com.account.persist.model.BankCard;
import com.account.service.card.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CardNumberController {

    @Autowired
    private BankCardService bankCardService;

    @RequestMapping("/card/numbers")
    @ResponseBody
    public List<Map<String, String>> list(@RequestParam("bankCode") String bankCode,
                                          @RequestParam("cardTypeCode") String cardTypeCode){
        List<BankCard> cards = bankCardService.listByBankAndType(bankCode, cardTypeCode);
        return cards.stream().map(c -> Map.of("id", c.getCardNo(), "text", c.getCardNo())).collect(Collectors.toList());
    }
}

