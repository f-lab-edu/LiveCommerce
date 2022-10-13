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

    private Long itemId;

    protected ItemOption() {
    }

    @Builder
    public ItemOption(Long itemOptionGroupId, String name, Integer ordering, Long price, Long itemId) {
        this.itemOptionGroupId = itemOptionGroupId;
        this.name = name;
        this.ordering = ordering;
        this.price = price;
        this.itemId = itemId;
    }

    public ItemOption setId(Long id) {
        this.id = id;
        return this;
    }
}
