package com.flab.order.application.result;

import com.flab.order.domain.data.InventoryData;
import java.util.List;

public class OrderPayedResult {

    private Long id;
    private List<InventoryData> inventoryData;
    private boolean success;

    public OrderPayedResult(Long id, List<InventoryData> inventoryData, boolean success) {
        this.id = id;
        this.inventoryData = inventoryData;
        this.success = success;
    }
}
