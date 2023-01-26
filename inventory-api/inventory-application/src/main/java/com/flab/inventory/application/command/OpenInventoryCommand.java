package com.flab.inventory.application.command;

import java.util.List;

public final class OpenInventoryCommand {

    private List<Long> inventoryIds;

    private OpenInventoryCommand() {
    }

    public OpenInventoryCommand(List<Long> inventoryIds) {
        this.inventoryIds = inventoryIds;
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }
}
