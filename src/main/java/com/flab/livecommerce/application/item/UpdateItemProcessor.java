package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.UpdateItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

public class UpdateItemProcessor {

    private final ItemRepository itemRepository;
    private final ItemOptionSeriesService itemOptionSeriesService;

    public UpdateItemProcessor(
        ItemRepository itemRepository,
        ItemOptionSeriesService itemOptionSeriesService
    ) {
        this.itemRepository = itemRepository;
        this.itemOptionSeriesService = itemOptionSeriesService;
    }

    @Transactional
    public void execute(UpdateItemCommand command, Long id) {
        Item updatedItem = itemRepository.update(command.toEntity(), id);
        itemOptionSeriesService.update(command, updatedItem);
    }
}
