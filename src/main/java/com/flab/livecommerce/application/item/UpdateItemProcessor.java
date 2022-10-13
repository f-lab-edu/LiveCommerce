package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.RegisterItemCommand;
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
    public void execute(RegisterItemCommand command, Long id) {
        itemRepository.update(command.toEntity(),id);
        itemOptionSeriesService.update(command, id);
    }
}
