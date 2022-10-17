package com.flab.livecommerce.infrastructure.coupon.persistence.mybatis;

import com.flab.livecommerce.domain.coupon.Coupon;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {

    List<Coupon> findAll();
}
