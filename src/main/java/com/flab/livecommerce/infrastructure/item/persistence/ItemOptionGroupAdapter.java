package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.infrastructure.item.persistence.inmemory.InMemoryItemOptionGroupRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOptionGroupAdapter implements ItemOptionGroupRepository {

    private final InMemoryItemOptionGroupRepository itemOptionGroupRepository;

    public ItemOptionGroupAdapter(InMemoryItemOptionGroupRepository itemOptionGroupRepository) {
        this.itemOptionGroupRepository = itemOptionGroupRepository;
    }

    @Override
    public ItemOptionGroup save(ItemOptionGroup itemOptionGroup) {
        return itemOptionGroupRepository.save(itemOptionGroup);
    }
}
