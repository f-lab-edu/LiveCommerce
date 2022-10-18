package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.DiscountCondition;
import com.flab.livecommerce.domain.order.OrderLineItem;

/*
 * 특정 상품 조건
 */
public class ItemCondition implements DiscountCondition {

    @Override
    public boolean isSatisfiedBy(OrderLineItem orderLineItem) {
        return false;
    }
}
