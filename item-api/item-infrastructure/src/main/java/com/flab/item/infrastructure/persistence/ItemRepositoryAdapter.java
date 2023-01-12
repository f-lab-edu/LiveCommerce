package com.flab.item.infrastructure.persistence;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.item.domain.Item;
import com.flab.item.domain.ItemRepository;
import com.flab.item.infrastructure.persistence.jpa.JpaItemRepository;

public class ItemRepositoryAdapter implements ItemRepository {

    private final JpaItemRepository itemRepository;

    public ItemRepositoryAdapter(JpaItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
