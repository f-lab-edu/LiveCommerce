package com.flab.order.presentation.response;

import com.flab.order.application.result.OrderResult;

public final class OrderResponse {

    private Long orderId;
    private Long userId;
    private Integer totalAmount;

    private OrderResponse() {
    }

    public OrderResponse(Long orderId, Long userId, Integer totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    public static OrderResponse form(OrderResult orderResult) {
        return new OrderResponse(
            orderResult.getOrderId(),
            orderResult.getUserId(),
            orderResult.getTotalAmount()
        );
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
