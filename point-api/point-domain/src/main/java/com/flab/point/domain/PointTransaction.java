package com.flab.point.domain;

/*
 * 적립포인트 아이디(earnedPointId)를 기준으로 groupBy 해서 만료 기간 및 포인트 사용 처리
 */
public class PointTransaction {

    private Long id;
    private Long pointId;
    private Long earnedPointId;
    private PointStatus pointStatus;
    private Long amount;
    private String transactAt;
    private String expireAt;

    public PointTransaction() {
    }


}
