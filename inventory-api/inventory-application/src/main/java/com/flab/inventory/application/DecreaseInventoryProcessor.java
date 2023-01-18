package com.flab.inventory.application;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.domain.DecreaseInventoryService;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.ItemQuantity;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class DecreaseInventoryProcessor {

    private static final Logger log = LoggerFactory.getLogger(DecreaseInventoryProcessor.class);
    private final InventoryRepository inventoryRepository;
    private final DecreaseInventoryService decreaseInventoryService;

    public DecreaseInventoryProcessor(
        InventoryRepository inventoryRepository,
        DecreaseInventoryService decreaseInventoryService
    ) {
        this.inventoryRepository = inventoryRepository;
        this.decreaseInventoryService = decreaseInventoryService;
    }

    @Transactional
    public List<ItemQuantity> execute(DecreaseInventoryCommand command) {
        return decreaseInventoryService.service(command.getItemQuantities());
    }

}
