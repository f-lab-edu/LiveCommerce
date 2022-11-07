package com.flab.livecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.flab")
public class LiveCommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveCommerceApplication.class, args);
    }
}
