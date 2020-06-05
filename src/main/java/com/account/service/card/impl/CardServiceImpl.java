package com.account.service.card.impl;

import com.account.persist.mapper.CardMapper;
import com.account.persist.model.Card;
import com.account.service.card.CardService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

    @Override
    public Map<String, Card> queryAllCards() {
        LambdaQueryWrapper<Card> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(Card::getCardId);
        List<Card> cards = super.list(queryWrapper);

        Map<String, Card> cardMap = cards.stream().collect(Collectors.toMap(Card::getCardId, (p) -> p));
        return cardMap;
    }
}
