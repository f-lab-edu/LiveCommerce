package com.flab.livecommerce.infrastructure.coupon.policy;

import com.flab.livecommerce.domain.coupon.DefaultDiscountPolicy;
import com.flab.livecommerce.domain.order.OrderLineItem;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {

    private Long discountAmount;

    public AmountDiscountPolicy(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    protected Long getDiscountAmount(OrderLineItem orderLineItem) {
        return discountAmount * orderLineItem.getOrderCount();
    }

}
