package com.account.service.card;

import com.account.persist.model.BankCard;

import java.util.List;

public interface BankCardService {
    List<BankCard> listByBankAndType(String bankCode, String cardTypeCode);
    List<BankCard> listAllEnabled();
    BankCard getByBankTypeNo(String bankCode, String cardTypeCode, String cardNo);
}
