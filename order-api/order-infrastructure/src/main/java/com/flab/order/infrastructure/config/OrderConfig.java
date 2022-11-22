package com.flab.order.infrastructure.config;

import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.domain.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public CreateOrderProcessor createOrderProcessor(
        OrderRepository orderRepository
    ) {
        return new CreateOrderProcessor(orderRepository);
    }

    @Bean
    public SearchOrderProcessor searchOrderProcessor(
        OrderRepository orderRepository
    ) {
        return new SearchOrderProcessor(orderRepository);
    }
}
