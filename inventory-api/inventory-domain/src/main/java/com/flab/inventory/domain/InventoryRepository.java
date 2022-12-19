package com.flab.inventory.domain;

public interface InventoryRepository {

    Inventory save(Inventory inventory);

    Inventory findById(Long id);

    Inventory findByItemId(Long id);
}
