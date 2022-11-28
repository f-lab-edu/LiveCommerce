package com.flab.user.application.command;

import com.flab.user.domain.User;

public class CreateUserCommand {

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

    public CreateUserCommand(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
