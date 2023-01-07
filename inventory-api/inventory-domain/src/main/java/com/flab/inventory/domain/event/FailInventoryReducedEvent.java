package com.flab.inventory.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.inventory.domain.Inventory;
import java.time.LocalDateTime;

public final class FailInventoryReducedEvent implements DomainEvent {

    private final Long inventoryId;
    private final Integer quantity;
    private final String itemName;
    private final LocalDateTime occurredOn;

    public FailInventoryReducedEvent(Inventory inventory) {
        this.inventoryId = inventory.getId();
        this.quantity = inventory.getQuantity();
        this.itemName = inventory.getItemName();
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
