package com.flab.order.application;

import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.event.OrderPayedEvent;
import javax.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;

public class CompletedPayedProcessor {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    public CompletedPayedProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Transactional
    public void execute(OrderPayedEvent event) {
        var order = orderRepository.findById(event.getOrderId());
        order.completed();
        order.pollAllEvents().forEach(publisher::publishEvent);
    }
}
