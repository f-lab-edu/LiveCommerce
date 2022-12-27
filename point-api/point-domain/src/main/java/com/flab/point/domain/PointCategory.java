package com.flab.point.domain;

public enum PointCategory {

    ORDER("주문 포인트"),
    EVENT("이벤트 제공 포인트"),
    CHARGE("사용자 충전 포인트");
    private final String description;

    PointCategory(String description) {
        this.description = description;
    }
}