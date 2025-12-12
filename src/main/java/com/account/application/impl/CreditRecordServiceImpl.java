package com.account.application.impl;

import com.account.core.StringTool;
import com.account.infrastructure.mapper.CreditRecordMapper;
import com.account.domain.model.CreditRecord;
import com.account.application.ICreditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("creditRecordService")
public class CreditRecordServiceImpl implements ICreditRecordService {
    @Autowired
    private CreditRecordMapper creditRecordMapper;

    @Override
    public void addCreditRecord(CreditRecord creditRecord, String userName) {
        creditRecord.setId(StringTool.generateID());
        creditRecord.setCreateuser(userName);
        creditRecord.setUpdateuser(userName);
        creditRecordMapper.addCreditRecord(creditRecord);
    }
}
