package com.account.persist.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Summer.Xia on 08/27/2014.
 */
@TableName("credit")
@Getter
@Setter
@ToString
public class Credit extends BaseEntity {
    @TableId(type = IdType.INPUT)
    private String id;
    private String cardId;
    @TableField(value = "bank_card_id")
    private String bankCardId;
    @TableField(value = "bank_card_name")
    private String bankCardName;
    private Date transactionDate;

    @TableField(value = "bookkeeping_date")
    private Date bookKeepingDate;

    private String transactionDesc;
    private String balanceCurrency;
    private Double balanceMoney;
    private Integer cardTypeId;
    private String cardTypeName;
    private Integer deleted;
    private Integer consumptionType;
    @TableField(value = "consume_id")
    private String consumeID;
    @TableField(value = "consume_name")
    private String consumeName;

    @TableField(value = "demoArea")
    private String demoArea;

    @TableField(value = "recordID")
    private String recordID;

    @TableField(exist = false)
    private String transactionDateStartStr;

    @TableField(exist = false)
    private Date transactionDateStart;

    @TableField(exist = false)
    private String transactionDateEndStr;

    @TableField(exist = false)
    private Date transactionDateEnd;

    @TableField(exist = false)
    private String[] consumes;

    @TableField(exist = false)
    private String weekName;

    @TableField(exist = false)
    private String year;

    @TableField(exist = false)
    private String month;
}
