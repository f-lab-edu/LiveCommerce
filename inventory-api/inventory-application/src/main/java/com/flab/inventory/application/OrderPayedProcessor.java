package com.flab.inventory.application;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.event.OrderPayedEvent;

public class OrderPayedProcessor {

    private final InventoryRepository inventoryRepository;

    public OrderPayedProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory execute(OrderPayedEvent event) {
        return null;
    }
}
