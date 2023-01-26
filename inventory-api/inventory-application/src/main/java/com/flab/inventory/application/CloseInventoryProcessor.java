package com.flab.inventory.application;

import com.flab.inventory.application.command.CloseInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class CloseInventoryProcessor {

    private final InventoryRepository inventoryRepository;

    public CloseInventoryProcessor(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void execute(CloseInventoryCommand command) {
        List<Inventory> inventories = inventoryRepository
            .findAllById(command.getInventoryIds());

        inventories.forEach(Inventory::close);
    }
}
