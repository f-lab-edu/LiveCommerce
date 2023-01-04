package com.flab.point.domain;

import com.flab.point.domain.exception.ReducePointException;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 사용자별 총 포인트
 *
 * 포인트 생성 시점
 * 1. 상품 주문 시 적립 포인트 2. 이벤트 제공 포인트 3. 충전
 *
 * 포인트 차감 시점
 * 1. 포인트 사용 2. 만료 기간 이후 차감
 */

@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long totalAmount;
    private LocalDateTime registerAt;
    private LocalDateTime updatedAt;


    protected Point() {
    }

    public Point(
            Long userId,
            Long amount
    ) {
        this.userId = userId;
        this.totalAmount = amount;
        this.registerAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 추가 (적립, 충전)
    public void add(
            Long addedAmount,
            PointCategory pointCategory
    ) {
        this.totalAmount += addedAmount;
        this.updatedAt = LocalDateTime.now();
        PointTransaction.add(this, totalAmount, pointCategory);
    }

    // 포인트 사용
    public void reduce(
            Long reducedAmount
    ) {
        if (this.totalAmount - reducedAmount < 0) {
            throw new ReducePointException();
        }

        PointTransaction.reduce(reducedAmount);

        this.totalAmount -= reducedAmount;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAmount() {
        return totalAmount;
    }

}
