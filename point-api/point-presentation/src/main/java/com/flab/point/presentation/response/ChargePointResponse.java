package com.flab.point.presentation.response;

public class ChargePointResponse {

    private final Long totalPoints;


    public ChargePointResponse(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }
}
