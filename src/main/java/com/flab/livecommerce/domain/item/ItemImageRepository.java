package com.flab.livecommerce.domain.item;

import java.util.List;

public interface ItemImageRepository {

    void saveAll(List<ItemImage> imageList);

    void save(ItemImage itemImage);
}
