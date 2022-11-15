package com.flab.livecommerce.domain.image;

import java.util.List;

public interface ItemImageRepository {

    ItemImage save(ItemImage itemImage);

    void deleteById(Long id);

    void updateOrdering(Long imageId, List<Integer> orderList);

    void updateOrder(Long imageId, Integer order);
}
