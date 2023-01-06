package com.flab.order;

import com.flab.order.application.PaymentCompletedProcessor;
import com.flab.order.domain.event.PaymentCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final PaymentCompletedProcessor processor;

    public OrderEventHandler(
        PaymentCompletedProcessor processor
    ) {
        this.processor = processor;
    }

    @EventListener
    public void handle(PaymentCompletedEvent event) {
        var order = processor.execute(event);
    }
}
