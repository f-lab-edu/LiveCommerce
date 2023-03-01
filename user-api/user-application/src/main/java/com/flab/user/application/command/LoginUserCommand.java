package com.flab.user.application.command;

public final class LoginUserCommand {

    private String email;
    private String password;

    private LoginUserCommand() {
    }

    public LoginUserCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
