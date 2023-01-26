package com.flab.inventory.presentation.response;

import com.flab.inventory.domain.data.InventoryData;
import java.util.List;

public final class InventoryResponse {

    private List<InventoryData> inventoryData;

    public InventoryResponse(List<InventoryData> inventoryData) {
        this.inventoryData = inventoryData;
    }

    private InventoryResponse() {
    }

    public List<InventoryData> getInventoryData() {
        return inventoryData;
    }
}
