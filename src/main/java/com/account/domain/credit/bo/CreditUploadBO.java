package com.account.domain.credit.bo;

import com.account.domain.model.CreditRecord;

import java.util.List;

public class CreditUploadBO {
    private CreditRecord creditRecord;
    private List<String[]> dataRows;

    public CreditRecord getCreditRecord() { return creditRecord; }
    public void setCreditRecord(CreditRecord creditRecord) { this.creditRecord = creditRecord; }
    public List<String[]> getDataRows() { return dataRows; }
    public void setDataRows(List<String[]> dataRows) { this.dataRows = dataRows; }
}
