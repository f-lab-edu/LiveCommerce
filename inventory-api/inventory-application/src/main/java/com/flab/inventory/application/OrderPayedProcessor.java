package com.flab.inventory.application;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.OrderReader;
import com.flab.inventory.domain.event.OrderPayedEvent;
import org.springframework.transaction.annotation.Transactional;

public class OrderPayedProcessor {

    private final InventoryRepository inventoryRepository;
    private final OrderReader orderReader;

    public OrderPayedProcessor(InventoryRepository inventoryRepository, OrderReader orderReader) {
        this.inventoryRepository = inventoryRepository;
        this.orderReader = orderReader;
    }

    @Transactional
    public void execute(OrderPayedEvent event) {
        var order = orderReader.findById(event.getOrderId());

        order.getOrderLineItems().forEach(
            orderLineItem -> {
                var inventory = inventoryRepository.findByItemId(orderLineItem.getItemId());
                inventory.reduce(orderLineItem.getCount());
                inventoryRepository.save(inventory);
            }
        );
    }
}
