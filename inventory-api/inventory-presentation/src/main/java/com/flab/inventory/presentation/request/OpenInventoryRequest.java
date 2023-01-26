package com.flab.inventory.presentation.request;

import com.flab.inventory.application.command.OpenInventoryCommand;
import java.util.List;
import javax.validation.constraints.NotNull;

public final class OpenInventoryRequest {

    @NotNull
    private List<Long> inventoryIds;

    private OpenInventoryRequest() {
    }

    public OpenInventoryCommand toCommand() {
        return new OpenInventoryCommand(inventoryIds);
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }
}
