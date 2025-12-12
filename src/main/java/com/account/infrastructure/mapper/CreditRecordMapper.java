package com.account.infrastructure.mapper;

import com.account.domain.model.CreditRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CreditRecordMapper extends BaseMapper<CreditRecord> {
    void addCreditRecord(CreditRecord creditRecord);
}
