package com.account.service.card;

import com.account.persist.model.Card;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface CardService extends IService<Card> {

    Map<String, Card> queryAllCards();
}
