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
    private Long pointId;
    @Enumerated(EnumType.STRING)
    private PointCategory pointCategory;
    private Long amount;
    private LocalDateTime transactAt;
    private LocalDateTime expireAt;
    private boolean status; // 포인트가 현재 사용 가능한지 여부 - 상태값 필드

    protected PointTransaction() {
    }

    public PointTransaction(
            Long userId,
            Long pointId,
            PointCategory pointCategory,
            Long amount
    ) {
        this.userId = userId;
        this.pointId = pointId;
        this.pointCategory = pointCategory;
        this.amount = amount;
        this.transactAt = LocalDateTime.now();
        this.expireAt = setExpireDate();
        this.status = true;
    }

    public static void add(Point point, Long amount, PointCategory pointCategory) {
        new PointTransaction(point.getUserId(), point.getId(), pointCategory, amount);
    }

    // TODO 추후 주문 상품별, 이벤트별 만료 기간 설정. 현재는 적립시 적립 일자 + 한달로 설정
    private LocalDateTime setExpireDate() {
        return LocalDateTime.now().plusMonths(1);
    }


}
