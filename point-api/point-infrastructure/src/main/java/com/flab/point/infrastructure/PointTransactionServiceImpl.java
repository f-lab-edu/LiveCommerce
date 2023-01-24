package com.flab.point.infrastructure;

import com.flab.point.domain.Point;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransaction;
import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.domain.PointTransactionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PointTransactionServiceImpl implements PointTransactionService {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;

    public PointTransactionServiceImpl(
            PointRepository pointRepository,
            PointTransactionRepository pointTransactionRepository
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
    }

    @Override
    public void chargePoints() {

    }

    @Override
    public void reducePoints(
            Point point,
            List<PointTransaction> pointTransactionList,
            Integer reducedAmount
    ) {
        Point reducedPoint = point.reduce(reducedAmount);
        pointRepository.save(reducedPoint);

        var ptxList = getValidPointTransactions(pointTransactionList);

        for (PointTransaction ptx : ptxList) { // TODO 남은돈?
            var reducedPtx = ptx.reduce(reducedAmount);
            pointTransactionRepository.save(reducedPtx);
            if (reducedPtx.getAmount() == 0) {
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
