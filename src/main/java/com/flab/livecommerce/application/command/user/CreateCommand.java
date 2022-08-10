package com.flab.livecommerce.application.command.user;

import com.flab.livecommerce.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommand {

    private String email;
    private String password;
    private String nickname;

    public User toUser() {
        return new User(email, password, nickname);
    }
}
