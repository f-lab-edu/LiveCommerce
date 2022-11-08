package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateItemOptionCommand {

    private Long id;
    private Long itemOptionGroupId;
    private Long itemId;
    private String name;
    private Integer ordering;
    private Long price;

    public ItemOption toEntity(ItemOptionGroup itemOptionGroup) {
        var itemOption = ItemOption.builder()
            //.itemOptionGroupId(itemOptionGroup.getId())
            .name(name)
            .ordering(ordering)
            .price(price)
            .build();
        itemOption.setItem(itemOptionGroup.getItem());
        return itemOption.setId(id);
    }
}
