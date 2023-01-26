package com.flab.inventory.application.command;

import com.flab.inventory.domain.data.ItemQuantity;
import java.util.List;

public final class DecreaseInventoryCommand {

    private List<ItemQuantity> itemQuantities;

    private DecreaseInventoryCommand() {
    }

    public DecreaseInventoryCommand(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }
}
