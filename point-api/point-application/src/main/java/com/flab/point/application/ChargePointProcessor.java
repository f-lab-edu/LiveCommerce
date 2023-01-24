package com.flab.point.application;

import com.flab.point.application.command.ChargePointCommand;
import com.flab.point.domain.PointCategory;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.domain.PointTransactionService;

public class ChargePointProcessor {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;
    private final PointTransactionService pointTransactionService;

    public ChargePointProcessor(
            PointRepository pointRepository,
            PointTransactionRepository pointTransactionRepository,
            PointTransactionService pointTransactionService
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
        this.pointTransactionService = pointTransactionService;
    }

    public Integer execute(Long userId, ChargePointCommand command) {
        var point = pointRepository.findByUserId(userId);
        return point.addPoints(pointTransactionService, command.getChargeAmount(), PointCategory.CHARGE);
    }
}
