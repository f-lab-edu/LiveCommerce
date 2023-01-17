package com.flab.order.domain;

import java.util.List;

public interface DecreaseInventoryProcessor {

    List<ItemQuantity> execute(List<ItemQuantity> itemQuantities);
}
