package com.flab.livecommerce.domain.coupon;

import com.flab.livecommerce.domain.order.OrderLineItem;

public class PercentDiscountPolicy implements DiscountPolicy {

    private double percent;

    public PercentDiscountPolicy(double percent) {
        this.percent = percent;
    }

    @Override
    public Long calculateDiscountAmount(OrderLineItem orderLineItem) {
        double doublePrice = (orderLineItem.getPrice() * percent) * orderLineItem.getOrderCount();
        return (long) doublePrice;
    }
}
