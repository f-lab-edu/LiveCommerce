package com.flab.livecommerce.infrastructure.item.persistence;

import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate.JdbcTemplateItemImageRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ItemImageRepositoryAdapter implements ItemImageRepository {
    private final JdbcTemplateItemImageRepository itemImageRepository;

    public ItemImageRepositoryAdapter(JdbcTemplateItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public void saveAll(List<ItemImage> imageList) {
        itemImageRepository.saveAll(imageList);
    }

    @Override
    public void save(ItemImage thumbnail) {
        itemImageRepository.save(thumbnail);
    }
}
