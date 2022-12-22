package com.flab.order.domain;

public final class PayedItemInfo {

    private final Long itemId;
    private final Integer count;

    public PayedItemInfo(Long itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }
}
