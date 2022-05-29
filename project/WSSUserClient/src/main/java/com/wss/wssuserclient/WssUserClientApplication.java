package com.wss.wssuserclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wss")
public class WssUserClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WssUserClientApplication.class, args);
    }

}
