package com.account.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 系统启动监听器
 *
 * @author XIAXINYU3
 * @date 2019.12.26
 */
@Slf4j
@Component
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        printConfig(event);
    }

    /**
     * 打印启动系统配置信息
     *
     * @param event 系统启动事件
     */
    private void printConfig(ApplicationReadyEvent event) {
        AccountProperties prop = event.getApplicationContext().getBean(AccountProperties.class);
        log.info("DesSignKey : {}", prop.getDesSignKey());
    }
}
