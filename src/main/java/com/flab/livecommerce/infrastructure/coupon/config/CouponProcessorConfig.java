package com.flab.livecommerce.infrastructure.coupon.config;

import com.flab.livecommerce.application.coupon.GetCouponsProcessor;
import com.flab.livecommerce.domain.coupon.CouponRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouponProcessorConfig {

    @Bean
    public GetCouponsProcessor getCouponsProcessor(
        CouponRepository couponRepository
    ) {
        return new GetCouponsProcessor(couponRepository);
    }
}
