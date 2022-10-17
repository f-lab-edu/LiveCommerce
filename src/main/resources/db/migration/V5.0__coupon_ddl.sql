create table coupon
(
    id              bigint auto_increment primary key comment 'ID',
    category_id     bigint      not null comment '카테고리 ID',
    name            varchar(30) not null comment '쿠폰 이름',
    min_price       int(11)     not null comment '최소 상품 주문 금액',
    discount_price  int(11)     not null comment '쿠폰 할인 가격',
    coupon_status   varchar(30) not null default 'AVAILABLE' comment '쿠폰 사용 상태',
    expiration_date datetime(6) not null comment '쿠폰 사용 만료 날짜'
);