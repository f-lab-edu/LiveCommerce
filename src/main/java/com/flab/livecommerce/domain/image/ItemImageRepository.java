package com.flab.livecommerce.domain.image;

public interface ItemImageRepository {

    ItemImage save(ItemImage itemImage);

    void deleteById(Long id);

    void updateOrdering(Long imageId, Integer order);
}
