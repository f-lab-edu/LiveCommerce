package com.flab.order.application.result;

import com.flab.order.domain.data.InventoryData;
import java.util.List;

public final class OrderPayedResult {

    private Long id;
    private List<InventoryData> inventoryData;
    private boolean success;

    private OrderPayedResult() {
    }

    public OrderPayedResult(Long id, List<InventoryData> inventoryData, boolean success) {
        this.id = id;
        this.inventoryData = inventoryData;
        this.success = success;
    }
}
