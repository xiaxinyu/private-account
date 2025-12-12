package com.account.application.importer.impl;

import com.account.core.DateTool;
import com.account.core.StringTool;
import com.account.domain.model.Credit;
import com.account.application.importer.StatementImporter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CcbCreditStatementImporter implements StatementImporter {
    private static final Pattern DATE8 = Pattern.compile("^\\d{8}$");
    private static final Pattern CARD = Pattern.compile("^\\d{12,19}$");

    @Override
    public List<Credit> parse(List<String[]> rows, String bankCode, String cardTypeCode, String cardNo) {
        List<Credit> list = new ArrayList<>();
        boolean inTable = false;
        for (String[] row : rows) {
            if (row == null) {
                continue;
            }
            if (row.length < 7) {
                String joined = StringUtils.join(row, "");
                if (StringUtils.contains(joined, "交易日") && StringUtils.contains(joined, "入账金额")) {
                    inTable = true;
                }
                continue;
            }
            String c0 = StringTool.cleanStr(row[0]);
            String c1 = StringTool.cleanStr(row[1]);
            String c2 = StringTool.cleanStr(row[2]).replace("'", "");
            String c3 = StringTool.cleanStr(row[3]);
            String c4 = StringTool.cleanStr(row[4]);
            String c5 = StringTool.cleanStr(row[5]).replace(",", "");
            String c6 = StringTool.cleanStr(row[6]);

            if (!inTable) {
                if (StringUtils.equalsAny(c0, "交易日")) {
                    inTable = true;
                }
                continue;
            }

            if (!DATE8.matcher(c0).matches()) {
                continue;
            }
            if (!DATE8.matcher(c1).matches()) {
                continue;
            }
            String card = StringUtils.isNotBlank(cardNo) ? StringTool.cleanStr(cardNo) : c2;
            if (!CARD.matcher(card).matches()) {
                continue;
            }
            if (StringUtils.isBlank(c3)) {
                continue;
            }
            if (StringUtils.isBlank(c4)) {
                continue;
            }
            Double amount;
            try {
                amount = Double.parseDouble(c5);
            } catch (Exception ex) {
                continue;
            }
            if (amount == null) {
                continue;
            }

            Credit credit = new Credit();
            credit.setId(StringTool.generateID());
            credit.setCardId(card);
            try {
                credit.setTransactionDate(DateTool.changeStringToDate(c0, DateTool.DF_YYYYMMDD));
                credit.setBookKeepingDate(DateTool.changeStringToDate(c1, DateTool.DF_YYYYMMDD));
            } catch (Exception e) {
                continue;
            }
            credit.setTransactionDesc(c6);
            credit.setBalanceCurrency(c4);
            credit.setBalanceMoney(amount);
            credit.setCardTypeId(1);
            credit.setCardTypeName("信用卡");
            list.add(credit);
        }
        return list;
    }
}
