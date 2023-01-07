package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.order.domain.Order;
import java.time.LocalDateTime;
import java.util.List;

public final class OrderCompletedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final List<Long> items;
    private final LocalDateTime occurredOn;

    public OrderCompletedEvent(Order order) {
        this.orderId = order.getId();
        this.userId = order.getUserId();
        this.items = order.getItemIds();
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

    public List<Long> getItems() {
        return items;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
