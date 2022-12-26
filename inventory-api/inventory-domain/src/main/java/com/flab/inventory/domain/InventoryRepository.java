package com.flab.inventory.domain;

import java.util.List;

public interface InventoryRepository {

    Inventory findById(Long id);

    Inventory findByItemId(Long id);

    List<Inventory> findAllByItemId(Iterable<Long> itemIds);

    Inventory save(Inventory inventory);

    List<Inventory> saveAll(List<Inventory> inventories);
}
