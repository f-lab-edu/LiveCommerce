-- order
create table orders
(
    id                      bigint auto_increment primary key comment 'ID',
    user_id                 bigint       not null comment '유저 ID',
    pay_method              varchar(30)  not null comment '결제수단',
    receiver_name           varchar(30)  not null comment '수령자명',
    receiver_phone_number   varchar(30)  not null comment '수령자 휴대폰번호',
    receiver_zipcode        varchar(10)  not null comment '수령자 우편번호',
    receiver_address        varchar(255) not null comment '수령자 주소',
    receiver_detail_address varchar(255) not null comment '수령자 상세 주소',
    receiver_message        varchar(255) not null comment '수령자 요청사항',
    order_status            varchar(30)  not null default 'ORDER_CREATED' comment '상태',
    ordered_at              datetime(6) not null comment '주문 일시'
);


-- order_item
create table order_line_item
(
    id          bigint auto_increment primary key comment 'ID',
    order_id    bigint       not null comment 'order_id',
    order_count tinyint      not null comment '주문갯수',
    shop_id     bigint       not null comment '상점 ID',
    item_id     bigint       not null comment '상품 ID',
    name        varchar(255) not null comment '상품명',
    price       int(11) not null comment '상품 가격'
);


-- order_item_option_group
create table order_item_option_group
(
    id                 bigint auto_increment primary key comment 'ID',
    order_line_item_id bigint       not null comment 'order_line_item_id',
    ordering           tinyint(3) not null comment '정렬순서',
    name               varchar(255) not null comment '상품 옵션 그룹명'
);


-- order_item_option
create table order_item_option
(
    id                         bigint auto_increment primary key comment 'ID',
    order_item_option_group_id bigint       not null comment 'order_item_option_group_id',
    ordering                   tinyint(3) not null comment '정렬순서',
    name                       varchar(255) not null comment '상품 옵션명',
    price                      int(11) not null comment '상품 옵션 가격'
);
