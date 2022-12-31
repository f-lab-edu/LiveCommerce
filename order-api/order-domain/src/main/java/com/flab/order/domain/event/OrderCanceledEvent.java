package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.order.domain.Order;
import java.time.LocalDateTime;

public final class OrderCanceledEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final LocalDateTime occurredOn;

    public OrderCanceledEvent(Order order) {
        this.orderId = order.getId();
        this.userId = order.getUserId();
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return this.occurredOn;
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
}
