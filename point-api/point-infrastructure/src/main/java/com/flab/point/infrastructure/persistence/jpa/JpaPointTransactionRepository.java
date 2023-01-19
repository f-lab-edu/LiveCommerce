package com.flab.point.infrastructure.persistence.jpa;

import com.flab.point.domain.PointTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPointTransactionRepository extends JpaRepository<PointTransaction, Long> {

    List<PointTransaction> findAllByUserId(Long userId);
}
