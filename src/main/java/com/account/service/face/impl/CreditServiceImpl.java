package com.account.service.face.impl;

import com.account.core.exception.DateParseException;
import com.account.core.tool.DateTool;
import com.account.core.tool.StringTool;
import com.account.persist.mapper.CreditMapper;
import com.account.persist.model.Card;
import com.account.persist.model.Credit;
import com.account.persist.model.KeyValue;
import com.account.persist.model.Page;
import com.account.service.card.CardService;
import com.account.service.exception.AppException;
import com.account.service.exception.AppServiceException;
import com.account.service.face.ICreditService;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
 * Created by Summer.Xia on 09/01/2015.
 */
@Service("creditService")
@Slf4j
public class CreditServiceImpl implements ICreditService {
    @Autowired
    CardService cardService;

    @Autowired
    CreditMapper creditMapper;

    @Override
    public void updateCredit(Credit credit, String userName) throws AppServiceException {
        try {
            credit.setUpdateUser(userName);
            creditMapper.updateCredit(credit);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
    }

    @Override
    public void deleteCredit(String id) throws AppServiceException {
        try {
            creditMapper.deleteCredit(id);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
    }

    @Override
    public List<Credit> getCredits(Credit credit, Page page) throws AppServiceException {
        List<Credit> result = null;
        try {
            log.info("Query credits：page={}", page);
            result = creditMapper.getCredits(credit, page);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }

    @Override
    public int countCredit(Credit credit) throws AppServiceException {
        int result = 0;
        try {
            result = creditMapper.countCredit(credit);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }

    @Override
    public void addCredits(List<String[]> rowDatas, String userName, String recordID) {
        if (CollectionUtils.isEmpty(rowDatas)) {
            throw new AppException("No exist original credit data, can't call add credits!");
        }

        Map<String, Card> cardMap = cardService.queryAllCards();

        boolean skipTitle = true;
        for (String[] rowData : rowDatas) {
            try {
                if (skipTitle) {
                    skipTitle = false;
                    continue;
                }
                if (rowData == null || rowData.length < 6) {
                    continue;
                }

                Credit credit = new Credit();
                credit.setId(StringTool.generateID());
                credit.setCreateUser(userName);
                credit.setUpdateUser(userName);
                credit.setId(StringTool.generateID());
                String cardId = StringTool.cleanStr(rowData[0]);
                credit.setCardId(cardId);
                credit.setTransactionDate(DateTool.changeStringToDate(StringTool.cleanStr(rowData[1]), DateTool.DF_YYYY_MM_DD));
                credit.setBookKeepingDate(DateTool.changeStringToDate(StringTool.cleanStr(rowData[2]), DateTool.DF_YYYY_MM_DD));
                credit.setTransactionDesc(StringTool.cleanStr(rowData[3]));
                credit.setBalanceCurrency(StringTool.cleanStr(rowData[4]));

                String balanceMoney = StringTool.cleanStr(rowData[5]);
                credit.setBalanceMoney(StringUtils.isBlank(balanceMoney) ? 0 : Double.parseDouble(balanceMoney));

                credit.setCardTypeId(1);
                credit.setCardTypeName(cardMap.get(cardId).getCardName());
                credit.setRecordID(recordID);
                creditMapper.insert(credit);
            } catch (Exception e) {
                log.error("Saving credit has error: credit={}", StringUtils.join(rowDatas, ","), e);
            }
        }
    }

    private void fetchCreditParam(Credit credit) throws DateParseException {
        if (StringTool.isNullOrEmpty(credit.getCardTypeName())) {
            credit.setCardTypeName(null);
        }
        if (!StringTool.isNullOrEmpty(credit.getConsumeID())) {
            credit.setConsumes(credit.getConsumeID().split(","));
        }
        if (!StringTool.isNullOrEmpty(credit.getTransactionDateStartStr())) {
            credit.setTransactionDateStart(DateTool.changeStringToDate(credit.getTransactionDateStartStr(), DateTool.DF_MM_DD_YYYY));
        }
        if (!StringTool.isNullOrEmpty(credit.getTransactionDateEndStr())) {
            credit.setTransactionDateEnd(DateTool.changeStringToDate(credit.getTransactionDateEndStr(), DateTool.DF_MM_DD_YYYY));
        }
        if (StringTool.isNullOrEmpty(credit.getDemoArea())) {
            credit.setDemoArea(null);
        }
    }

    @Override
    public String consumeReport(Credit credit) throws AppServiceException {
        String result = StringTool.EMPTY;
        try {
            fetchCreditParam(credit);
            List<KeyValue> list = creditMapper.consumeReport(credit);
            result = JSONArray.toJSONString(list);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }

    @Override
    public String weekConsumeReport(Credit credit) throws AppServiceException {
        String result = StringTool.EMPTY;
        try {
            fetchCreditParam(credit);
            List<KeyValue> list = creditMapper.weekConsumeReport(credit);
            result = JSONArray.toJSONString(list);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }

    @Override
    public String monthConsumeReport(Credit credit) throws AppServiceException {
        String result = StringTool.EMPTY;
        try {
            fetchCreditParam(credit);
            List<KeyValue> list = creditMapper.monthConsumeReport(credit);
            result = JSONArray.toJSONString(list).toString();
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }
}
