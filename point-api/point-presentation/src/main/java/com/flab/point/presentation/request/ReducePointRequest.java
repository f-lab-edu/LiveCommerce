package com.flab.point.presentation.request;

import com.flab.point.application.command.ReducePointCommand;
import javax.validation.constraints.NotNull;

public class ReducePointRequest {

    @NotNull(message = "사용할 포인트 금액을 입력하세요.")
    private Integer reducedAmount;

    public ReducePointRequest() {
    }

    public Integer getReducedAmount() {
        return reducedAmount;
    }

    public ReducePointCommand toCommand() {
        return new ReducePointCommand(reducedAmount);
    }
}