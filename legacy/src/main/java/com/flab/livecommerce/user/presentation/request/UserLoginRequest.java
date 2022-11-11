package com.flab.livecommerce.user.presentation.request;

import com.flab.livecommerce.user.application.UserLoginProcessor.LoginCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰 주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^[0-9a-z].{6,10}$", message = "영문 소문자, 숫자 6~10자 이내로 입력하세요.”")
    private String password;

    public LoginCommand toCommand() {
        return new LoginCommand(email, password);
    }

}

