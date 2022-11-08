package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;
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
        var item = Item.create(command.toEntity(), command.toOptionGroup());
        itemRepository.save(item);

        return item;
    }
}
