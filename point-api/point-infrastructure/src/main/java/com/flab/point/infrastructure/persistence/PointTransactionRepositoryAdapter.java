package com.flab.point.infrastructure.persistence;

import com.flab.point.domain.PointTransaction;
import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.infrastructure.persistence.jpa.JpaPointTransactionRepository;
import java.util.List;

public class PointTransactionRepositoryAdapter implements PointTransactionRepository {

    private final JpaPointTransactionRepository jpaPointTransactionRepository;


    public PointTransactionRepositoryAdapter(JpaPointTransactionRepository jpaPointTransactionRepository) {
        this.jpaPointTransactionRepository = jpaPointTransactionRepository;
    }


    @Override
    public List<PointTransaction> findByUserId(Long userId) {
        return jpaPointTransactionRepository.findAllByUserId(userId);
    }
}
