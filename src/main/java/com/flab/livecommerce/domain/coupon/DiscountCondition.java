package com.flab.livecommerce.domain.coupon;

public interface DiscountCondition {

    boolean isSatisfied(Coupon coupon); //
}
