package com.flab.order.infrastructure.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.flab.order.infrastructure.feign")
public class OrderFeignConfig {

}
