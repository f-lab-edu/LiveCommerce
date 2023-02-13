package com.flab.order.application;

import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.result.OrderResult;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class CreateOrderProcessor {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(CreateOrderProcessor.class);

    public CreateOrderProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Transactional
    public OrderResult execute(Long userId, CreateOrderCommand command) {
        var order = Order.create(userId, command.getPayMethod(), command.toLineItems());
        //todo 주문 검증 (상품 정보 변화)
        //validator(order)
        orderRepository.save(order);
        order.pollAllEvents().forEach(publisher::publishEvent);

        return new OrderResult(order.getId(), order.getUserId(), order.getTotalAmount());
    }
}

