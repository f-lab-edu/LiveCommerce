package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.order.domain.Order;
import java.time.LocalDateTime;

public final class OrderPayedEvent implements DomainEvent {
    private final Long orderId;
    private final Long userId;
    private final LocalDateTime occurredOn;

    public OrderPayedEvent(Order order) {
        this.orderId = order.getId();
        this.userId = order.getUserId();
        this.occurredOn = LocalDateTime.now();
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
        return occurredOn;
    }
}
