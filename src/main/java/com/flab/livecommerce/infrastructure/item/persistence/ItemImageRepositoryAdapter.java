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
    public ItemImage save(ItemImage itemImage) {
        return this.itemImageRepository.save(itemImage);
    }

    @Override
    public void deleteById(Long id) {
        this.itemImageRepository.deleteAllById(id);
    }

    @Override
    public void deleteAll(Long itemId, List<Integer> orderList) {
        this.itemImageRepository.deleteAll(itemId, orderList);
    }

    @Override
    public void updateOrdering(Long imageId, List<Integer> orderList) {
        this.itemImageRepository.updateOrdering(imageId, orderList);
    }

    @Override
    public void updateOrder(Long imageId, Integer order) {
        this.itemImageRepository.updateOrder(imageId, order);
    }
}
