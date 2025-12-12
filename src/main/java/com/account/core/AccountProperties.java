package com.account.core;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
