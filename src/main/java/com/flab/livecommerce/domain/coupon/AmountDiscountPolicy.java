package com.flab.livecommerce.domain.coupon;

import com.flab.livecommerce.domain.order.OrderLineItem;

public class AmountDiscountPolicy implements DiscountPolicy {

    private Long discountAmount;

    public AmountDiscountPolicy(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Long calculateDiscountAmount(OrderLineItem orderLineItem) {
        return discountAmount * orderLineItem.getOrderCount();
    }
}
