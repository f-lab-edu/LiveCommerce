package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.DiscountCondition;
import com.flab.livecommerce.domain.order.OrderLineItem;

/*
 * 특정 기간 안에 구매시 쿠폰 적용 조건
 */
public class PeriodCondition implements DiscountCondition {

    @Override
    public boolean isSatisfiedBy(OrderLineItem orderLineItem) {
        return false;
    }
}
