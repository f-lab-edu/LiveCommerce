package com.flab.livecommerce.domain.coupon;

import com.flab.livecommerce.domain.order.OrderLineItem;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/*
 * 하나의 쿠폰은 하나의 할인 정책, 한 개 '이상'의 할인 조건을 갖는다.
 * (정액 or 정률 정책 + 특정 카테고리/상품/n원 이상 주문/기간 한정 조건)
 */

@Getter
public class Coupon {

    private Long id;

    // 쿠폰 이름
    private String name;

    // 쿠폰 사용 만료 날짜
    private LocalDateTime expirationDate;

    // 할인 금액
    private Long discountPrice;

    // 상태
    private CouponStatus couponStatus;

    private DiscountPolicy discountPolicy;

    @Builder
    public Coupon(
        Long id,
        String name,
        LocalDateTime expirationDate,
        Long discountPrice,
        CouponStatus couponStatus,
        DiscountPolicy discountPolicy) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.discountPrice = discountPrice;
        this.couponStatus = CouponStatus.AVAILABLE;
        this.discountPolicy = discountPolicy;
    }

    public Long calculateDiscountPrice(OrderLineItem orderLineItem) {
        discountPrice = discountPolicy.calculateDiscountAmount(orderLineItem);
        return discountPrice;
    }

    public enum CouponStatus {
        AVAILABLE("쿠폰 사용 가능"), USED("쿠폰 사용 완료");
        private final String description;

        CouponStatus(String description) {
            this.description = description;
        }
    }
}
