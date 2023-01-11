package com.flab.point.infrastructure.persistence;

import com.flab.point.domain.Point;
import com.flab.point.domain.PointRepository;
import com.flab.point.infrastructure.persistence.jpa.JpaPointRepository;


public class PointRepositoryAdapter implements PointRepository {

    private final JpaPointRepository pointRepository;


    public PointRepositoryAdapter(JpaPointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public Point findByUserId(Long userId) {
        return pointRepository.findByUserId(userId)
                .orElse(new Point(userId, 0L));
    }
}
