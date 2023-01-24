package com.flab.inventory.application;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    public List<Inventory> saveAll(List<Inventory> inventories) {
        return inventories.stream()
            .map(this::save)
            .collect(Collectors.toList());
    }

    @Override
    public Inventory findById(Long id) {
        return data.values().stream()
            .filter(inventory -> inventory.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Inventory> findAllById(Iterable<Long> id) {
        List<Inventory> inventoryList = new ArrayList<>();

        id.forEach(
            inventoryId -> inventoryList.add(findById(inventoryId))
        );

        return inventoryList;
    }

    @Override
    public Inventory findByItemId(Long itemId) {
        return data.values().stream()
            .filter(inventory -> inventory.getItemId().equals(itemId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Inventory> findByItemIdIn(Iterable<Long> itemIds) {
        List<Inventory> inventoryList = new ArrayList<>();

        itemIds.forEach(
            id -> inventoryList.add(findByItemId(id))
        );

        return inventoryList;
    }

}
