package com.flab.livecommerce.supports;

import com.flab.PaymentCompletedEvent;
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

}
