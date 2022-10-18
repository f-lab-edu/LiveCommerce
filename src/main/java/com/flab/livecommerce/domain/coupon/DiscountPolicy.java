package com.flab.livecommerce.domain.coupon;

import com.flab.livecommerce.domain.order.OrderLineItem;

public interface DiscountPolicy {
    Long calculateDiscountAmount(OrderLineItem orderLineItem); // TODO 연결지을 도메인 파라미터 고민 - 상품 or 주문상품

}
