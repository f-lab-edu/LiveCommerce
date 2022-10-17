package com.flab.livecommerce.application.coupon;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.CouponRepository;
import java.util.List;

public class GetCouponsProcessor {

    private final CouponRepository couponRepository;

    public GetCouponsProcessor(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<Coupon> execute() {
        List<Coupon> coupons = couponRepository.getAllCoupons();
        return coupons;
    }
}
