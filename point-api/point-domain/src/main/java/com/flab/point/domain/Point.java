package com.flab.point.domain;

import com.flab.point.domain.exception.NotEnoughPointsException;
import java.time.LocalDateTime;
import java.util.List;
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
    private Integer totalAmount;
    private LocalDateTime updatedAt;

    protected Point() {
    }

    public Point(
            Long userId,
            Integer amount
    ) {
        this.userId = userId;
        this.totalAmount = amount;
        this.updatedAt = LocalDateTime.now();
    }

    // 추가 (적립, 충전)
    public Integer add(
            Integer addedAmount,
            PointCategory pointCategory
    ) {
        this.totalAmount += addedAmount;
        this.updatedAt = LocalDateTime.now();
        PointTransaction.add(
                this,
                addedAmount,
                pointCategory
        );

        return totalAmount;
    }

    // 포인트 사용
    public Integer reduce(
            Integer reducedAmount
    ) {
        if (this.totalAmount - reducedAmount < 0) {
            throw new NotEnoughPointsException();
        }

        var pointTransactionList = getValidPointTransactions();

        Integer remainAmount = reducedAmount;
        while (remainAmount > 0) {
            for (PointTransaction ptx : pointTransactionList) {
                remainAmount = ptx.reduce(remainAmount);
            }
        }

        this.updatedAt = LocalDateTime.now();

        this.totalAmount -= reducedAmount;
        return this.totalAmount;
    }

    private List<PointTransaction> getValidPointTransactions() {
        /*
        return PointTransaction.stream()
                .filter(ptx -> ptx.isStatus() && ptx.getExpireAt().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

         */
        return null;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return totalAmount;
    }

}
