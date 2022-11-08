package com.flab.livecommerce.domain.item;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    void deleteById(Long id);

    Item update(Item item, Long id);

}
