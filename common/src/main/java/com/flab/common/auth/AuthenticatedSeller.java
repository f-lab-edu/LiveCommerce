package com.flab.common.auth;

import java.io.Serializable;

public class AuthenticatedSeller implements Serializable {
    private Long sellerId;
    private String email;
    private Role role;

    public AuthenticatedSeller() {
    }

    public AuthenticatedSeller(
            Long sellerId,
            String email,
            Role role
    ) {
        this.sellerId = sellerId;
        this.email = email;
        this.role = role;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
