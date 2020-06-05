package com.account.service.face.impl;

import com.account.core.tool.StringTool;
import com.account.persist.mapper.CreditRecordMapper;
import com.account.persist.model.CreditRecord;
import com.account.service.face.ICreditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer.Xia on 9/7/2015.
 */
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
