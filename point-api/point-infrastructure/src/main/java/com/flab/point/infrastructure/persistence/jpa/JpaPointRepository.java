package com.flab.point.infrastructure.persistence.jpa;

import com.flab.point.domain.Point;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaPointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByUserId(Long userId);
}
