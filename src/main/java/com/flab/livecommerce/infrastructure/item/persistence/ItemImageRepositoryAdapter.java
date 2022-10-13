package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemImageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemImageRepositoryAdapter implements ItemImageRepository {

    private final JdbcTemplateItemImageRepository itemImageRepository;

    public ItemImageRepositoryAdapter(JdbcTemplateItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public void save(ItemImage itemImage) {
        itemImageRepository.save(itemImage);
    }

    @Override
    public void deleteById(Long id) {
        itemImageRepository.deleteAllById(id);
    }
}
