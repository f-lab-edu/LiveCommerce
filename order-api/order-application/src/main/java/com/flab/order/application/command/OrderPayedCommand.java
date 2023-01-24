package com.flab.order.application.command;

import com.flab.order.domain.data.ItemQuantityData;
import com.flab.order.domain.event.OrderPayedEvent;
import java.util.List;

public final class OrderPayedCommand {

    private final Long orderId;
    private final List<ItemQuantityData> itemQuantityData;

    public OrderPayedCommand(Long orderId, List<ItemQuantityData> itemQuantityData) {
        this.orderId = orderId;
        this.itemQuantityData = itemQuantityData;
    }

    public OrderPayedCommand(OrderPayedEvent event) {
        this.orderId = event.getOrderId();
        this.itemQuantityData = event.getItemQuantities();
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<ItemQuantityData> getItemQuantityData() {
        return itemQuantityData;
    }
}
