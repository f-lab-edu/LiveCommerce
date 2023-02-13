package com.flab.order.application.result;

public final class OrderResult {

    private final Long orderId;
    private final Long userId;
    private final Integer totalAmount;

    public OrderResult(Long orderId, Long userId, Integer totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }
}
