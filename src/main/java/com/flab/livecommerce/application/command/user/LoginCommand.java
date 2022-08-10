package com.flab.livecommerce.application.command.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginCommand {

    private String email;
    private String password;
}
