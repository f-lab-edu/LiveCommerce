package com.flab.inventory.domain;

public final class OrderLineItem {

    private final Long id;
    private final Long itemId;
    private final Integer count;

    public OrderLineItem(Long id, Long itemId, Integer count) {
        this.id = id;
        this.itemId = itemId;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getCount() {
        return count;
    }
}
