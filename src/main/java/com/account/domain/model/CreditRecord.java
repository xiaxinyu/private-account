package com.account.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Summer.Xia on 08/31/2015.
 */
@TableName("credit_record")
public class CreditRecord extends Base {
    private static final long serialVersionUID = 1L;

    private String billData;
    private String billFileName;
    private Integer billItemsNumber;

    public String getBillData() {
        return billData;
    }

    public void setBillData(String billData) {
        this.billData = billData;
    }

    public String getBillFileName() {
        return billFileName;
    }

    public void setBillFileName(String billFileName) {
        this.billFileName = billFileName;
    }

    public Integer getBillItemsNumber() {
        return billItemsNumber;
    }

    public void setBillItemsNumber(Integer billItemsNumber) {
        this.billItemsNumber = billItemsNumber;
    }
}
