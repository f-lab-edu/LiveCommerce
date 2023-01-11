package com.flab.point.presentation.response;

public class ReducePointResponse {

    private final Long remainPoints;


    public ReducePointResponse(Long remainPoints) {
        this.remainPoints = remainPoints;
    }

    public Long getRemainPoints() {
        return remainPoints;
    }
}
