package com.flab.point.domain;

import java.util.List;

public interface PointTransactionService {

    void chargePoints();

    void reducePoints(Point point, List<PointTransaction> pointTransactionList, Integer reducedAmount);

}
