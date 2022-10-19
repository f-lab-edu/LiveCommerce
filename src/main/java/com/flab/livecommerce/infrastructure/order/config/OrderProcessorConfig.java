package com.flab.livecommerce.infrastructure.order.config;

import com.flab.livecommerce.application.order.RegisterOrderProcessor;
import com.flab.livecommerce.application.order.SearchOrderProcessor;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.OrderItemSeriesServiceImpl;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderItemOptionGroupMapper;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderItemOptionMapper;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderLineItemMapper;
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
