package com.flab.point.domain;

import com.flab.point.domain.data.TransactionDto;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/*
 *  포인트 '차감' 시 PointTransaction에서 포인트를 적립 순서와 만료기간을 고려하여 차감할 수 있도록 함 -> '적립'된 포인트를 따로 관리하고 차감
 */

@Entity
@Table(indexes = {@Index(name = "idx_point_transact_status_expire_at", columnList = "userId, status")})
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
    private boolean status;

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

    public TransactionDto reduce(Integer reducedAmount) {
        if (this.amount > reducedAmount) {
            this.amount -= reducedAmount;
            reducedAmount = 0;
        } else {
            reducedAmount -= this.amount;
            this.status = false;
            this.amount = 0;
        }
        return new TransactionDto(this, reducedAmount);
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
