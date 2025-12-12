package com.account.infrastructure.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.domain.model.HouseRent;
import com.account.domain.model.Page;

public interface HouseRentMapper extends BaseMapper<HouseRent> {
    int countHouseRent(@Param("houseRent") HouseRent houseRent);
    List<HouseRent> getHouseRents(@Param("houseRent") HouseRent houseRent, @Param("page") Page page);
}
