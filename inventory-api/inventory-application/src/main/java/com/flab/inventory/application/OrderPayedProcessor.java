package com.flab.inventory.application;



import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import com.flab.common.exception.BaseException;
import com.flab.inventory.application.command.OrderPayedCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.ItemQuantity;
import com.flab.inventory.domain.exception.FailInventoryReducedException;
import java.util.List;
import java.util.Map;
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
    public void execute(OrderPayedCommand command) {
        Map<Long, Integer> itemQuantityMap = getItemQuantityMap(command.getItemQuantities());
        List<Inventory> inventories = inventoryRepository.findByItemIdIn(itemQuantityMap.keySet());

        inventories.forEach(
            inventory -> {
                try {
                    log.info("재고감소 로직 수행");
                    inventory.orderReduce(itemQuantityMap.get(inventory.getItemId()));
                } catch (BaseException e) {
                    log.error("error= ", e);
                    inventory.failReduce();
                    inventory.pollAllEvents().forEach(publisher::publishEvent);
                    throw new FailInventoryReducedException("재고감소에 실패했습니다.");
                }
            }
        );
    }

    private Map<Long, Integer> getItemQuantityMap(List<ItemQuantity> itemQuantities) {
        return itemQuantities
            .stream()
            .collect(groupingBy(ItemQuantity::getItemId, summingInt(ItemQuantity::getCount)));
    }
}
