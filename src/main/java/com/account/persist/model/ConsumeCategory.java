package com.account.persist.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("consume_category")
@Getter
@Setter
public class ConsumeCategory extends BaseEntity {
    @TableId
    private String id;
    @TableField(value = "parentId")
    private String parentId;
    private String code;
    private String name;
    private Integer level;
    @TableField(value = "sortNo")
    private Integer sortNo;
    private Integer deleted;
}
