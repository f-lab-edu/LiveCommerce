package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.ItemOption;
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

    public ItemOption toEntity(Long itemOptionGroupId) {
        return ItemOption.builder()
            .itemOptionGroupId(itemOptionGroupId)
            .ordering(ordering)
            .name(name)
            .price(price)
            .build();
    }
}
