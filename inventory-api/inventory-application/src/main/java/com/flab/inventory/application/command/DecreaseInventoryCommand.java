package com.flab.inventory.application.command;

import com.flab.inventory.domain.data.ItemQuantity;
import java.util.List;

public class DecreaseInventoryCommand {

    private final List<ItemQuantity> itemQuantities;

    public DecreaseInventoryCommand(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }
}
