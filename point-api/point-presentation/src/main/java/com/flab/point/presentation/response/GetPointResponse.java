package com.flab.point.presentation.response;

public class GetPointResponse {

    private final Integer userPoints;

    public GetPointResponse(Integer userPoints) {
        this.userPoints = userPoints;
    }

    public Integer getUserPoints() {
        return userPoints;
    }
}
