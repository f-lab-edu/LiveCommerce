package com.flab.inventory.infrastructure.persistence;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.infrastructure.persistence.jpa.JpaInventoryRepository;

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
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Inventory findByItemId(Long id) {
        return inventoryRepository.findByItemId(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

}
