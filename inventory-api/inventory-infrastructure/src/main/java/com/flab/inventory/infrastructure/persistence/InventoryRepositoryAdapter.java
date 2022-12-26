package com.flab.inventory.infrastructure.persistence;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.infrastructure.persistence.jpa.JpaInventoryRepository;
import java.util.List;

public class InventoryRepositoryAdapter implements InventoryRepository {

    private final JpaInventoryRepository inventoryRepository;

    public InventoryRepositoryAdapter(JpaInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> saveAll(List<Inventory> inventories) {
        return inventoryRepository.saveAll(inventories);
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Inventory findByItemId(Long id) {
        return inventoryRepository.findByItemId(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Inventory> findAllByItemId(Iterable<Long> itemIds) {
        return inventoryRepository.findAllByItemId(itemIds);
    }

}
