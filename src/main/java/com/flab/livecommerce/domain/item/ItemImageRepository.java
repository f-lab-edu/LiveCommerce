package com.flab.livecommerce.domain.item;

import java.util.List;

public interface ItemImageRepository {

    void save(ItemImage itemImage);

    void deleteById(Long id);

    void deleteAll(Long id, List<Integer> orderList);

    void updateOrdering(Long itemId, List<Integer> orderList);
}
