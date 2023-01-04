package com.flab.common.auth;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuthenticatedMember implements Serializable {

    private Long id;
    private String authId;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

    protected AuthenticatedMember() {
    }

    // Builder Pattern
    private AuthenticatedMember(Builder builder) {
        this.id = builder.id;
        this.authId = builder.authId;
        this.email = builder.email;
        this.role = builder.role;
        this.expireAt = builder.expireAt;
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

    public static class Builder {
        private Long id;
        private String authId;
        private String email;
        private Role role;
        private LocalDateTime expireAt;

        public Builder(Long id) {
            this.id = id;
        }

        public Builder setAuthId(String authId) {
            this.authId = authId;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setexpireAt(LocalDateTime expireAt) {
            this.expireAt = expireAt;
            return this;
        }

        public AuthenticatedMember build() {
            return new AuthenticatedMember(this);
        }
    }
}
