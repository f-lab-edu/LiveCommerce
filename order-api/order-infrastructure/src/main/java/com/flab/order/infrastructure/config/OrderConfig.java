package com.flab.order.infrastructure.config;

import com.flab.order.application.CompletePayedProcessor;
import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.FailInventoryReducedProcessor;
import com.flab.order.application.PaymentCompletedProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.domain.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public CreateOrderProcessor createOrderProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        return new CreateOrderProcessor(orderRepository, publisher);
    }

    @Bean
    public SearchOrderProcessor searchOrderProcessor(
        OrderRepository orderRepository
    ) {
        return new SearchOrderProcessor(orderRepository);
    }

    @Bean
    public PaymentCompletedProcessor paymentCompletedProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        return new PaymentCompletedProcessor(orderRepository, publisher);
    }

    @Bean
    public FailInventoryReducedProcessor failInventoryReducedProcessor(
        OrderRepository orderRepository
    ) {
        return new FailInventoryReducedProcessor(orderRepository);
    }

    @Bean
    public CompletePayedProcessor completedPayedProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        return new CompletePayedProcessor(orderRepository, publisher);
    }
}
