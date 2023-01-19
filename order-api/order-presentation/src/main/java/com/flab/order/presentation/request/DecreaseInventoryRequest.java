package com.flab.order.presentation.request;

import com.flab.order.domain.ItemQuantity;
import java.util.List;

public class DecreaseInventoryRequest {
    private List<ItemQuantity> itemQuantities;

    public DecreaseInventoryRequest(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    private DecreaseInventoryRequest() {
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }
}
