package com.flab.inventory.domain.data;

public final class InventoryData {

    private Long inventoryId;
    private Integer quantity;

    public InventoryData(Long inventoryId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }

    private InventoryData() {
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
