package com.account.domain.model;

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

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
    public String getCreateUser() { return createUser; }
    public void setCreateUser(String createUser) { this.createUser = createUser; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getUpdateUser() { return updateUser; }
    public void setUpdateUser(String updateUser) { this.updateUser = updateUser; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
