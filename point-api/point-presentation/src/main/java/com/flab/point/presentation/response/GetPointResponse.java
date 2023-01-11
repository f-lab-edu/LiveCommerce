package com.flab.point.presentation.response;

public class GetPointResponse {

    private final Long userPoints;

    public GetPointResponse(Long userPoints) {
        this.userPoints = userPoints;
    }

    public Long getUserPoints() {
        return userPoints;
    }
}
