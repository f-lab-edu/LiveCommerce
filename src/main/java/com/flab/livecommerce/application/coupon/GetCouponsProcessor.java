package com.flab.livecommerce.application.coupon;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.CouponRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GetCouponsProcessor {

    private final CouponRepository couponRepository;

    public GetCouponsProcessor(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<Coupon> execute() {
        List<Coupon> allCoupons = couponRepository.getAllCoupons();
        List<Coupon> coupons = allCoupons.stream()
            .filter(coupon -> coupon.getExpirationDate().isAfter(LocalDateTime.now()))
            .collect(Collectors.toList());
        return coupons;
    }
}
