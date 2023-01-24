package com.flab.point.domain.data;

import com.flab.point.domain.PointTransaction;

public class TransactionDto {

    private PointTransaction pointTransaction;
    private Integer remainPoints;

    public TransactionDto(PointTransaction pointTransaction, Integer remainPoints) {
        this.pointTransaction = pointTransaction;
        this.remainPoints = remainPoints;
    }

    private TransactionDto() {
    }

    public PointTransaction getPointTransaction() {
        return pointTransaction;
    }

    public Integer getRemainPoints() {
        return remainPoints;
    }
}
