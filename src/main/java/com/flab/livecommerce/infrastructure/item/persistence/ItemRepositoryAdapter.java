package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryAdapter implements ItemRepository {

    private final JdbcTemplateItemRepository itemRepository;

    public ItemRepositoryAdapter(JdbcTemplateItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.itemRepository.deleteById(id);
    }

}
