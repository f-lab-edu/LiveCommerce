package com.flab.inventory.application.command;

import java.util.List;

public class OpenInventoryCommand {

    private List<Long> inventoryIds;

    protected OpenInventoryCommand() {
    }

    public OpenInventoryCommand(List<Long> inventoryIds) {
        this.inventoryIds = inventoryIds;
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }
}
