package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.Item.Info;
import com.flab.livecommerce.domain.item.ItemRepository;

public class SearchItemProcessor {

    private final ItemRepository itemRepository;
    private final String uriPrefix;

    public SearchItemProcessor(
        ItemRepository itemRepository,
        String uriPrefix
    ) {
        this.itemRepository = itemRepository;
        this.uriPrefix = uriPrefix;
    }

    public Info execute(Long id) {
        Item item = itemRepository.findById(id);
        return new Info(item, uriPrefix);
    }
}
