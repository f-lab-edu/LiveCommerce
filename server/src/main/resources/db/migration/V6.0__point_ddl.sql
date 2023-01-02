-- point
create table points
(
    id           bigint auto_increment primary key comment 'ID',
    user_id      bigint      not null comment '유저 ID',
    total_amount bigint      not null comment '포인트 합계 금액',
    register_at  datetime(6) not null comment '포인트 등록 일시',
    updated_at   datetime(6) not null comment '포인트 업데이트 일시'
);

-- point_transaction
create table point_transaction
(
    id             bigint auto_increment primary key comment 'ID',
    user_id        bigint       not null comment '유저 ID',
    point_id       bigint       not null comment '포인트 ID',
    point_category varchar(255) not null comment '포인트 생성 카테고리',
    amount         bigint       not null comment '포인트 금액',
    transact_at    datetime(6)  not null comment '포인트 거래 일시',
    expire_at      datetime(6)  not null comment '포인트 만료 일시',
    status         tinyint(3)   not null comment '포인트 상태값'
);
CREATE INDEX idx_point_transact_status_expire_at ON point_transaction (user_id, status, expire_at)