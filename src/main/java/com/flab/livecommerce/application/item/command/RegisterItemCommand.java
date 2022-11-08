package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterItemCommand {

    private Long shopId;
    private String name;
    private Long price;
    private Long salesPrice;
    private String description;
    private Integer stockQuantity;

    private List<RegisterItemOptionGroupCommand> itemOptionGroup;

    public Item toEntity() {
        return Item.builder()
            .shopId(shopId)
            .name(name)
            .description(description)
            .price(price)
            .salesPrice(salesPrice)
            .stockQuantity(stockQuantity)
            .itemOptionGroups(toOptionGroup())
            .build();
    }

    public List<ItemOptionGroup> toOptionGroup() {
        return itemOptionGroup.stream().map(
            optionGroup -> ItemOptionGroup.builder()
                .name(optionGroup.getName())
                .ordering(optionGroup.getOrdering())
                .basic(optionGroup.isBasic())
                .exclusive(optionGroup.isExclusive())
                .minimumChoice(optionGroup.getMinimumChoice())
                .maximumChoice(optionGroup.getMaximumChoice())
                .itemOptions(optionGroup.toItemOptions()).build())
            .collect(Collectors.toList());
    }
}
