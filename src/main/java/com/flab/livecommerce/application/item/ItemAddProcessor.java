package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.presentation.item.request.ItemRequest;

public class ItemAddProcessor {

    private final ItemRepository itemRepository;

    public ItemAddProcessor(ItemRepository repository) {
        this.itemRepository = repository;
    }

    public void execute(ItemRequest requestDto) {
        itemRepository.save(
            Item.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .salesPrice(requestDto.getSalesPrice())
                .stockQuantity(requestDto.getStockQuantity())
                .modelNumber(requestDto.getModelNumber())
                .optionGroups(requestDto.getOptionGroups())
                .build());
    }
}
