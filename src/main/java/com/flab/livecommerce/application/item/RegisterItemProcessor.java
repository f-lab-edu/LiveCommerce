package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RegisterItemProcessor {

    private final ItemRepository itemRepository;

    public RegisterItemProcessor(ItemRepository repository) {
        this.itemRepository = repository;
    }

    public Item execute(RegisterCommand command) {

        return itemRepository.save(
            Item.builder()
                .name(command.getName())
                .description(command.getDescription())
                .price(command.getPrice())
                .salesPrice(command.getSalesPrice())
                .stockQuantity(command.getStockQuantity())
                .modelNumber(command.getModelNumber())
                .build());
    }

    @Getter
    @AllArgsConstructor
    public static class RegisterCommand {

        private String name;
        private Integer price;
        private Integer salesPrice;
        private String description;
        private Integer stockQuantity;
        private int modelNumber;
        private List<ItemOptionGroup> itemOptionGroup;
    }
}
