package com.flab.livecommerce.domain.item;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    List<ItemOptionGroup> findItemOptionSeries(Item item);
}
