package com.flab.point.presentation.response;

public class ChargePointResponse {

    private final Integer totalPoints;


    public ChargePointResponse(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }
}
