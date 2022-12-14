package com.flab.payment.application.command;

public class PaymentFakeCommand {

    private Long orderId;

    private Integer totalAmount;

    public PaymentFakeCommand(Long orderId, Integer totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }
}
