package com.flab.point.application;

import com.flab.point.application.command.ChargePointCommand;
import com.flab.point.domain.PointCategory;
import com.flab.point.domain.PointRepository;

public class ChargePointProcessor {

    private final PointRepository pointRepository;

    public ChargePointProcessor(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Integer execute(Long userId, ChargePointCommand command) {
        var point = pointRepository.findByUserId(userId);
        return point.add(command.getChargeAmount(), PointCategory.CHARGE);
    }
}
