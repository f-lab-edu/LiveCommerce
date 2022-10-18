package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.DiscountCondition;

/*
 * 특정 카테고리 조건
 */
public class CategoryCondition implements DiscountCondition {

    @Override
    public boolean isSatisfied(Coupon coupon) {
        return false;
    }
}
