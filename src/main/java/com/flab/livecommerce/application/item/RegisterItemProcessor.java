package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RegisterItemProcessor {

    private final ItemRepository itemRepository;
    private final ItemOptionGroupRepository itemOptionGroupRepository;
    private final ItemOptionRepository itemOptionRepository;

    public RegisterItemProcessor(
        ItemRepository itemRepository,
        ItemOptionGroupRepository itemOptionGroupRepository,
        ItemOptionRepository itemOptionRepository
    ) {
        this.itemRepository = itemRepository;
        this.itemOptionGroupRepository = itemOptionGroupRepository;
        this.itemOptionRepository = itemOptionRepository;
    }

    public Item execute(RegisterItemCommand command) {
        var item = itemRepository.save(command.toEntity());

        command.getItemOptionGroup().forEach(
            requestItemOptionGroup -> {
                var optionGroup = ItemOptionGroup.builder()
                    .itemId(item.getId())
                    .ordering(requestItemOptionGroup.getOrdering())
                    .name(requestItemOptionGroup.getName())
                    .basic(requestItemOptionGroup.isBasic())
                    .exclusive(requestItemOptionGroup.isExclusive())
                    .minimumChoice(requestItemOptionGroup.getMinimumChoice())
                    .maximumChoice(requestItemOptionGroup.getMaximumChoice())
                    .build();
                var itemOptionGroup = itemOptionGroupRepository.save(optionGroup);

                itemOptionGroup.getItemOptions().forEach(
                    requestItemOption -> {
                        var option = ItemOption.builder()
                            .itemOptionGroupId(itemOptionGroup.getItemId())
                            .name(requestItemOption.getName())
                            .ordering(requestItemOption.getOrdering())
                            .price(requestItemOption.getPrice())
                            .build();
                        itemOptionRepository.save(option);
                    }
                );
            }
        );

        return item;
    }

    @Getter
    @AllArgsConstructor
    public static class RegisterItemCommand {

        private String name;
        private Integer price;
        private Integer salesPrice;
        private String description;
        private Integer stockQuantity;
        private int modelNumber;
        private List<ItemOptionGroup> itemOptionGroup;

        public Item toEntity() {
            return Item.builder()
                .name(name)
                .description(description)
                .price(price)
                .salesPrice(salesPrice)
                .stockQuantity(stockQuantity)
                .modelNumber(modelNumber)
                .build();
        }
    }
}
