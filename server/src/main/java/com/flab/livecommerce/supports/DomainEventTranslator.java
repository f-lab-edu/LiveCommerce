package com.flab.livecommerce.supports;


import static java.util.Comparator.comparing;

import com.flab.inventory.domain.ItemQuantity;
import com.flab.order.domain.event.OrderPayedEvent;
import com.flab.payment.domain.PaymentCompletedEvent;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

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
            new com.flab.order.domain.event.PaymentCompletedEvent(
                event.getOrderId(),
                event.getPayedAmount(),
                event.getOccurredOn()
            )
        );
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void translate(OrderPayedEvent event) {
        List<ItemQuantity> itemQuantities = event.getItemQuantities()
            .stream()
            .sorted(comparing(com.flab.order.domain.ItemQuantity::getItemId))
            .map(
                payedItemInfo -> new ItemQuantity(
                    payedItemInfo.getItemId(),
                    payedItemInfo.getCount()
                )
            ).collect(Collectors.toList());

        publisher.publishEvent(
            new com.flab.inventory.presentation.request.OrderPayedEvent(
                event.getOrderId(),
                event.getUserId(),
                itemQuantities,
                event.getOccurredOn()
            )
        );
    }
}
