package com.account.persist.model;

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
    private String categoryId;
    private String pattern;
    private String patternType;
    private Integer priority;
    private Integer active;
    private String bankCode;
    private String cardTypeCode;
    private String remark;
}
