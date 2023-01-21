package com.flab.inventory.application;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.application.result.InventoryResult;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.data.InventoryData;
import com.flab.inventory.domain.data.ItemQuantity;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DecreaseInventoryProcessor {

    private final InventoryRepository inventoryRepository;
    private final Logger log = LoggerFactory.getLogger(DecreaseInventoryProcessor.class);

    public DecreaseInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public InventoryResult execute(DecreaseInventoryCommand command) {
        Map<Long, Integer> itemQuantityMap = getItemQuantityMap(command.getItemQuantities());
        List<Inventory> inventories = inventoryRepository.findByItemIdIn(itemQuantityMap.keySet());

        List<InventoryData> result = inventories.stream()
            .map(inventory -> new InventoryData(
                inventory.getId(),
                inventory.decrease(itemQuantityMap.get(inventory.getItemId()))))
            .collect(Collectors.toList());

        return new InventoryResult(result);
    }

    private Map<Long, Integer> getItemQuantityMap(List<ItemQuantity> itemQuantities) {
        return itemQuantities.stream()
            .collect(groupingBy(ItemQuantity::getItemId, summingInt(ItemQuantity::getCount)));
    }
}
