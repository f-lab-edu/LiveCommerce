package com.flab.point.application;

import com.flab.point.application.command.ReducePointCommand;
import com.flab.point.domain.PointRepository;

public class ReducePointProcessor {

    private final PointRepository pointRepository;

    public ReducePointProcessor(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Integer execute(Long userId, ReducePointCommand command) {
        var point = pointRepository.findByUserId(userId);

        return point.reduce(command.getReducedAmount());
    }
}
