package com.flab.inventory.application;

import com.flab.inventory.application.command.IncreaseInventoryCommand;
import com.flab.inventory.domain.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;

public class IncreaseInventoryProcessor {

    private final InventoryRepository inventoryRepository;

    public IncreaseInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void execute(IncreaseInventoryCommand command) {
        var inventory = inventoryRepository.findByItemId(command.getItemId());
        inventory.increase(command.getCount());
    }
}
