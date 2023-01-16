package com.flab.point.presentation.response;

public class ReducePointResponse {

    private final Integer remainPoints;


    public ReducePointResponse(Integer remainPoints) {
        this.remainPoints = remainPoints;
    }

    public Integer getRemainPoints() {
        return remainPoints;
    }
}
