package com.flab.livecommerce.infrastructure.image.persistence;

import com.flab.livecommerce.domain.image.ItemImage;
import com.flab.livecommerce.domain.image.ItemImageRepository;
import com.flab.livecommerce.infrastructure.image.persistence.jdbctemplate.JdbcTemplateItemImageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemImageRepositoryAdapter implements ItemImageRepository {

    private final JdbcTemplateItemImageRepository itemImageRepository;

    public ItemImageRepositoryAdapter(JdbcTemplateItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public ItemImage save(ItemImage itemImage) {
        return this.itemImageRepository.save(itemImage);
    }

    @Override
    public void deleteById(Long id) {
        this.itemImageRepository.deleteAllById(id);
    }

    @Override
    public void updateOrdering(Long imageId, Integer order) {
        this.itemImageRepository.updateOrdering(imageId, order);
    }
}
