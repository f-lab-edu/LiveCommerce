package com.flab.livecommerce.infrastructure.order.config;

import com.flab.livecommerce.application.order.RegisterOrderProcessor;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.OrderItemSeriesServiceImpl;
import com.flab.livecommerce.infrastructure.order.persistence.OrderRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProcessorConfig {

    @Bean
    public RegisterOrderProcessor registerOrderProcessor(
        OrderRepository orderRepository,
        OrderItemSeriesService orderItemSeriesService
    ) {
        return new RegisterOrderProcessor(
            orderRepository,
            orderItemSeriesService
        );
    }

    @Bean
    public OrderItemSeriesService orderItemSeriesService(
        ItemRepository itemRepository,
        OrderRepositoryAdapter orderRepositoryAdapter
    ) {
        return new OrderItemSeriesServiceImpl(
            itemRepository,
            orderRepositoryAdapter
        );
    }
}
