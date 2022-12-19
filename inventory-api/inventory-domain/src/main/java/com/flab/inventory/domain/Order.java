package com.flab.inventory.domain;

import java.util.List;

public final class Order {

    private final Long id;
    private final Long userId;
    private final List<OrderLineItem> orderLineItems;

    public Order(Long id, Long userId, List<OrderLineItem> orderLineItems) {
        this.id = id;
        this.userId = userId;
        this.orderLineItems = orderLineItems;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }
}
