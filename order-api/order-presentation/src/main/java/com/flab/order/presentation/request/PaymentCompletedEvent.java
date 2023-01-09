package com.flab.order.presentation.request;

import com.flab.common.domain.DomainEvent;
import com.flab.order.application.command.PaymentCompletedCommand;
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

    public PaymentCompletedCommand toCommand() {
        return new PaymentCompletedCommand(orderId, payedAmount);
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
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
}
