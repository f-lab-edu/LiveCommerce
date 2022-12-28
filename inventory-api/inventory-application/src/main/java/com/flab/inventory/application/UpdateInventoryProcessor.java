package com.flab.inventory.application;

import com.flab.inventory.application.command.UpdateInventoryCommand;
import com.flab.inventory.domain.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;

public class UpdateInventoryProcessor {

    private final InventoryRepository inventoryRepository;

    public UpdateInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void execute(UpdateInventoryCommand command) {

    }
}
