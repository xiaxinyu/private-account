package com.account.service.consume.impl;

import com.account.persist.mapper.ConsumeRuleMapper;
import com.account.persist.model.ConsumeRule;
import com.account.service.consume.ConsumeRuleService;
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
