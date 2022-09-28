package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemOptionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOptionRepositoryAdapter implements ItemOptionRepository {

    private final JdbcTemplateItemOptionRepository itemOptionRepository;

    public ItemOptionRepositoryAdapter(JdbcTemplateItemOptionRepository itemOptionRepository) {
        this.itemOptionRepository = itemOptionRepository;
    }

    @Override
    public ItemOption save(ItemOption itemOption) {
        return itemOptionRepository.save(itemOption);
    }
}
