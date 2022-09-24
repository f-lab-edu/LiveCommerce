package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;

public class RegisterItemProcessor {

    private final ItemRepository itemRepository;
    private final ItemOptionSeriesService itemOptionSeriesService;

    public RegisterItemProcessor(
        ItemRepository itemRepository,
        ItemOptionSeriesService itemOptionSeriesService
    ) {
        this.itemRepository = itemRepository;
        this.itemOptionSeriesService = itemOptionSeriesService;
    }

    public Item execute(RegisterItemCommand command) {
        var item = itemRepository.save(command.toEntity());
        itemOptionSeriesService.save(command, item);

        return item;
    }
}
