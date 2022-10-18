package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.DiscountCondition;

/*
 * 특정 상품 조건
 */
public class ItemCondition implements DiscountCondition {

    @Override
    public boolean isSatisfied(Coupon coupon) {
        return false;
    }
}
