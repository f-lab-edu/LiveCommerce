package com.flab.order;

import com.flab.order.application.PaymentCompletedProcessor;
import com.flab.order.domain.Order;
import com.flab.order.domain.event.PaymentCompletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final ApplicationEventPublisher publisher;
    private final PaymentCompletedProcessor processor;

    public OrderEventHandler(
        ApplicationEventPublisher publisher,
        PaymentCompletedProcessor processor
    ) {
        this.publisher = publisher;
        this.processor = processor;
    }

    @EventListener
    public void handle(PaymentCompletedEvent event) {
        var order = processor.execute(event);
        order.pollAllEvents().forEach(publisher::publishEvent);
    }
}
