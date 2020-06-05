package com.account.persist.mapper;

import java.util.List;

import com.account.persist.model.CreditRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.persist.model.Endowment;
import com.account.persist.model.Page;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
public interface EndowmentMapper extends BaseMapper<Endowment> {
	void addEndowment(Endowment endowment);
	
	void updateEndowment(Endowment endowment);
	
	void deleteEndowment(String id);
	
	int countEndowments(@Param("endowment")Endowment endowment);
	
	List<Endowment> getEndowments(@Param("endowment")Endowment endowment,@Param("page")Page page);
}
