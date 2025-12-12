package com.account.infrastructure.mapper;

import com.account.domain.model.Credit;
import com.account.domain.model.KeyValue;
import com.account.domain.model.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CreditMapper extends BaseMapper<Credit> {
    void addCreditList(@Param("credits") List<Credit> credits);
    void updateCredit(Credit credit);
    void deleteCredit(String id);
    int countCredit(@Param("credit") Credit credit);
    List<Credit> getCredits(@Param("credit") Credit credit, @Param("page") Page page);
    List<KeyValue> consumeReport(@Param("credit") Credit credit);
    List<KeyValue> weekConsumeReport(@Param("credit") Credit credit);
    List<KeyValue> monthConsumeReport(@Param("credit") Credit credit);
}
