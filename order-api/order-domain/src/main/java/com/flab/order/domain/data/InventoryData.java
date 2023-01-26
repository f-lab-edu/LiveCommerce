package com.flab.order.domain.data;

public final class InventoryData {

    private final Long inventoryId;
    private final Integer quantity;

    public InventoryData(Long inventoryId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
