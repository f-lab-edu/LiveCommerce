package com.flab.livecommerce.user.application.command;

import com.flab.livecommerce.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateCommand {

    private String email;
    private String password;
    private String nickname;

    public User toEntity(String encryptedPassword) {
        return new User(
            this.email,
            encryptedPassword,
            this.nickname
        );
    }
}
