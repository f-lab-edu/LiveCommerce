package com.flab.point.domain;


public interface PointRepository {
    Point findByUserId(Long userId);
}
