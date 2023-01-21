package com.flab.order.domain.data;

public final class ItemQuantity {

    private final Long itemId;
    private final Integer count;

    public ItemQuantity(Long itemId, Integer count) {
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
