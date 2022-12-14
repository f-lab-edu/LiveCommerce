package com.flab.livecommerce.item.application.command;

import com.flab.livecommerce.item.domain.ItemOption;
import com.flab.livecommerce.item.domain.ItemOptionGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterItemOptionCommand {

    private String name;
    private Integer ordering;
    private Long price;

    public ItemOption toEntity(ItemOptionGroup itemOptionGroup) {
        return ItemOption.builder()
            .itemOptionGroupId(itemOptionGroup.getId())
            .ordering(ordering)
            .name(name)
            .price(price)
            .itemId(itemOptionGroup.getItemId())
            .build();
    }
}
