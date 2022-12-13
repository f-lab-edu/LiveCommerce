package com.flab.order;

import com.flab.order.domain.event.PaymentCompletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final ApplicationEventPublisher publisher;

    public OrderEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void handle(PaymentCompletedEvent event) {

    }
}
