package com.flab.livecommerce.supports;

import com.flab.inventory.domain.data.ItemQuantity;
import com.flab.order.domain.event.OrderCompletedEvent;
import com.flab.payment.domain.PaymentCompletedEvent;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventTranslator {

    private final ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(DomainEventTranslator.class);

    public DomainEventTranslator(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void translate(PaymentCompletedEvent event) {
        publisher.publishEvent(
            new com.flab.order.presentation.request.PaymentCompletedEvent(
                event.getOrderId(),
                event.getPayedAmount(),
                event.getOccurredOn()
            )
        );
    }

    @EventListener
    public void translate(OrderCompletedEvent event) {
        publisher.publishEvent(
            new com.flab.inventory.presentation.request.OrderCompletedEvent(
                event.getItemQuantities()
                    .stream()
                    .map(item -> new ItemQuantity(item.getItemId(), item.getCount()))
                    .collect(Collectors.toList()),
                event.getOccurredOn()
            )
        );
    }
}
