package com.account.persist.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 实体类父类
 *
 * @author XIAXINYU3
 * @date 2020.4.22
 */
@Setter
@Getter
public class BaseEntity {
    @TableField(exist = false)
    private Integer version = 0;

    @TableField(value = "createUser")
    private String createUser;

    @TableField(value = "createTime")
    private Date createTime;

    @TableField(value = "updateUser")
    private String updateUser;

    @TableField(value = "updateTime")
    private Date updateTime;
}
