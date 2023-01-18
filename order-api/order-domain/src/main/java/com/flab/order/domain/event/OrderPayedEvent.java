package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.order.domain.ItemQuantity;
import com.flab.order.domain.Order;
import java.time.LocalDateTime;
import java.util.List;

public final class OrderPayedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final Integer totalAmount;
    private final List<ItemQuantity> itemQuantities;
    private final LocalDateTime occurredOn;

    public OrderPayedEvent(Order order) {
        this.orderId = order.getId();
        this.userId = order.getUserId();
        this.totalAmount = order.getTotalAmount();
        this.itemQuantities = order.getItemQuantities();
        this.occurredOn = LocalDateTime.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
