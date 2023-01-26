package com.flab.inventory.domain;

import java.util.List;

public interface InventoryRepository {

    Inventory findById(Long id);

    List<Inventory> findAllById(Iterable<Long> id);

    Inventory findByItemId(Long id);

    List<Inventory> findByItemIdIn(Iterable<Long> itemIds);

    Inventory save(Inventory inventory);

    List<Inventory> saveAll(List<Inventory> inventories);
}
