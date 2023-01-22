package com.flab.order.domain.data;

import java.util.List;

public final class DecreaseInventoryData {

    private final List<InventoryData> inventoryData;
    private final boolean success;

    public DecreaseInventoryData(List<InventoryData> inventoryData, boolean success) {
        this.inventoryData = inventoryData;
        this.success = success;
    }

    public static DecreaseInventoryData success(List<InventoryData> inventoryData) {
        return new DecreaseInventoryData(inventoryData, true);
    }

    public static DecreaseInventoryData fail(List<InventoryData> inventoryData) {
        return new DecreaseInventoryData(inventoryData, false);
    }

    public List<InventoryData> getInventoryData() {
        return inventoryData;
    }

    public boolean isSuccess() {
        return success;
    }
}
