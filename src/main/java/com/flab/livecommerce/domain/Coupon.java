package com.flab.livecommerce.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/*
 * 상품 카테고리별 쿠폰 도메인
 */

@Getter
public class Coupon {

    private Long id;

    private Long categoryId;

    // 쿠폰 이름
    private String name;

    // 사용 기간
    private LocalDateTime endDate;

    // 할인 금액
    private Integer discountPrice;

    // 상태
    private CouponStatus couponStatus;

    public enum CouponStatus {
        DEFAULT("쿠폰 사용 전"), USED("쿠폰 사용 완료");
        private final String description;

        CouponStatus(String description) {
            this.description = description;
        }
    }

    @Builder
    public Coupon(
        Long id,
        Long categoryId,
        String name,
        LocalDateTime endDate,
        Integer discountPrice,
        CouponStatus couponStatus
    ) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.endDate = endDate;
        this.discountPrice = discountPrice;
        this.couponStatus = couponStatus;
    }
}
