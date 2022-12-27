package com.flab.inventory.application.facade;

import com.flab.inventory.application.CloseInventoryProcessor;
import com.flab.inventory.application.UpdateInventoryProcessor;
import com.flab.inventory.application.command.CloseInventoryCommand;
import com.flab.inventory.application.command.UpdateInventoryCommand;
import com.flab.inventory.domain.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryManager {

    private final UpdateInventoryProcessor updateInventoryProcessor;
    private final CloseInventoryProcessor closeInventoryProcessor;

    public InventoryManager(
        UpdateInventoryProcessor updateInventoryProcessor,
        CloseInventoryProcessor closeInventoryProcessor
    ) {
        this.updateInventoryProcessor = updateInventoryProcessor;
        this.closeInventoryProcessor = closeInventoryProcessor;
    }

    public void close(CloseInventoryCommand command) {
        closeInventoryProcessor.execute(command);
    }

    public Inventory update(Long sellerId, UpdateInventoryCommand command) {
        updateInventoryProcessor.execute(sellerId, command);
        return null;
    }
}
