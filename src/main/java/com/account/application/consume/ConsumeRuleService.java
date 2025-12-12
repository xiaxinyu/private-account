package com.account.application.consume;

import com.account.domain.model.ConsumeRule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsumeRuleService extends IService<ConsumeRule> {
    List<ConsumeRule> listActive();
}
