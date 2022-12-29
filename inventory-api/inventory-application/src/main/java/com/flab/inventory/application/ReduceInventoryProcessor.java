package com.flab.inventory.application;

import com.flab.inventory.application.command.ReduceInventoryCommand;
import com.flab.inventory.domain.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;

public class ReduceInventoryProcessor {

    private final InventoryRepository inventoryRepository;

    public ReduceInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void execute(ReduceInventoryCommand command) {
        var inventory = inventoryRepository.findByItemId(command.getItemId());
        inventory.reduce(command.getCount());
    }
}
