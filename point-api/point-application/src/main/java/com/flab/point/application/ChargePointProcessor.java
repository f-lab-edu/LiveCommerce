package com.flab.point.application;

import com.flab.point.application.command.ChargePointCommand;
import com.flab.point.domain.PointCategory;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransactionRepository;

public class ChargePointProcessor {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;

    public ChargePointProcessor(
            PointRepository pointRepository,
            PointTransactionRepository pointTransactionRepository
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
    }

    public Integer execute(Long userId, ChargePointCommand command) {
        var point = pointRepository.findByUserId(userId);
        Integer totalPoints = point.add(command.getChargeAmount(), PointCategory.CHARGE);

        return totalPoints;
    }
}
