package com.flab.inventory.application;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.ItemQuantity;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class DecreaseInventoryProcessor {

    private static final Logger log = LoggerFactory.getLogger(DecreaseInventoryProcessor.class);
    private final InventoryRepository inventoryRepository;
    private final ApplicationEventPublisher publisher;

    public DecreaseInventoryProcessor(
        InventoryRepository inventoryRepository,
        ApplicationEventPublisher publisher
    ) {
        this.inventoryRepository = inventoryRepository;
        this.publisher = publisher;
    }

    @Transactional
    public List<ItemQuantity> execute(DecreaseInventoryCommand command) {
        Map<Long, Integer> itemQuantityMap = getItemQuantityMap(command.getItemQuantities());
        List<Inventory> inventories = inventoryRepository.findByItemIdIn(itemQuantityMap.keySet());

        inventories.forEach(
            inventory -> {
                log.info("재고감소 로직 수행");
                inventory.decrease(itemQuantityMap.get(inventory.getItemId()));
            }
        );

        return command.getItemQuantities();
    }

    private Map<Long, Integer> getItemQuantityMap(List<ItemQuantity> itemQuantities) {
        return itemQuantities.stream()
            .collect(groupingBy(ItemQuantity::getItemId, summingInt(ItemQuantity::getCount)));
    }
}
