package com.account.application.card;

import com.account.domain.model.Card;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface CardService extends IService<Card> {

    Map<String, Card> queryAllCards();
}
