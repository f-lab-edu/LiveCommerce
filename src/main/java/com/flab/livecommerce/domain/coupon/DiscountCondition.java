package com.flab.livecommerce.domain.coupon;

import com.flab.livecommerce.domain.order.OrderLineItem;

public interface DiscountCondition {

    boolean isSatisfiedBy(OrderLineItem orderLineItem);
}
