package com.flab.point.domain;

import java.util.List;

public interface PointTransactionService {

    Integer addPoints(Point point, Integer chargedAmount, PointCategory pointCategory);

    Integer reducePoints(Point point, List<PointTransaction> pointTransactionList, Integer reducedAmount);

}
