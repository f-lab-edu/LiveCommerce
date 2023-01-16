package com.flab.point.presentation.request;

import com.flab.point.application.command.ChargePointCommand;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class ChargePointRequest {

    @NotBlank(message = "payMethod 를 작성하세요.")
    private String payMethod;

    @NotNull(message = "포인트 충전 금액을 작성하세요.")
    @Range(min = 1000, message = "포인트 충전 최소 금액은 1,000원입니다.")
    private Integer chargeAmount;

    public ChargePointRequest() {
    }

    public String getPayMethod() {
        return payMethod;
    }

    public Integer getChargeAmount() {
        return chargeAmount;
    }

    public ChargePointCommand toCommand() {
        return new ChargePointCommand(payMethod, chargeAmount);
    }
}
