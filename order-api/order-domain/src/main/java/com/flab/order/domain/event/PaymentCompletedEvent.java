package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import java.time.LocalDateTime;

public final class PaymentCompletedEvent implements DomainEvent {

    private final Long orderId;
    private final Integer payedAmount;
    private final LocalDateTime occurredOn;

    public PaymentCompletedEvent(Long orderId, Integer payedAmount, LocalDateTime occurredOn) {
        this.orderId = orderId;
        this.payedAmount = payedAmount;
        this.occurredOn = occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
