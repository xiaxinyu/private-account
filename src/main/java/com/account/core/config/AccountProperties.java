package com.account.core.config;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 流水线系统配置
 *
 * @author XIAXINYU3
 * @date 2019.12.26
 */
@Component
@ToString
@ConfigurationProperties(prefix = "account")
public class AccountProperties {
    private String desSignKey;

    public String getDesSignKey() {
        return desSignKey;
    }

    public void setDesSignKey(String desSignKey) {
        this.desSignKey = desSignKey;
    }
}
