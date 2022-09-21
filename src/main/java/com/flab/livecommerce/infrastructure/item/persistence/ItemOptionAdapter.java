package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.infrastructure.item.persistence.inmemory.InMemoryItemOptionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOptionAdapter implements ItemOptionRepository {

    private final InMemoryItemOptionRepository itemOptionRepository;

    public ItemOptionAdapter(InMemoryItemOptionRepository itemOptionRepository) {
        this.itemOptionRepository = itemOptionRepository;
    }

    @Override
    public ItemOption save(ItemOption itemOption) {
        return itemOptionRepository.save(itemOption);
    }
}
