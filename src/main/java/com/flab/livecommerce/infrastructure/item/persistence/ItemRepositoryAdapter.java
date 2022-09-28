package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemRepository;
import java.util.List;
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
        return this.itemRepository.findById(id);
    }

    @Override
    public List<ItemOptionGroup> findItemOptionSeries(Item item) {
        return this.itemRepository.findItemOptionSeries(item);
    }

}
