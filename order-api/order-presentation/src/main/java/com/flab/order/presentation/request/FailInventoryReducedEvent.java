package com.flab.order.presentation.request;

import com.flab.common.domain.DomainEvent;
import com.flab.order.application.command.FailInventoryReducedCommand;
import java.time.LocalDateTime;

public final class FailInventoryReducedEvent implements DomainEvent {

    private final Long inventoryId;
    private final Long orderId;
    private final Integer quantity;
    private final String itemName;
    private final LocalDateTime occurredOn;

    public FailInventoryReducedEvent(
        Long inventoryId,
        Long orderId,
        Integer quantity,
        String itemName,
        LocalDateTime occurredOn
    ) {
        this.inventoryId = inventoryId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.itemName = itemName;
        this.occurredOn = occurredOn;
    }

    public FailInventoryReducedCommand toCommand() {
        return new FailInventoryReducedCommand(orderId, occurredOn);
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
