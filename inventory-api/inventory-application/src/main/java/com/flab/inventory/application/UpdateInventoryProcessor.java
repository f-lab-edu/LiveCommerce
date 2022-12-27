package com.flab.inventory.application;

import com.flab.inventory.application.command.UpdateInventoryCommand;
import com.flab.inventory.domain.InventoryRepository;

public class UpdateInventoryProcessor {

    private final InventoryRepository inventoryRepository;

    public UpdateInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void execute(Long sellerId, UpdateInventoryCommand command) {

    }
}
