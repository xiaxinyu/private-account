package com.account.infrastructure.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.account.domain.model.Page;
import com.account.domain.model.UnEmployment;

public interface UnEmploymentMapper extends BaseMapper<UnEmployment> {
    void addUnEmployment(UnEmployment unEmployment);
    void updateUnEmployment(UnEmployment unEmployment);
    void deleteUnEmployment(String id);
    int countUnEmployments(@Param("unEmployment") UnEmployment unEmployment);
    List<UnEmployment> getUnEmployments(@Param("unEmployment") UnEmployment unEmployment, @Param("page") Page page);
}
