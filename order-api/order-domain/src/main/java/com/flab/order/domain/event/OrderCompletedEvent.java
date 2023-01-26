package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.order.domain.Order;
import com.flab.order.domain.data.ItemQuantityData;
import java.time.LocalDateTime;
import java.util.List;

public final class OrderCompletedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final Integer totalAmount;
    private final List<ItemQuantityData> itemQuantities;
    private final LocalDateTime occurredOn;

    public OrderCompletedEvent(Order order) {
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

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public List<ItemQuantityData> getItemQuantities() {
        return itemQuantities;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
