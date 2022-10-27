package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemOption {

    private Long id;
    private Long itemOptionGroupId;
    private Long itemId;
    private String name;
    private Integer ordering;
    private Long price;


    protected ItemOption() {
    }

    @Builder
    public ItemOption(Long itemOptionGroupId, String name, Integer ordering, Long price, Long itemId) {
        if (itemOptionGroupId == null) {
            throw new InvalidParameterException("ItemOption.itemOptionGroupId");
        }
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("ItemOptionGroup.name");
        }
        if (ordering == null) {
            throw new InvalidParameterException("ItemOptionGroup.ordering");
        }
        if (price == null) {
            throw new InvalidParameterException("ItemOptionGroup.price");
        }

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
