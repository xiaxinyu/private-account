package com.account.persist.mapper;

import java.util.List;

import com.account.persist.model.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.persist.model.Page;
import com.account.persist.model.UnEmployment;

/**
 * Created by Summer.Xia on 10/13/2015.
 */
public interface UnEmploymentMapper extends BaseMapper<UnEmployment> {
	void addUnEmployment(UnEmployment unEmployment);
	
	void updateUnEmployment(UnEmployment unEmployment);
	
	void deleteUnEmployment(String id);
	
	int countUnEmployments(@Param("unEmployment")UnEmployment unEmployment);
	
	List<UnEmployment> getUnEmployments(@Param("unEmployment")UnEmployment unEmployment,@Param("page")Page page);
}
