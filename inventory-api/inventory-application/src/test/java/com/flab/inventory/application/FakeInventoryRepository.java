package com.flab.inventory.application;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeInventoryRepository implements InventoryRepository {

    private final Map<Long, Inventory> data = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Inventory save(Inventory inventory) {
        if (inventory.getId() == null) {
            inventory.setId(idGenerator.incrementAndGet());
            data.put(inventory.getId(), inventory);
            return inventory;
        } else {
            return data.put(inventory.getId(), inventory);
        }
    }

    @Override
    public Inventory findById(Long id) {
        return null;
    }

    @Override
    public Inventory findByItemId(Long itemId) {
        return data.values().stream()
            .filter(inventory -> inventory.getItemId().equals(itemId))
            .findFirst()
            .orElse(null);
    }

}
