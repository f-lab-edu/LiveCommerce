package com.flab.inventory.application.result;

import com.flab.inventory.domain.data.InventoryData;
import java.util.List;

public final class InventoryResult {

    private List<InventoryData> inventoryData;

    public InventoryResult(List<InventoryData> inventoryData) {
        this.inventoryData = inventoryData;
    }

    private InventoryResult() {
    }

    public List<InventoryData> getInventoryData() {
        return inventoryData;
    }
}
