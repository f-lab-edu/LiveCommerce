package com.flab.livecommerce.infrastructure.order.config;

import com.flab.livecommerce.application.order.RegisterOrderProcessor;
import com.flab.livecommerce.domain.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProcessorConfig {

    @Bean
    public RegisterOrderProcessor registerOrderProcessor(
        OrderRepository orderRepository
    ) {
        return new RegisterOrderProcessor(
            orderRepository
        );
    }
}
