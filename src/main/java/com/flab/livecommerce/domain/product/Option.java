package com.flab.livecommerce.domain.product;

import lombok.Getter;

@Getter
public class Option {

    private Long id;
    private String name;
    private int price;

    public Option(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
