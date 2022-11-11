package com.flab.livecommerce.order.infrastructure.config;

import com.flab.livecommerce.item.domain.ItemRepository;
import com.flab.livecommerce.order.application.RegisterOrderProcessor;
import com.flab.livecommerce.order.application.SearchOrderProcessor;
import com.flab.livecommerce.order.domain.OrderItemSeriesService;
import com.flab.livecommerce.order.domain.OrderRepository;
import com.flab.livecommerce.order.infrastructure.persistence.OrderItemSeriesServiceImpl;
import com.flab.livecommerce.order.infrastructure.persistence.mybatis.MyBatisOrderItemOptionGroupMapper;
import com.flab.livecommerce.order.infrastructure.persistence.mybatis.MyBatisOrderItemOptionMapper;
import com.flab.livecommerce.order.infrastructure.persistence.mybatis.MyBatisOrderLineItemMapper;
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

    @Bean
    public SearchOrderProcessor searchOrderProcessor(
        OrderRepository orderRepository
    ) {
        return new SearchOrderProcessor(orderRepository);
    }

    @Bean
    public OrderItemSeriesService orderItemSeriesService(
        ItemRepository itemRepository,
        MyBatisOrderLineItemMapper orderLineItemMapper,
        MyBatisOrderItemOptionGroupMapper orderItemOptionGroupMapper,
        MyBatisOrderItemOptionMapper orderItemOptionMapper

    ) {
        return new OrderItemSeriesServiceImpl(
            itemRepository,
            orderLineItemMapper,
            orderItemOptionGroupMapper,
            orderItemOptionMapper
        );
    }
}
