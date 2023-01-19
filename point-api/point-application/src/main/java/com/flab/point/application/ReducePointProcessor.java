package com.flab.point.application;

import com.flab.point.application.command.ReducePointCommand;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.domain.PointTransactionService;

public class ReducePointProcessor {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;
    private final PointTransactionService pointTransactionService;

    public ReducePointProcessor(
            PointRepository pointRepository,
            PointTransactionRepository pointTransactionRepository,
            PointTransactionService pointTransactionService
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
        this.pointTransactionService = pointTransactionService;
    }

    public void execute(Long userId, ReducePointCommand command) {
        var point = pointRepository.findByUserId(userId);
        var pointTransaction = pointTransactionRepository.findByUserId(userId);

        point.reduceTransactions(this.pointTransactionService, pointTransaction, command.getReducedAmount());
    }
}
