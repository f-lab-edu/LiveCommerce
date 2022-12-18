package com.flab.payment.presentation.request;

import com.flab.payment.application.command.PaymentFakeCommand;
import javax.validation.constraints.NotNull;

public class PaymentFakeRequest {

    @NotNull(message = "orderId 를 작성하세요.")
    private Long orderId;

    @NotNull(message = "totalAmount 를 작성하세요.")
    private Integer totalAmount;

    protected PaymentFakeRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public PaymentFakeCommand toCommand() {
        return new PaymentFakeCommand(this.orderId, this.totalAmount);
    }
}
