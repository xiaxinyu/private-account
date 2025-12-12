package com.account.application.card;

import com.account.domain.model.BankCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BankCardService extends IService<BankCard> {
    List<BankCard> listByBankAndType(String bankCode, String cardTypeCode);
    List<BankCard> listAllEnabled();
    BankCard getByBankTypeNo(String bankCode, String cardTypeCode, String cardNo);
    BankCard getByCardNo(String cardNo);
}
