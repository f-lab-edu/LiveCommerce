package com.flab.livecommerce.infrastructure.coupon.persistence;

import com.flab.livecommerce.domain.coupon.Coupon;
import com.flab.livecommerce.domain.coupon.CouponRepository;
import com.flab.livecommerce.infrastructure.coupon.persistence.mybatis.CouponMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepositoryAdaptor implements CouponRepository {

    private final CouponMapper couponMapper;

    public CouponRepositoryAdaptor(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponMapper.findAll();
    }
}
