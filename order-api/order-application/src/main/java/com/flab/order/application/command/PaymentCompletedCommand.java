package com.flab.order.application.command;

public class PaymentCompletedCommand {

    private Long orderId;
    private Integer payedAmount;

    public PaymentCompletedCommand(Long orderId, Integer payedAmount) {
        this.orderId = orderId;
        this.payedAmount = payedAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getPayedAmount() {
        return payedAmount;
    }
}
