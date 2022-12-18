package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 * 적립포인트 아이디(earnedPointId)를 기준으로 groupBy 해서 만료 기간 및 포인트 사용 처리
 */
public class PointTransaction {

    private Long id;
    private Long userId;
    private Long pointId;
    private Long earnedPointId;
    private PointStatus pointStatus;
    private Long amount;
    private LocalDateTime transactAt;
    private LocalDateTime expireAt;

    public PointTransaction() {
    }

    public PointTransaction(
            Long userId,
            Long pointId,
            Long earnedPointId,
            PointStatus pointStatus,
            Long amount
    ) {
        this.userId = userId;
        this.pointId = pointId;
        this.earnedPointId = earnedPointId;
        this.pointStatus = pointStatus;
        this.amount = amount;
        this.transactAt = LocalDateTime.now();
        // TODO this.expireAt = ;
    }

    public static PointTransaction add(Point addedPoint) {
        var addTransaction = new PointTransaction(addedPoint.getUserId(), addedPoint.getId(), addedPoint.getId(), addedPoint.getPointStatus(), addedPoint.getAmount());
        addTransaction.expireAt = addedPoint.getExpireAt();
        return addTransaction;
    }

    public static void remove(Point removedPoint) {
        // TODO 적립된 순서대로 적립 포인트 차감 -> HOW?
        // TODO 이후 남은 포인트 만료 기간 설정
    }

    // TODO 만료 처리
}
