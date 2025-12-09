package com.account.service.consume;

import com.account.persist.model.ConsumeRule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsumeRuleService extends IService<ConsumeRule> {
    List<ConsumeRule> listActive();
}
