-- order
create table orders
(
    id           bigint auto_increment primary key comment 'ID',
    user_id      bigint      not null comment '유저 ID',
    pay_method   varchar(30) not null comment '결제수단',
    total_amount bigint      not null comment '주문 합계 금액',
    order_status varchar(30) not null default 'ORDER_CREATED' comment '상태',
    ordered_at   datetime(6) not null comment '주문 일시'
);


-- order_item
create table order_line_item
(
    id          bigint auto_increment primary key comment 'ID',
    order_id    bigint null comment 'order_id',
    item_id     bigint       not null comment '상품 ID',
    order_count int          not null comment '주문갯수',
    name        varchar(255) not null comment '상품명',
    price       int(11) not null comment '상품 가격'
);


-- order_item_option_group
create table order_item_option_group
(
    id                 bigint auto_increment primary key comment 'ID',
    order_line_item_id bigint null comment 'order_line_item_id',
    ordering           tinyint(3) not null comment '정렬순서',
    name               varchar(255) not null comment '상품 옵션 그룹명'
);


-- order_item_option
create table order_item_option
(
    id                         bigint auto_increment primary key comment 'ID',
    order_item_option_group_id bigint null comment 'order_item_option_group_id',
    ordering                   tinyint(3) not null comment '정렬순서',
    name                       varchar(255) not null comment '상품 옵션명',
    price                      int(11) not null comment '상품 옵션 가격'
);
