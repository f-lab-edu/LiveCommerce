package com.flab.item.domain;

import java.util.List;

public interface ItemImageRepository {

    void saveAll(List<ItemImage> imageList);

    void save(ItemImage thumbnail);
}
