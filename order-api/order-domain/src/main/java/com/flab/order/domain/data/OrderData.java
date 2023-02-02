package com.flab.order.domain.data;

public final class OrderData {

    private Long orderId;
    private Long userId;
    private Integer totalAmount;

    public OrderData(Long orderId, Long userId, Integer totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }
}
