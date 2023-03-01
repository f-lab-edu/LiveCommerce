package com.flab.common.auth;

import java.time.LocalDateTime;

public final class AuthenticatedUser {

    private String token;
    private Long userId;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

    private AuthenticatedUser() {
    }

    public AuthenticatedUser(
        String token,
        Long userId,
        String email,
        Role role,
        LocalDateTime expireAt
    ) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.expireAt = expireAt;
    }

    public void plusSecondExpiration(long second) {
        this.expireAt = expireAt.plusSeconds(second);
    }

    public static AuthenticatedUser create(
        String token,
        Long userId,
        String email,
        Role role,
        long expirationSec
    ) {
        return new AuthenticatedUser(
            token,
            userId,
            email,
            role,
            LocalDateTime.now().plusSeconds(expirationSec)
        );
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }
}
