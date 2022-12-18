package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 * 포인트 생성 시점
 * 1. 상품 주문 시 1% 적립 포인트 2. 이벤트 포인트 3. 충전
 *
 * 기업 부채로서 작용되는 포인트 이력을 정확하고 상세하게 관리하기 위해 update와 delete가 없는 도메인 모델로 구성
 * -> PointTransaction에서 포인트별 사용 및 차감 상세 관리
 */
public class Point {

    private Long id;
    private Long userId;
    private PointCategory pointCategory;
    private Long amount;
    private PointStatus pointStatus;
    private LocalDateTime registerAt;

    // TODO 추후 주문 상품별, 이벤트별 만료 기간 설정. 현재는 적립시 적립 일자 + 한달로 설정
    private LocalDateTime expireAt;


    public Point() {
    }

    public Point(
            Long userId,
            PointCategory pointCategory,
            Long amount,
            PointStatus pointStatus
    ) {
        this.userId = userId;
        this.pointCategory = pointCategory;
        this.amount = amount;
        this.pointStatus = pointStatus;
        this.registerAt = LocalDateTime.now();
        this.expireAt = setExpireDate();
    }

    // 추가 (적립, 충전)
    public static Point add(
            Long userId,
            PointCategory pointCategory,
            Long amount
    ) {
        var addedPoint = new Point(userId, pointCategory, amount, PointStatus.POINT_EARN);
        PointTransaction.add(addedPoint);

        return addedPoint;
    }

    // 차감 (사용 시 차감)
    public void remove(
            Long userId,
            PointCategory pointCategory,
            Long amount
    ) {
        var removedPoint = new Point(userId, pointCategory, amount, PointStatus.POINT_USED);
        PointTransaction.remove(removedPoint);
    }

    private LocalDateTime setExpireDate() {
        if (this.pointStatus == PointStatus.POINT_EARN) {
            return LocalDateTime.now().plusMonths(1);
        } else {
            return this.expireAt;
        }
    }


    public enum PointCategory {
        ORDER("주문 포인트"),
        EVENT("이벤트 제공 포인트"),
        CHARGE("사용자 충전 포인트");
        private final String description;

        PointCategory(String description) {
            this.description = description;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public PointCategory getPointCategory() {
        return pointCategory;
    }

    public Long getAmount() {
        return amount;
    }

    public PointStatus getPointStatus() {
        return pointStatus;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }
}
