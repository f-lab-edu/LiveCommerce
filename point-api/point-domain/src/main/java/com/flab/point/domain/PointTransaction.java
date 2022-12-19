package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 *  기업 부채로서 작용되는 포인트 이력을 정확하고 상세하게 관리하기 위해 -> PointTransaction에서 포인트별 사용 및 차감 상세 이력 관리
 */
public class PointTransaction {

    private Long id;
    private Long userId;
    private Long pointId;
    private Long amount;
    private PointStatus pointStatus;
    private LocalDateTime transactAt;
    private LocalDateTime pointExpireAt;

    public PointTransaction() {
    }

    public PointTransaction(
            Long userId,
            Long pointId,
            Long amount,
            PointStatus pointStatus,
            LocalDateTime pointExpireAt
    ) {
        this.userId = userId;
        this.pointId = pointId;
        this.amount = amount;
        this.pointStatus = pointStatus;
        this.transactAt = LocalDateTime.now();
        this.pointExpireAt = pointExpireAt;
    }

    public static void add(Point addedPoint, Long amount) {
        new PointTransaction(addedPoint.getUserId(), addedPoint.getId(), amount, PointStatus.POINT_EARN, addedPoint.getExpireAt());
    }

    public static Long remove(Point removedPoint, Long amount) {
        new PointTransaction(removedPoint.getUserId(), removedPoint.getId(), amount, PointStatus.POINT_USED, removedPoint.getExpireAt());
        // TODO 만료기간 이내 && 적립 순서대로 포인트 차감
        return amount; // TODO 차감 후 남은 point 반환
    }

    // TODO 만료 처리 (spring batch)

    public enum PointStatus {
        POINT_EARN("포인트 적립"),
        POINT_USED("포인트 사용"),
        POINT_EXPIRED("포인트 유효기간 만료");
        private final String description;

        PointStatus(String description) {
            this.description = description;
        }
    }
}
