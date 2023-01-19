package com.flab.point.domain;

import java.util.List;

public interface PointTransactionRepository {

    List<PointTransaction> findByUserId(Long userId);
}
