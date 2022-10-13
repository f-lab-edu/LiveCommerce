package com.flab.livecommerce.domain.item;

public interface ItemImageRepository {

    void save(ItemImage itemImage);

    void deleteById(Long id);
}
