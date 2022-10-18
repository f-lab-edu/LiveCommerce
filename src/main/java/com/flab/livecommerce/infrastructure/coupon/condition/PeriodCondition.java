package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.DiscountCondition;

/*
 * 특정 기간 안에 구매시 쿠폰 적용 조건
 */
public class PeriodCondition implements DiscountCondition {

    @Override
    public boolean isSatisfied(Coupon coupon) {
        return false;
    }
}
