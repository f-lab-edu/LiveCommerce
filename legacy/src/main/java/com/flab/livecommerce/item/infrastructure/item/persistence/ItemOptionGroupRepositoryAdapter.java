package com.flab.livecommerce.item.infrastructure.item.persistence;

import com.flab.livecommerce.item.domain.ItemOptionGroup;
import com.flab.livecommerce.item.domain.ItemOptionGroupRepository;
import com.flab.livecommerce.item.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemOptionGroupRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOptionGroupRepositoryAdapter implements ItemOptionGroupRepository {

    private final JdbcTemplateItemOptionGroupRepository itemOptionGroupRepository;

    public ItemOptionGroupRepositoryAdapter(
        JdbcTemplateItemOptionGroupRepository itemOptionGroupRepository) {
        this.itemOptionGroupRepository = itemOptionGroupRepository;
    }

    @Override
    public ItemOptionGroup save(ItemOptionGroup itemOptionGroup) {
        return itemOptionGroupRepository.save(itemOptionGroup);
    }


    @Override
    public ItemOptionGroup update(ItemOptionGroup itemOptionGroup, Long id) {
        return itemOptionGroupRepository.update(itemOptionGroup, id);
    }


    @Override
    public ItemOptionGroup findById(Long itemOptionGroupId) {
        return itemOptionGroupRepository.findById(itemOptionGroupId);
    }
}
