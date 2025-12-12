package com.account.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("bank_card")
public class BankCard extends BaseEntity {
    @TableId
    private String id;
    private String bankCode;
    private String cardTypeCode; // debit | credit
    private String cardNo;
    private String cardName;
    private Integer deleted;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
    public String getCardTypeCode() { return cardTypeCode; }
    public void setCardTypeCode(String cardTypeCode) { this.cardTypeCode = cardTypeCode; }
    public String getCardNo() { return cardNo; }
    public void setCardNo(String cardNo) { this.cardNo = cardNo; }
    public String getCardName() { return cardName; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    public Integer getDeleted() { return deleted; }
    public void setDeleted(Integer deleted) { this.deleted = deleted; }
}
