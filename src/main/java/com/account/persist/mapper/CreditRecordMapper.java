package com.account.persist.mapper;

import com.account.persist.model.Credit;
import com.account.persist.model.CreditRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Created by Summer.Xia on 08/31/2015.
 */
public interface CreditRecordMapper extends BaseMapper<CreditRecord> {
	void addCreditRecord(CreditRecord creditRecord);
}
