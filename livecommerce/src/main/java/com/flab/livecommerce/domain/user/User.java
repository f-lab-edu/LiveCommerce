package com.flab.livecommerce.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private String nickname;
}
