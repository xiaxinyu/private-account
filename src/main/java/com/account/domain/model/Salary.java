package com.account.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("salary")
public class Salary extends Base {
    private static final long serialVersionUID = 1L;
    private String cardId;
    private Date transactionDate;
    private String transactionDesc;
    private String balanceCurrency;
    private Double balanceMoney;
    private Integer cardTypeId;
    private String cardTypeName;
    private Integer deleted;
    private String demoArea;

    private String year;
    private String transactionDateStartStr;
    private Date transactionDateStart;
    private String transactionDateEndStr;
    private Date transactionDateEnd;

    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }
    public String getTransactionDesc() { return transactionDesc; }
    public void setTransactionDesc(String transactionDesc) { this.transactionDesc = transactionDesc; }
    public String getBalanceCurrency() { return balanceCurrency; }
    public void setBalanceCurrency(String balanceCurrency) { this.balanceCurrency = balanceCurrency; }
    public Double getBalanceMoney() { return balanceMoney; }
    public void setBalanceMoney(Double balanceMoney) { this.balanceMoney = balanceMoney; }
    public Integer getCardTypeId() { return cardTypeId; }
    public void setCardTypeId(Integer cardTypeId) { this.cardTypeId = cardTypeId; }
    public String getCardTypeName() { return cardTypeName; }
    public void setCardTypeName(String cardTypeName) { this.cardTypeName = cardTypeName; }
    public Integer getDeleted() { return deleted; }
    public void setDeleted(Integer deleted) { this.deleted = deleted; }
    public String getDemoArea() { return demoArea; }
    public void setDemoArea(String demoArea) { this.demoArea = demoArea; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getTransactionDateStartStr() { return transactionDateStartStr; }
    public void setTransactionDateStartStr(String transactionDateStartStr) { this.transactionDateStartStr = transactionDateStartStr; }
    public Date getTransactionDateStart() { return transactionDateStart; }
    public void setTransactionDateStart(Date transactionDateStart) { this.transactionDateStart = transactionDateStart; }
    public String getTransactionDateEndStr() { return transactionDateEndStr; }
    public void setTransactionDateEndStr(String transactionDateEndStr) { this.transactionDateEndStr = transactionDateEndStr; }
    public Date getTransactionDateEnd() { return transactionDateEnd; }
    public void setTransactionDateEnd(Date transactionDateEnd) { this.transactionDateEnd = transactionDateEnd; }
}
