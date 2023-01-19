package com.flab.point.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 *  포인트 '차감' 시 PointTransaction에서 포인트를 적립 순서와 만료기간을 고려하여 차감할 수 있도록 함 -> '적립'된 포인트를 따로 관리하고 차감
 */

@Entity
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private PointCategory pointCategory;
    private Integer amount;
    private LocalDateTime transactAt;
    private LocalDateTime expireAt;
    private boolean status; // 포인트가 현재 사용 가능한지 여부 - 상태값 필드

    protected PointTransaction() {
    }

    public PointTransaction(
            Long userId,
            PointCategory pointCategory,
            Integer amount
    ) {
        this.userId = userId;
        this.pointCategory = pointCategory;
        this.amount = amount;
        this.transactAt = LocalDateTime.now();
        this.expireAt = setExpireDate();
        this.status = true;
    }

    public static PointTransaction add(Point point, Integer amount, PointCategory pointCategory) {
        return new PointTransaction(point.getUserId(), pointCategory, amount);
    }

    // 하나의 포인트 트랜잭션에서 point 차감. return 감소시켜야 할 남은 포인트
    public Integer reduce(Integer reducedAmount) {
        if (this.amount <= reducedAmount) {
            this.status = false;
            reducedAmount -= this.amount;
        } else {
            this.amount -= reducedAmount;
            reducedAmount = 0;
        }
        return reducedAmount;
    }

    // TODO 선착순 이벤트 쿠폰 추가 후 각 카테고리 별 만료기간 설정을 유연성있게 할 예정. 현재는 적립시 적립 일자 + 한달로 설정
    private LocalDateTime setExpireDate() {
        return LocalDateTime.now().plusMonths(1);
    }

    public PointCategory getPointCategory() {
        return pointCategory;
    }

    public Integer getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }
}