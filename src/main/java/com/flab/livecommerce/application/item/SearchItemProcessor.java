package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.image.FileUriGenerator;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.Item.Info;
import com.flab.livecommerce.domain.item.ItemRepository;

public class SearchItemProcessor {

    private final ItemRepository itemRepository;
    private final FileUriGenerator fileUriGenerator;

    public SearchItemProcessor(
        ItemRepository itemRepository,
        FileUriGenerator fileUriGenerator
    ) {
        this.itemRepository = itemRepository;
        this.fileUriGenerator = fileUriGenerator;
    }

    public Info execute(Long id) {
        Item item = itemRepository.findById(id);
        String uriPrefix = fileUriGenerator.getUriPrefix();
        return new Info(item, uriPrefix);
    }
}
