package com.flab.inventory.application;

import com.flab.inventory.application.command.OpenInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class OpenInventoryProcessor {

    private final InventoryRepository inventoryRepository;

    public OpenInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void execute(OpenInventoryCommand command) {
        List<Inventory> inventories = inventoryRepository
            .findAllById(command.getInventoryIds());

        inventories.forEach(Inventory::open);
    }
}