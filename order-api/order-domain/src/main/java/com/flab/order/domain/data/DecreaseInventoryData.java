package com.flab.order.domain.data;

import java.util.List;

public final class DecreaseInventoryData {

    private List<InventoryData> inventoryData;

    public DecreaseInventoryData(List<InventoryData> inventoryData) {
        this.inventoryData = inventoryData;
    }

    private DecreaseInventoryData() {
    }

    public static DecreaseInventoryData success(List<InventoryData> inventoryData) {
        return new DecreaseInventoryData(inventoryData);
    }

    public static DecreaseInventoryData fail(List<InventoryData> inventoryData) {
        return new DecreaseInventoryData(inventoryData);
    }

    public List<InventoryData> getInventoryData() {
        return inventoryData;
    }
}
