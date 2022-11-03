package com.flab.livecommerce.presentation.seller.request;

import com.flab.livecommerce.application.seller.command.RegisterSellerCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterSellerRequest {

    @NotBlank(message = "판매자 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "사업자 번호는 필수입니다.")
    private String businessNo;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰 주세요.")
    private String email;

    public RegisterSellerCommand toCommand() {
        return new RegisterSellerCommand(
            name,
            businessNo,
            email
        );
    }
}
