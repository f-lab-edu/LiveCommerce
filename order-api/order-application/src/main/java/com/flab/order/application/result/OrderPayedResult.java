package com.flab.order.application.result;

import com.flab.order.domain.data.InventoryData;
import java.util.List;

public final class OrderPayedResult {

    private Long orderId;
    private List<InventoryData> inventoryData;

    private OrderPayedResult() {
    }

    public OrderPayedResult(Long orderId, List<InventoryData> inventoryData) {
        this.orderId = orderId;
        this.inventoryData = inventoryData;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<InventoryData> getInventoryData() {
        return inventoryData;
    }
}
