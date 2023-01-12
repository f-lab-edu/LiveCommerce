package com.flab.inventory.presentation;

import com.flab.inventory.application.facade.InventoryManager;
import com.flab.inventory.presentation.request.OrderPayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventHandler {

    private final InventoryManager inventoryManager;

    public InventoryEventHandler(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @EventListener
    public void handle(OrderPayedEvent event) {
        inventoryManager.orderPayed(event.toCommand());
    }

}
