package com.flab.livecommerce.domain.item;

import lombok.Getter;

@Getter
public class ItemOption {

    private Long id;
    private String name;
    private Integer ordering;
    private Long price;

    public ItemOption(Long id, String name, Integer ordering, Long price) {
        this.name = name;
        this.ordering = ordering;
        this.price = price;
    }
}
