package com.flab.livecommerce.infrastructure.order.config;

import com.flab.livecommerce.application.order.RegisterOrderProcessor;
import com.flab.livecommerce.application.order.SearchOrderProcessor;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.OrderItemSeriesServiceImpl;
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
    public SearchOrderProcessor searchOrderProcessor(
        OrderRepository orderRepository
    ) {
        return new SearchOrderProcessor(orderRepository);
    }

    @Bean
    public OrderItemSeriesService orderItemSeriesService(
        ItemRepository itemRepository,
        OrderRepository orderRepository
    ) {
        return new OrderItemSeriesServiceImpl(
            itemRepository,
            orderRepository
        );
    }
}
