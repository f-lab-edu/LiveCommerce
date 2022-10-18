package com.flab.livecommerce.domain.coupon;

import com.flab.livecommerce.domain.order.OrderLineItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 적용된 할인 조건을 모두 만족해야 쿠폰이 적용되며, 할인된 금액을 적용한다.
 */
public abstract class DefaultDiscountPolicy implements DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList<>();

    public DefaultDiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public Long calculateDiscountAmount(OrderLineItem orderLineItem) {
        for (DiscountCondition each : conditions) {
            if (!each.isSatisfiedBy(orderLineItem)) {
                return (long) 0;
            }
        }
        return getDiscountAmount(orderLineItem);
    }

    protected abstract Long getDiscountAmount(OrderLineItem orderLineItem);
}
