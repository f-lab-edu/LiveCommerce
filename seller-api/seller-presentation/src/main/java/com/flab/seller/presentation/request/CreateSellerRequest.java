package com.flab.seller.presentation.request;

import com.flab.seller.application.command.CreateSellerCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CreateSellerRequest {

    @NotBlank(message = "판매자 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "사업자 번호는 필수입니다.")
    private String businessNo;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰 주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^[0-9a-z].{6,10}$", message = "영문 소문자, 숫자 6~10자 이내로 입력하세요.”")
    private String password;

    public CreateSellerCommand toCommand() {
        return new CreateSellerCommand(
            name,
            businessNo,
            email,
            password
        );
    }

    protected CreateSellerRequest() {
    }

    public CreateSellerRequest(String name, String businessNo, String email) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public String getEmail() {
        return email;
    }

}
