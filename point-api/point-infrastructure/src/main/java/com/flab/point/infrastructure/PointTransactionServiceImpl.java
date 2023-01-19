package com.flab.point.infrastructure;

import com.flab.point.domain.Point;
import com.flab.point.domain.PointTransaction;
import com.flab.point.domain.PointTransactionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PointTransactionServiceImpl implements PointTransactionService {

    @Override
    public void reducePoints(
            Point point,
            List<PointTransaction> pointTransactionList,
            Integer reducedAmount
    ) {
        point.reduce(reducedAmount);
        var ptxList = getValidPointTransactions(pointTransactionList);

        int remainPoints = reducedAmount;
        for (PointTransaction ptx : ptxList) {
            remainPoints = ptx.reduce(remainPoints);
            if (remainPoints == 0) {
                break;
            }
        }
    }

    private List<PointTransaction> getValidPointTransactions(List<PointTransaction> pointTransactionList) {
        return pointTransactionList.stream()
                .filter(ptx -> ptx.isStatus() && ptx.getExpireAt().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

}
