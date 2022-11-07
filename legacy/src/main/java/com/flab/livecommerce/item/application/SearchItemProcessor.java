package com.flab.livecommerce.item.application;

import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.item.domain.ItemRepository;

public class SearchItemProcessor {

    private final ItemRepository itemRepository;

    public SearchItemProcessor(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(Long id) {
        return itemRepository.findById(id);
    }
}
