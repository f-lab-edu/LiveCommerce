package com.flab.order.presentation.request;

import com.flab.order.domain.data.ItemQuantityData;
import java.util.List;

public class DecreaseInventoryRequest {
    private List<ItemQuantityData> itemQuantities;

    public DecreaseInventoryRequest(List<ItemQuantityData> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    private DecreaseInventoryRequest() {
    }

    public List<ItemQuantityData> getItemQuantities() {
        return itemQuantities;
    }
}
