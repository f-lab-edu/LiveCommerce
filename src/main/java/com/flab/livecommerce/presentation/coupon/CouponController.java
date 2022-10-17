package com.flab.livecommerce.presentation.coupon;

import com.flab.livecommerce.application.coupon.facade.CouponManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vi/coupon-event")
@RestController
public class CouponController {

    private final CouponManager couponManager;

    public CouponController(CouponManager couponManager) {
        this.couponManager = couponManager;
    }

    // 쿠폰 페이지 목록
    @GetMapping
    public CommonApiResponse getEventCoupons() {
        var coupons = couponManager.getEventCoupons();
        return CommonApiResponse.success(coupons);
    }

    // 쿠폰 받기
}
