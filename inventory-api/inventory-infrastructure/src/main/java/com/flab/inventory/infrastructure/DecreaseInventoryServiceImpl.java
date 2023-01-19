package com.flab.inventory.infrastructure;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import com.flab.inventory.domain.DecreaseInventoryService;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.ItemQuantity;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecreaseInventoryServiceImpl implements DecreaseInventoryService {

    private final InventoryRepository inventoryRepository;
    private final Logger log = LoggerFactory.getLogger(DecreaseInventoryServiceImpl.class);

    public DecreaseInventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<ItemQuantity> service(List<ItemQuantity> itemQuantities) {
        Map<Long, Integer> itemQuantityMap = getItemQuantityMap(itemQuantities);
        List<Inventory> inventories = inventoryRepository.findByItemIdIn(itemQuantityMap.keySet());

        log.info(">>>>Inventory service 수행");
        inventories.forEach(
            inventory -> {
                inventory.decrease(itemQuantityMap.get(inventory.getItemId()));
            }
        );

        return itemQuantities;
    }

    private Map<Long, Integer> getItemQuantityMap(List<ItemQuantity> itemQuantities) {
        return itemQuantities.stream()
            .collect(groupingBy(ItemQuantity::getItemId, summingInt(ItemQuantity::getCount)));
    }
}
