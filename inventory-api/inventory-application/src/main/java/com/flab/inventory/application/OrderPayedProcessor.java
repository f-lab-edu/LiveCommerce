package com.flab.inventory.application;

import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.event.OrderPayedEvent;
import com.flab.inventory.domain.exception.NotEnoughQuantity;
import org.springframework.transaction.annotation.Transactional;

public class OrderPayedProcessor {

    private final InventoryRepository inventoryRepository;

    public OrderPayedProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void execute(OrderPayedEvent event) {
        event.getPayedItemInfos().forEach(
            payedItemInfo -> {
                var inventory = inventoryRepository.findByItemId(payedItemInfo.getItemId());

                try {
                    inventory.reduce(payedItemInfo.getCount());
                    inventoryRepository.save(inventory);
                } catch (NotEnoughQuantity e) {
                    inventory.failReduce();
                }
            }
        );
    }
}
