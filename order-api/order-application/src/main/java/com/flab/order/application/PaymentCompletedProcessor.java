package com.flab.order.application;

import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.event.PaymentCompletedEvent;
import org.springframework.transaction.annotation.Transactional;

public class PaymentCompletedProcessor {

    public PaymentCompletedProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private final OrderRepository orderRepository;

    @Transactional
    public Order execute(PaymentCompletedEvent event) {
        var order = orderRepository.findById(event.getOrderId());
        order.payed(event.getPayedAmount());

        return order;
    }
}
