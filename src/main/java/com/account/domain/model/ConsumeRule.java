package com.account.domain.model;

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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }
    public String getPatternType() { return patternType; }
    public void setPatternType(String patternType) { this.patternType = patternType; }
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
    public Integer getActive() { return active; }
    public void setActive(Integer active) { this.active = active; }
    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
    public String getCardTypeCode() { return cardTypeCode; }
    public void setCardTypeCode(String cardTypeCode) { this.cardTypeCode = cardTypeCode; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
