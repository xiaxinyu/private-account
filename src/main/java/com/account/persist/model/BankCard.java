package com.account.persist.model;

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
    private String cardTypeCode; // deposit | credit
    private String cardNo;
    private Integer deleted;
}

