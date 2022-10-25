package com.flab.livecommerce.common;

import com.flab.livecommerce.domain.user.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticatedUser {

    private Long userId;
    private String email;
    private Role role;
    private LocalDateTime expireAt;


    public AuthenticatedUser(Long userId, String email, Role role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public AuthenticatedUser updateExpireTime(long time) {
        this.expireAt = LocalDateTime.now().plusSeconds(time);
        return this;
    }

    public static AuthenticatedUser create(User user) {
        return new AuthenticatedUser(
            user.getId(),
            user.getEmail(),
            user.getRole()
        );
    }
}
