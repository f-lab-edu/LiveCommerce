package com.flab.inventory.presentation.request;

import com.flab.inventory.application.command.UpdateInventoryCommand;
import java.util.List;

public class UpdateInventoryRequest {

    List<Long> inventoryIds;

    public UpdateInventoryCommand toCommand() {
        return null;
    }

}
