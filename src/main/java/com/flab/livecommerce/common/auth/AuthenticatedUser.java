package com.flab.livecommerce.common.auth;

import com.flab.livecommerce.common.Role;
import com.flab.livecommerce.domain.user.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticatedUser {

    private String token;
    private Long userId;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

    public AuthenticatedUser(String token, Long userId, String email, Role role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public AuthenticatedUser plusExpireTime(long second) {
        this.expireAt = LocalDateTime.now().plusSeconds(second);
        return this;
    }

    public static AuthenticatedUser create(User user, String token) {
        return new AuthenticatedUser(
            token,
            user.getId(),
            user.getEmail(),
            user.getRole()
        );
    }
}
