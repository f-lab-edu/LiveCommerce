package com.flab.inventory.application.command;

import java.util.List;

public final class CloseInventoryCommand {

    private List<Long> inventoryIds;

    private CloseInventoryCommand() {
    }

    public CloseInventoryCommand(List<Long> inventoryIds) {
        this.inventoryIds = inventoryIds;
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }
}
