package com.flab.inventory.application.command;

import com.flab.inventory.domain.ItemQuantity;
import java.util.List;

public class OrderPayedCommand {

    private final List<ItemQuantity> itemQuantities;

    public OrderPayedCommand(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }
}
