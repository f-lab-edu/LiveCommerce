package com.flab.livecommerce.infrastructure.coupon.condition;

import com.flab.livecommerce.domain.coupon.DiscountCondition;
import com.flab.livecommerce.domain.order.OrderLineItem;

/*
 * n원 이상 주문시 적용 조건
 */
public class OrderItemPriceCondition implements DiscountCondition {

    @Override
    public boolean isSatisfiedBy(OrderLineItem orderLineItem) {
        return false;
    }
}
