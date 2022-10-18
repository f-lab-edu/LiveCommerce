package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.DiscountCondition;

/*
 * n원 이상 주문시 적용 조건
 */
public class OrderItemPriceCondition implements DiscountCondition {

    @Override
    public boolean isSatisfied(Coupon coupon) {
        return false;
    }
}
