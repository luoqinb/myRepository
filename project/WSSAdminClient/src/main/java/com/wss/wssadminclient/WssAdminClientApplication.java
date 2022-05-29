package com.wss.wssadminclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wss.wssadminclient.mapper")

public class WssAdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WssAdminClientApplication.class, args);
    }

}
