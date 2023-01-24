package com.flab.point.infrastructure.persistence.jpa;

import com.flab.point.domain.PointTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPointTransactionRepository extends JpaRepository<PointTransaction, Long> {

    List<PointTransaction> findByUserId(Long userId);

    List<PointTransaction> findByUserIdAndStatus(Long userId, boolean status);
}
