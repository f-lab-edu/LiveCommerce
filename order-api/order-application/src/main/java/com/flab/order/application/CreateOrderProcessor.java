package com.flab.order.application;

import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class CreateOrderProcessor {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    public CreateOrderProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Transactional
    public Order execute(Long userId, CreateOrderCommand command) {
        var order = Order.create(userId, command.getPayMethod(), command.toLineItems());
        //todo 주문 검증 (상품 정보 변화)
        //validator(order)
        orderRepository.save(order);
        order.pollAllEvents().forEach(publisher::publishEvent);

        return order;
    }
}

