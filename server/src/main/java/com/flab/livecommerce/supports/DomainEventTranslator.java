package com.flab.livecommerce.supports;

import com.flab.order.domain.event.OrderPayedEvent;
import com.flab.payment.domain.PaymentCompletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class DomainEventTranslator {

    private final ApplicationEventPublisher publisher;

    public DomainEventTranslator(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void translate(PaymentCompletedEvent event) {
        publisher.publishEvent(
            new com.flab.order.domain.event.PaymentCompletedEvent(
                event.getOrderId(),
                event.getPayedAmount(),
                event.getOccurredOn()
            )
        );
    }

    @EventListener
    public void translate(OrderPayedEvent event) {
        publisher.publishEvent(
            new com.flab.inventory.domain.event.OrderPayedEvent(
                event.getOrderId(),
                event.getUserId(),
                event.getOccurredOn()
            )
        );
    }

}
