package com.flab.point.domain;

public enum PointStatus {
    POINT_EARN("포인트 적립"),
    POINT_USED("포인트 사용"),
    POINT_REFUND("포인트 환불 반환"),
    POINT_EXPIRED("포인트 유효기간 만료");
    private final String description;

    PointStatus(String description) {
        this.description = description;
    }
}
