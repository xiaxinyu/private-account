package com.account.persist.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 卡片
 *
 * @author XIAXINYU3
 * @date 2020.4.22
 */
@Setter
@Getter
@ToString
@TableName("card")
public class Card extends BaseEntity {
    @TableId
    private String cardId;
    private String cardName;
}
