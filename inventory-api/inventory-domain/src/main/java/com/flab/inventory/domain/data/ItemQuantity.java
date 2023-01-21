package com.flab.inventory.domain.data;

public final class ItemQuantity {

    private Long itemId;
    private Integer count;

    public ItemQuantity(Long itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }

    private ItemQuantity() {
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getCount() {
        return count;
    }
}
