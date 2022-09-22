package com.flab.livecommerce.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemOption {

    private Long id;
    private ItemOptionGroup itemOptionGroup;
    private String name;
    private Integer ordering;
    private Long price;

    protected ItemOption() {
    }

    @Builder
    public ItemOption(ItemOptionGroup itemOptionGroup, String name, Integer ordering, Long price) {
        this.itemOptionGroup = itemOptionGroup;
        this.name = name;
        this.ordering = ordering;
        this.price = price;
    }
}
