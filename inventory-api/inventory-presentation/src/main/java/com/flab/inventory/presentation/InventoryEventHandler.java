package com.flab.inventory.presentation;

import com.flab.inventory.application.facade.InventoryManager;
import com.flab.inventory.presentation.request.OrderPayedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class InventoryEventHandler {

    private final InventoryManager inventoryManager;

    public InventoryEventHandler(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @Async
    @TransactionalEventListener
    public void handle(OrderPayedEvent event) {
        inventoryManager.orderPayed(event.toCommand());
    }

}
