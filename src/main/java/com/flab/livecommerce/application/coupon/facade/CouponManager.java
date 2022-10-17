package com.flab.livecommerce.application.coupon.facade;

import com.flab.livecommerce.application.coupon.GetCouponsProcessor;
import com.flab.livecommerce.domain.coupon.Coupon;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CouponManager {

    private final GetCouponsProcessor getCouponsProcessor;

    public CouponManager(GetCouponsProcessor getCouponsProcessor) {
        this.getCouponsProcessor = getCouponsProcessor;
    }

    public List<Coupon> getEventCoupons() {
        return getCouponsProcessor.execute();
    }
}
