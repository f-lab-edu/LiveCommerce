package com.flab.livecommerce.domain.seller;

import lombok.Getter;

@Getter
public class Seller {

    private Long id;
    private String name;
    private String businessNo;
    private String email;

    public Seller(String name, String businessNo, String email) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
    }

    public Seller setId(Long id) {
        this.id = id;
        return this;
    }
}
