package com.flab.point.application.command;

public class ReducePointCommand {
    private Integer reducedAmount;

    public Integer getReducedAmount() {
        return reducedAmount;
    }

    public ReducePointCommand(Integer reducedAmount) {
        this.reducedAmount = reducedAmount;
    }
}
