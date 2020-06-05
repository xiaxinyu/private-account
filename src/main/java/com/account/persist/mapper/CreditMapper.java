package com.account.persist.mapper;

import com.account.persist.model.Credit;
import com.account.persist.model.KeyValue;
import com.account.persist.model.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Summer.Xia on 08/27/2014.
 */
public interface CreditMapper extends BaseMapper<Credit> {

	void addCreditList(@Param("credits")List<Credit> credits);
	
	void updateCredit(Credit credit);
	
	void deleteCredit(String id);
	
	int countCredit(@Param("credit")Credit credit);
	
	List<Credit> getCredits(@Param("credit")Credit credit,@Param("page")Page page);
	
	List<KeyValue> consumeReport(@Param("credit")Credit credit);
	
	List<KeyValue> weekConsumeReport(@Param("credit")Credit credit);
	
	List<KeyValue> monthConsumeReport(@Param("credit")Credit credit);
}
