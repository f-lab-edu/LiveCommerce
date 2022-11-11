package com.flab.item.domain;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    List<ItemOptionGroup> findItemOptionSeries(Item item);
}
