package com.flab.point.application.command;

public class ReducePointCommand {
    private Long reducedAmount;

    public Long getReducedAmount() {
        return reducedAmount;
    }

    public ReducePointCommand(Long reducedAmount) {
        this.reducedAmount = reducedAmount;
    }
}
