package com.account.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.account"})
public class PrivateAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrivateAccountApplication.class, args);
    }
}
