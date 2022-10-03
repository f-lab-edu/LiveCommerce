package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemRepository;
import java.util.List;

public class SearchItemProcessor {

    private final ItemRepository itemRepository;

    public SearchItemProcessor(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(Long id) {
        return itemRepository.findById(id);
    }
}
