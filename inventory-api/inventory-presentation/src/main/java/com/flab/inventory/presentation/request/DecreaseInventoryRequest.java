package com.flab.inventory.presentation.request;

import static java.util.Comparator.comparing;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.domain.ItemQuantity;
import java.util.List;
import java.util.stream.Collectors;

public class DecreaseInventoryRequest {
    private List<ItemQuantity> itemQuantities;

    public DecreaseInventoryRequest(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    private DecreaseInventoryRequest() {
    }

    public DecreaseInventoryCommand toCommand() {
        return new DecreaseInventoryCommand(sortedItemQuantities());
    }

    private List<ItemQuantity> sortedItemQuantities() {
        return this.itemQuantities.stream()
            .sorted(comparing(ItemQuantity::getItemId))
            .collect(Collectors.toList());
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }
}
