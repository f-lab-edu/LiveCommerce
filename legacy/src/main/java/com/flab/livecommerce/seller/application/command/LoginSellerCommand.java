package com.flab.seller.application.command;

public class LoginSellerCommand {

    private String email;
    private String password;

    public LoginSellerCommand(String email, String password) {
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
