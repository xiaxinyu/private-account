package com.account.persist.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.persist.model.Medical;
import com.account.persist.model.Page;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
public interface MedicalMapper extends BaseMapper<Medical> {
	void addMedical(Medical medical);
	
	void updateMedical(Medical medical);
	
	void deleteMedical(String id);
	
	int countMedicals(@Param("medical")Medical medical);
	
	List<Medical> getMedicals(@Param("medical")Medical medical,@Param("page")Page page);
}
