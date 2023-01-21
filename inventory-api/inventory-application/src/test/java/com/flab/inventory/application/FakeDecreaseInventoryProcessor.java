package com.flab.inventory.application;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.data.ItemQuantity;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationEventPublisher;

public class FakeDecreaseInventoryProcessor {
    private final InventoryRepository inventoryRepository;
    private final ApplicationEventPublisher publisher;

    public FakeDecreaseInventoryProcessor(
        InventoryRepository inventoryRepository,
        ApplicationEventPublisher publisher
    ) {
        this.inventoryRepository = inventoryRepository;
        this.publisher = publisher;
    }


    public List<ItemQuantity> execute(DecreaseInventoryCommand command) {
        Map<Long, Integer> itemQuantityMap = getItemQuantityMap(command.getItemQuantities());
        List<Inventory> inventories = inventoryRepository.findByItemIdIn(itemQuantityMap.keySet());

        inventories.forEach(
            inventory -> {
                inventory.decrease(itemQuantityMap.get(inventory.getItemId()));
            }
        );
        return null;
    }

    private Map<Long, Integer> getItemQuantityMap(List<ItemQuantity> itemQuantities) {
        return itemQuantities.stream()
            .collect(groupingBy(ItemQuantity::getItemId, summingInt(ItemQuantity::getCount)));
    }
}
