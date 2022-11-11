package com.flab.seller.presentation.request;

import com.flab.seller.application.command.RegisterSellerCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

    protected RegisterSellerRequest() {
    }

    public RegisterSellerRequest(String name, String businessNo, String email) {
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
