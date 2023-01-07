package com.flab.order.application;

import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.event.PaymentCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class PaymentCompletedProcessor {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(PaymentCompletedProcessor.class);

    public PaymentCompletedProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Transactional
    public Order execute(PaymentCompletedEvent event) {
        var order = orderRepository.findById(event.getOrderId());
        order.payed(event.getPayedAmount());
        order.pollAllEvents().forEach(publisher::publishEvent);
        //todo Entity 직접 반환하지 않고 변환해서 반환하도록 만들기
        return order;
    }
}
