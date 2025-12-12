package com.account.application.consume.impl;

import com.account.infrastructure.mapper.ConsumeRuleMapper;
import com.account.domain.model.ConsumeRule;
import com.account.application.consume.ConsumeRuleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

@org.springframework.stereotype.Service
public class ConsumeRuleServiceImpl extends ServiceImpl<ConsumeRuleMapper, ConsumeRule> implements ConsumeRuleService {
    @Override
    public List<ConsumeRule> listActive() {
        LambdaQueryWrapper<ConsumeRule> qw = Wrappers.lambdaQuery();
        qw.eq(ConsumeRule::getActive, 1).orderByAsc(ConsumeRule::getPriority);
        return super.list(qw);
    }
}
