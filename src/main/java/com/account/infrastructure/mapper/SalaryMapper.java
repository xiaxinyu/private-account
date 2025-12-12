package com.account.infrastructure.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.domain.model.Page;
import com.account.domain.model.Salary;

public interface SalaryMapper extends BaseMapper<Salary> {
    int countSalary(@Param("salary") Salary salary);
    List<Salary> getSalarys(@Param("salary") Salary salary, @Param("page") Page page);
}
