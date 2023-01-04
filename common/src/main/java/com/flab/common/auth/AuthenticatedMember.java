package com.flab.common.auth;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuthenticatedMember implements Serializable {

    private String authId;
    private Long id;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

    protected AuthenticatedMember() {
    }

    public AuthenticatedMember(
            Long id,
            String email,
            Role role
    ) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public AuthenticatedMember(
            String authId,
            Long id,
            String email,
            Role role,
            LocalDateTime expireAt
    ) {
        this.authId = authId;
        this.id = id;
        this.email = email;
        this.role = role;
        this.expireAt = expireAt;
    }

    public static AuthenticatedMember create(
            String authId,
            Long id,
            String email,
            Role role,
            long expirationSec
    ) {
        return new AuthenticatedMember(
                authId,
                id,
                email,
                role,
                LocalDateTime.now().plusSeconds(expirationSec)
        );
    }

    public void addExpirationSec(long second) {
        this.expireAt = expireAt.plusSeconds(second);
    }

    public String getAuthId() {
        return authId;
    }

    public Long getId() {
        return id;
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

    public void addAuthInfo(String authId, Long sessionExpirationSec) {
        this.authId = authId;
        this.expireAt = LocalDateTime.now().plusSeconds(sessionExpirationSec);
    }
}
