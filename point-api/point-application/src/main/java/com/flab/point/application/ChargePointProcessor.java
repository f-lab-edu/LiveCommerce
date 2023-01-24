package com.flab.point.application;

import com.flab.point.application.command.ChargePointCommand;
import com.flab.point.domain.PointCategory;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransactionService;
import org.springframework.transaction.annotation.Transactional;

public class ChargePointProcessor {

    private final PointRepository pointRepository;
    private final PointTransactionService pointTransactionService;

    public ChargePointProcessor(
            PointRepository pointRepository,
            PointTransactionService pointTransactionService
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionService = pointTransactionService;
    }

    @Transactional
    public Integer execute(Long userId, ChargePointCommand command) {
        var point = pointRepository.findByUserId(userId);
        return point.addPoints(pointTransactionService, command.getChargeAmount(), PointCategory.CHARGE);
    }
}
