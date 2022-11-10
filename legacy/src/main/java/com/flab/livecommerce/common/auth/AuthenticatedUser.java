package com.flab.livecommerce.common.auth;

import java.time.LocalDateTime;

public class AuthenticatedUser {

    private String token;
    private Long userId;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

    protected AuthenticatedUser() {
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

    public void addExpirationSec(long second) {
        this.expireAt = expireAt.plusSeconds(second);
    }

    public static AuthenticatedUser create(
        String token,
        Long id,
        String email,
        Role role,
        long expirationSec
    ) {
        return new AuthenticatedUser(
            token,
            id,
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
