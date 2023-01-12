package com.flab.item.application;

import com.flab.item.application.command.RegisterItemCommand;
import com.flab.item.domain.Item;
import com.flab.item.domain.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

public class RegisterItemProcessor {

    private final ItemRepository itemRepository;

    public RegisterItemProcessor(
        ItemRepository itemRepository
    ) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item execute(Long sellerId, RegisterItemCommand command) {
        var item = Item.create(
            sellerId,
            command.getName(),
            command.getDescription(),
            command.getPrice(),
            command.getSalesPrice(),
            command.toOptionGroups()
        );
        itemRepository.save(item);

        return item;
    }
}
