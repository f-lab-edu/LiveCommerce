package com.flab.livecommerce.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticatedUser {

    public static final long EXPIRE_TIME = 1000L;
    private Long userId;
    private String email;
    private Role role;
    private Long expireAt;


    public AuthenticatedUser(Long userId, String email, Role role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.expireAt = EXPIRE_TIME;
    }
}
