package com.flab.inventory.domain;

import java.util.List;

public interface DecreaseInventoryService {

    List<ItemQuantity> service(List<ItemQuantity> itemQuantities);
}
