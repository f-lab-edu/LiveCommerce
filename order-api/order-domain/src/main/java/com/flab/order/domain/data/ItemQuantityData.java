package com.flab.order.domain.data;

public final class ItemQuantityData {

    private final Long itemId;
    private final Integer count;

    public ItemQuantityData(Long itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getCount() {
        return count;
    }
}
