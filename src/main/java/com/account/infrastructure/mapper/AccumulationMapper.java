package com.account.infrastructure.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.domain.model.Accumulation;
import com.account.domain.model.Page;

public interface AccumulationMapper extends BaseMapper<Accumulation> {
    void addAccumulation(Accumulation accumulation);
    void updateAccumulation(Accumulation accumulation);
    void deleteAccumulation(String id);
    int countAccumulations(@Param("accumulation") Accumulation accumulation);
    List<Accumulation> getAccumulations(@Param("accumulation") Accumulation accumulation, @Param("page") Page page);
}
