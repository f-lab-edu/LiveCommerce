package com.flab.livecommerce.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemOption {

    private Long id;
    private Long itemOptionGroupId;
    private String name;
    private Integer ordering;
    private Long price;

    protected ItemOption() {
    }

    @Builder
    public ItemOption(Long itemOptionGroupId, String name, Integer ordering, Long price) {
        this.itemOptionGroupId = itemOptionGroupId;
        this.name = name;
        this.ordering = ordering;
        this.price = price;
    }
}
