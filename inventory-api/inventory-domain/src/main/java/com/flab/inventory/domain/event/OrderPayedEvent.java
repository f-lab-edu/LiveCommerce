package com.flab.inventory.domain.event;


import com.flab.common.domain.DomainEvent;
import java.time.LocalDateTime;

public final class OrderPayedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final LocalDateTime occurredOn;

    public OrderPayedEvent(Long orderId, Long userId, LocalDateTime occurredOn) {
        this.orderId = orderId;
        this.userId = userId;
        this.occurredOn = occurredOn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }
}
