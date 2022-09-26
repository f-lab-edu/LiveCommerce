package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.infrastructure.item.persistence.inmemory.InMemoryItemOptionGroupRepository;
import com.flab.livecommerce.infrastructure.item.persistence.mysql.MysqlItemOptionGroupRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOptionGroupRepositoryAdapter implements ItemOptionGroupRepository {

    private final MysqlItemOptionGroupRepository itemOptionGroupRepository;

    public ItemOptionGroupRepositoryAdapter(MysqlItemOptionGroupRepository itemOptionGroupRepository) {
        this.itemOptionGroupRepository = itemOptionGroupRepository;
    }

    @Override
    public ItemOptionGroup save(ItemOptionGroup itemOptionGroup) {
        return itemOptionGroupRepository.save(itemOptionGroup);
    }
}
