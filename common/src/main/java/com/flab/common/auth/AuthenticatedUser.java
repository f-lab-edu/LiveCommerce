package com.flab.common.auth;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class AuthenticatedUser {

    private String token;
    private Long userId;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

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

}
