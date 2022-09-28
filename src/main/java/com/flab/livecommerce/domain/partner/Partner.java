package com.flab.livecommerce.domain.partner;

import lombok.Getter;

@Getter
public class Partner {

    private Long id;
    private String name;
    private String businessNo;
    private String email;

    public Partner(String name, String businessNo, String email) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
    }

    public Partner setId(Long id) {
        this.id = id;
        return this;
    }
}
