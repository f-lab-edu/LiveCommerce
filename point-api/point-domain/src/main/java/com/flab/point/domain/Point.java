package com.flab.point.domain;

import java.time.LocalDateTime;

/*
 * 사용자별 총 포인트
 *
 * 포인트 생성 시점
 * 1. 상품 주문 시 적립 포인트 2. 이벤트 제공 포인트 3. 충전
 *
 * 포인트 차감 시점
 * 1. 포인트 사용 2. 만료 기간 이후 차감
 */

public class Point {

    private Long id;
    private Long userId;
    private Long amount;
    private LocalDateTime registerAt;
    private LocalDateTime expireAt;


    protected Point() {
    }

    public Point(
            Long userId,
            Long amount
    ) {
        this.userId = userId;
        this.amount = amount;
        this.registerAt = LocalDateTime.now();
        this.expireAt = setExpireDate();
    }

    // 추가 (적립, 충전)
    public void add(
            Long addedAmount,
            PointCategory pointCategory
    ) {
        this.amount += addedAmount;
        PointTransaction.add(this, amount, pointCategory);
    }

    // 포인트 사용
    public void remove(
            Long removedAmount
    ) {
        //Long removedAmount = PointTransaction.remove(this, amount);
        this.amount -= removedAmount;

        if (this.amount < 0) {
            throw new RuntimeException("가용 포인트를 넘어선 접근입니다."); // TODO exception 처리
        }
    }

    // TODO 추후 주문 상품별, 이벤트별 만료 기간 설정. 현재는 적립시 적립 일자 + 한달로 설정
    private LocalDateTime setExpireDate() {
        return LocalDateTime.now().plusMonths(1);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

}
