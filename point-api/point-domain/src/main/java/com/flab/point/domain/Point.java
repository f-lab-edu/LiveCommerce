package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 * 포인트 생성 시점
 * 1. 상품 주문 시 1% 적립 포인트 2. 이벤트 포인트 3. 충전
 */

public class Point {

    private Long id;
    private Long userId;
    private PointCategory pointCategory;
    private Long amount;
    private LocalDateTime registerAt;

    // TODO 추후 주문 상품별, 이벤트별 만료 기간 설정. 현재는 적립시 적립 일자 + 한달로 설정
    private LocalDateTime expireAt;


    public Point() {
    }

    public Point(
            Long userId,
            PointCategory pointCategory,
            Long amount
    ) {
        this.userId = userId;
        this.pointCategory = pointCategory;
        this.amount = amount;
        this.registerAt = LocalDateTime.now();
        this.expireAt = setExpireDate();
    }

    // 추가 (적립, 충전)
    public void add(
            PointCategory pointCategory,
            Long amount
    ) {
        this.pointCategory = pointCategory;
        this.amount += amount;
        PointTransaction.add(this, amount);
    }

    // 차감 (사용 시 차감)
    public void remove(
            Long amount
    ) {
        Long removedAmount = PointTransaction.remove(this, amount);
        this.amount -= removedAmount;

        if (this.amount < 0) {
            throw new RuntimeException("가용 포인트를 넘어선 접근입니다."); //
        }
    }

    private LocalDateTime setExpireDate() {
        return LocalDateTime.now().plusMonths(1);
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

    public LocalDateTime getExpireAt() {
        return expireAt;
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
}
