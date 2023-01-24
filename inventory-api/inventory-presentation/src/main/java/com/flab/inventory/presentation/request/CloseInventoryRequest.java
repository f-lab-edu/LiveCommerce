package com.flab.inventory.presentation.request;

import com.flab.inventory.application.command.CloseInventoryCommand;
import java.util.List;
import javax.validation.constraints.NotNull;

public final class CloseInventoryRequest {

    @NotNull
    private List<Long> inventoryIds;

    private CloseInventoryRequest() {
    }

    public CloseInventoryCommand toCommand() {
        return new CloseInventoryCommand(inventoryIds);
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }

}
