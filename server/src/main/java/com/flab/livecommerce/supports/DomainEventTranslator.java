package com.flab.livecommerce.supports;

import com.flab.inventory.domain.PayedItemInfo;
import com.flab.order.domain.event.OrderPayedEvent;
import com.flab.payment.domain.PaymentCompletedEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
        List<PayedItemInfo> payedItemInfos = event.getPayedItemInfos()
            .stream()
            .sorted(Comparator.comparing(com.flab.order.domain.PayedItemInfo::getItemId))
            .map(
                payedItemInfo -> new PayedItemInfo(
                    payedItemInfo.getItemId(),
                    payedItemInfo.getCount()
                )
            ).collect(Collectors.toList());

        publisher.publishEvent(
            new com.flab.inventory.domain.event.OrderPayedEvent(
                event.getOrderId(),
                event.getUserId(),
                payedItemInfos,
                event.getOccurredOn()
            )
        );
    }

}
