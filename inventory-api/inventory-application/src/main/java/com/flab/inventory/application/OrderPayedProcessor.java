package com.flab.inventory.application;

import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.event.OrderPayedEvent;
import com.flab.inventory.domain.exception.NotEnoughQuantityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class OrderPayedProcessor {

    private static final Logger log = LoggerFactory.getLogger(OrderPayedProcessor.class);
    private final InventoryRepository inventoryRepository;
    private final ApplicationEventPublisher publisher;

    public OrderPayedProcessor(
        InventoryRepository inventoryRepository,
        ApplicationEventPublisher publisher
    ) {
        this.inventoryRepository = inventoryRepository;
        this.publisher = publisher;
    }

    @Transactional
    public void execute(OrderPayedEvent event) {
        event.getPayedItemInfos().forEach(
            itemQuantity -> {
                var inventory = inventoryRepository.findByItemId(itemQuantity.getItemId());

                try {
                    inventory.reduce(itemQuantity.getCount());
                    inventoryRepository.save(inventory);
                } catch (NotEnoughQuantityException e) {
                    inventory.failReduce();
                    inventory.pollAllEvents().forEach(publisher::publishEvent);
                    throw e;
                }
            }
        );
    }
}
