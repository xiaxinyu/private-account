package com.account.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
/**
 * Created by Summer.Xia on 08/31/2015.
 */
public class Base implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Date nowDate = new Date();
    private Integer version = 0;
    private String createuser;
    private Date createtime = nowDate;
    private String updateuser;
    private Date updatetime = nowDate;
    @TableId
    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
