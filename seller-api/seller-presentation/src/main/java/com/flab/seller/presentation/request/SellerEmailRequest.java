package com.flab.seller.presentation.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SellerEmailRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰 주세요.")
    private String email;

    public String getEmail() {
        return email;
    }
}
