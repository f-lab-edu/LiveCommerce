package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.infrastructure.item.persistence.inmemory.InMemoryItemOptionRepository;
import com.flab.livecommerce.infrastructure.item.persistence.mysql.MysqlItemOptionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOptionRepositoryAdapter implements ItemOptionRepository {

    private final MysqlItemOptionRepository itemOptionRepository;

    public ItemOptionRepositoryAdapter(MysqlItemOptionRepository itemOptionRepository) {
        this.itemOptionRepository = itemOptionRepository;
    }

    @Override
    public ItemOption save(ItemOption itemOption) {
        return itemOptionRepository.save(itemOption);
    }
}
