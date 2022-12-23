package com.flab.inventory.domain.event;


import com.flab.common.domain.DomainEvent;
import com.flab.inventory.domain.ItemQuantity;
import java.time.LocalDateTime;
import java.util.List;

public final class OrderPayedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final List<ItemQuantity> itemQuantities;
    private final LocalDateTime occurredOn;

    public OrderPayedEvent(Long orderId, Long userId, List<ItemQuantity> itemQuantities, LocalDateTime occurredOn) {
        this.orderId = orderId;
        this.userId = userId;
        this.itemQuantities = itemQuantities;
        this.occurredOn = occurredOn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<ItemQuantity> getPayedItemInfos() {
        return itemQuantities;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }
}
