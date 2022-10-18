package com.flab.livecommerce.infrastructure.coupon.policy;

import com.flab.livecommerce.domain.coupon.DefaultDiscountPolicy;
import com.flab.livecommerce.domain.order.OrderLineItem;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {

    private double percent;

    public PercentDiscountPolicy(double percent) {
        this.percent = percent;
    }


    @Override
    protected Long getDiscountAmount(OrderLineItem orderLineItem) {
        return (long) (percent * orderLineItem.getPrice() * orderLineItem.getOrderCount());
    }
}
