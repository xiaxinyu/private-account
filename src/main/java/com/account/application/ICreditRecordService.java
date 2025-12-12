package com.account.application;

import com.account.domain.model.CreditRecord;

/**
 * Created by Summer.Xia on 9/7/2015.
 */
public interface ICreditRecordService {
    void addCreditRecord(CreditRecord creditRecord, String userName);
}
