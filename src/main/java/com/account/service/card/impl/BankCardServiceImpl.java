package com.account.service.card.impl;

import com.account.persist.mapper.BankCardMapper;
import com.account.persist.model.BankCard;
import com.account.service.card.BankCardService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardServiceImpl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardService {
    @Override
    public List<BankCard> listByBankAndType(String bankCode, String cardTypeCode) {
        LambdaQueryWrapper<BankCard> qw = Wrappers.lambdaQuery();
        qw.select(BankCard::getId, BankCard::getBankCode, BankCard::getCardTypeCode, BankCard::getCardNo, BankCard::getCardName, BankCard::getDeleted)
          .eq(BankCard::getBankCode, bankCode)
          .eq(BankCard::getCardTypeCode, cardTypeCode)
          .eq(BankCard::getDeleted, 0)
          .orderByAsc(BankCard::getCardNo);
        return super.list(qw);
    }

    @Override
    public List<BankCard> listAllEnabled() {
        LambdaQueryWrapper<BankCard> qw = Wrappers.lambdaQuery();
        qw.select(BankCard::getId, BankCard::getBankCode, BankCard::getCardTypeCode, BankCard::getCardNo, BankCard::getCardName, BankCard::getDeleted)
          .eq(BankCard::getDeleted, 0)
          .orderByAsc(BankCard::getBankCode)
          .orderByAsc(BankCard::getCardTypeCode)
          .orderByAsc(BankCard::getCardNo);
        return super.list(qw);
    }

    @Override
    public BankCard getByBankTypeNo(String bankCode, String cardTypeCode, String cardNo) {
        LambdaQueryWrapper<BankCard> qw = Wrappers.lambdaQuery();
        qw.select(BankCard::getId, BankCard::getBankCode, BankCard::getCardTypeCode, BankCard::getCardNo, BankCard::getCardName, BankCard::getDeleted)
          .eq(BankCard::getBankCode, bankCode)
          .eq(BankCard::getCardTypeCode, cardTypeCode)
          .eq(BankCard::getCardNo, cardNo)
          .eq(BankCard::getDeleted, 0)
          .last("limit 1");
        return super.getOne(qw);
    }
}
