package com.flab.livecommerce.item.infrastructure.item.persistence;

<<<<<<< HEAD:toMove/infrastructure/item/persistence/ItemRepositoryAdapter.java
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemRepository;
=======
import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.item.domain.ItemOptionGroup;
import com.flab.livecommerce.item.domain.ItemRepository;
import com.flab.livecommerce.item.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemRepository;
import java.util.List;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/infrastructure/item/persistence/ItemRepositoryAdapter.java
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryAdapter implements ItemRepository {

    private final JdbcTemplateItemRepository itemRepository;

    public ItemRepositoryAdapter(
        JdbcTemplateItemRepository itemRepository
    ) {
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
    public void deleteById(Long id) {
        this.itemRepository.deleteById(id);
    }

    @Override
    public Item update(Item item, Long id) {
        return this.itemRepository.update(item, id);
    }
}
