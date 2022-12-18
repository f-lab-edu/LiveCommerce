-- payment
create table payment
(
    id           bigint auto_increment primary key comment 'ID',
    order_id     bigint not null comment '주문 ID',
    total_amount bigint not null comment '결제 합계 금액'
);