package com.flab.livecommerce.application.user.command;

import com.flab.livecommerce.domain.user.User;
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
