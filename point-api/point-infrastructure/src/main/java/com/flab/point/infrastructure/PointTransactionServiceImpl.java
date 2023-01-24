package com.flab.point.infrastructure;

import com.flab.point.domain.Point;
import com.flab.point.domain.PointCategory;
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
    public Integer addPoints(
            Point point,
            Integer chargedAmount,
            PointCategory pointCategory
    ) {
        Point addedPoint = point.add(chargedAmount);
        pointRepository.save(addedPoint);
        pointTransactionRepository.save(
                new PointTransaction(
                        point.getUserId(),
                        pointCategory,
                        chargedAmount
                )
        );

        return chargedAmount;
    }

    @Override
    public Integer reducePoints(
            Point point,
            List<PointTransaction> pointTransactionList,
            Integer reducedAmount
    ) {
        Point reducedPoint = point.reduce(reducedAmount);
        pointRepository.save(reducedPoint);

        var ptxList = getValidPointTransactions(pointTransactionList);

        int toReduceAmount = reducedAmount;
        for (PointTransaction ptx : ptxList) {
            if (toReduceAmount == 0) {
                break;
            }
            var reducedPtxDto = ptx.reduce(toReduceAmount);
            toReduceAmount = reducedPtxDto.getRemainPoints();
            pointTransactionRepository.save(reducedPtxDto.getPointTransaction());
        }

        return reducedPoint.getAmount();
    }

    private List<PointTransaction> getValidPointTransactions(List<PointTransaction> pointTransactionList) {

        return pointTransactionList.stream()
                .filter(ptx -> ptx.getExpireAt().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

}
