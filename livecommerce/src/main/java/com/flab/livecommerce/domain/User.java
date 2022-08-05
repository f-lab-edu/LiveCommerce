package com.flab.livecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private String nickname;
}
