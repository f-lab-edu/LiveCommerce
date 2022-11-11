package com.flab.user.presentation.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class UserEmailRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰 주세요.")
    private String email;

    protected UserEmailRequest() {
    }

    public UserEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
