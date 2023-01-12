package com.flab.item.domain;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);
}
