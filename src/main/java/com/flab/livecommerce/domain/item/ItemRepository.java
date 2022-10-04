package com.flab.livecommerce.domain.item;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    void deleteById(Long id);

    void update(Item item, Long id);
}
