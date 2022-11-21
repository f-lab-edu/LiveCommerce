package com.flab.order.infrastructure.config;

import com.flab.order.domain.OrderRepository;
import com.flab.order.infrastructure.persistence.OrderRepositoryAdapter;
import com.flab.order.infrastructure.persistence.jpa.JpaOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRepositoryConfig {

    @Bean
    public OrderRepository orderRepository(JpaOrderRepository jpaOrderRepository) {
        return new OrderRepositoryAdapter(jpaOrderRepository);
    }
}
