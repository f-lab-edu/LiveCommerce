package com.flab.point.domain;

import java.util.List;

public interface PointTransactionRepository {

    List<PointTransaction> findAllByUserId(Long userId);

    void save(PointTransaction reducedPtx);
}
