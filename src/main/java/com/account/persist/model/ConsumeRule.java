package com.account.persist.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("consume_rule")
@Getter
@Setter
public class ConsumeRule extends BaseEntity {
    @TableId
    private String id;
    @TableField(value = "categoryId")
    private String categoryId;
    private String pattern;
    @TableField(value = "patternType")
    private String patternType;
    private Integer priority;
    private Integer active;
    @TableField(value = "bankCode")
    private String bankCode;
    @TableField(value = "cardTypeCode")
    private String cardTypeCode;
    private String remark;
}
