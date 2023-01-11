package com.flab.point.application;

import com.flab.point.domain.PointRepository;

public class GetUserPointProcessor {

    private final PointRepository pointRepository;


    public GetUserPointProcessor(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Long execute(Long userId) {
        var point = pointRepository.findByUserId(userId);
        return point.getAmount();
    }
}
