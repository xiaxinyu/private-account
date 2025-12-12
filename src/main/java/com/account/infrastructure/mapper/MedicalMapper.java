package com.account.infrastructure.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.domain.model.Medical;
import com.account.domain.model.Page;

public interface MedicalMapper extends BaseMapper<Medical> {
    void addMedical(Medical medical);
    void updateMedical(Medical medical);
    void deleteMedical(String id);
    int countMedicals(@Param("medical") Medical medical);
    List<Medical> getMedicals(@Param("medical") Medical medical, @Param("page") Page page);
}
