package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 *  포인트 '차감' 시 PointTransaction에서 포인트를 적립 순서와 만료기간을 고려하여 차감할 수 있도록 함 -> '적립'된 포인트를 따로 관리하고 차감
 */
public class PointTransaction {

    private Long id;
    private Long userId;
    private Long pointId;
    private PointCategory pointCategory;
    private Long amount;
    private LocalDateTime transactAt;
    private LocalDateTime pointExpireAt;
    private boolean status; // 포인트가 현재 사용 가능한지 여부 - 상태값 필드

    protected PointTransaction() {
    }

    public PointTransaction(
            Long userId,
            Long pointId,
            PointCategory pointCategory,
            Long amount,
            LocalDateTime pointExpireAt
    ) {
        this.userId = userId;
        this.pointId = pointId;
        this.pointCategory = pointCategory;
        this.amount = amount;
        this.transactAt = LocalDateTime.now();
        this.pointExpireAt = pointExpireAt;
        this.status = true;
    }

    public static void add(Point point, Long amount, PointCategory pointCategory) {
        new PointTransaction(point.getUserId(), point.getId(), pointCategory, amount, point.getExpireAt());
    }

}
