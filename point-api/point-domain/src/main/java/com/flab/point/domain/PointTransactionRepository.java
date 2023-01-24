package com.flab.point.domain;

import java.util.List;

public interface PointTransactionRepository {

    List<PointTransaction> findByUserId(Long userId);

    void save(PointTransaction pointTransaction);

    List<PointTransaction> findByUserIdAndStatus(Long userId, boolean status);
}
