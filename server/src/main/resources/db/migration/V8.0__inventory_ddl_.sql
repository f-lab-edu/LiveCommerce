-- inventory
create table inventory
(
    id              bigint auto_increment primary key comment 'ID',
    item_id         bigint      not null comment '주문 ID',
    sale_status     varchar(30) not null comment '상품 상태',
    item_name       varchar(30) not null comment '상품 이름',
    quantity        int      not null comment '상품 수량',
    inventory_state varchar(30) not null comment '재고 상태'
);



