package com.flab.livecommerce.item.application;

import com.flab.livecommerce.item.application.command.RegisterItemCommand;
import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.item.domain.ItemOptionSeriesService;
import com.flab.livecommerce.item.domain.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Item execute(RegisterItemCommand command) {
        var item = itemRepository.save(command.toEntity());
        itemOptionSeriesService.save(command, item);

        return item;
    }
}
