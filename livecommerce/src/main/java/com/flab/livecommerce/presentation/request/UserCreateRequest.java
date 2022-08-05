package com.flab.livecommerce.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequest {
    private String email;
    private String password;
    private String nickname;
}
