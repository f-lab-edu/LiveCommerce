package com.flab.order.infrastructure.config;

import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.application.facade.OrderManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderFacadeConfig {
    @Bean
    public OrderManager orderManager(
        CreateOrderProcessor createOrderProcessor,
        SearchOrderProcessor searchOrderProcessor
    ) {
        return new OrderManager(
            createOrderProcessor,
            searchOrderProcessor
        );
    }

}
