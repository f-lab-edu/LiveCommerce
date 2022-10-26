package com.flab.livecommerce.domain.shop;

import lombok.Getter;

@Getter
public class Shop {

    private Long id;
    private String name;
    private String businessNo;
    private String email;

    public Shop(String name, String businessNo, String email) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
    }

    public Shop setId(Long id) {
        this.id = id;
        return this;
    }
}
