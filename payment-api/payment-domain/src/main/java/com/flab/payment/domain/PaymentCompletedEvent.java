package com.flab.payment.domain;

import com.flab.common.domain.DomainEvent;
import java.time.LocalDateTime;

public final class PaymentCompletedEvent implements DomainEvent {

    private final Long orderId;
    private final Integer payedAmount;
    private final LocalDateTime occurredOn;

    public PaymentCompletedEvent(Payment payment) {
        this.orderId = payment.getOrderId();
        this.payedAmount = payment.getTotalAmount();
        this.occurredOn = LocalDateTime.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getPayedAmount() {
        return payedAmount;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
