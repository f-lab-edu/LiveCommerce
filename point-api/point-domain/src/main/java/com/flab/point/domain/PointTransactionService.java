package com.flab.point.domain;

import java.util.List;

public interface PointTransactionService {

    void reducePoints(Point point, List<PointTransaction> pointTransactionList, Integer reducedAmount);

}
