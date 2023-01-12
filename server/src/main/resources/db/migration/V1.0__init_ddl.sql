-- user
CREATE TABLE user
(
    id       bigint       not null auto_increment primary key comment 'ID',
    email    varchar(255) not null comment '유저 email',
    password varchar(255) not null comment '유저 패스워드',
    nickname varchar(255) not null comment '닉네임'
);

-- item
create table item
(
    id             bigint auto_increment primary key comment 'ID',
    name           varchar(255) not null comment '상품명',
    description    varchar(255) not null comment '상품 설명',
    price          int(11) not null comment '상품 가격',
    sales_price    int(11) not null comment '할인 가격',
    stock_quantity int(11) not null comment '상품 재고',
    model_number   int(11) not null
);

-- item_option_groups
create table item_option_group
(
    id             bigint auto_increment primary key comment 'ID',
    item_id        bigint null comment '상품 ID',
    ordering       tinyint(3) not null comment '정렬순서',
    name           varchar(30) not null comment '옵션그룹명',
    basic          bit         not null comment '기본 옵션',
    exclusive      bit         not null comment '배타 옵션',
    minimum_choice int(11) not null comment '최소 선택 개수',
    maximum_choice int(11) not null comment '최대 선택 개수'
);

-- item_option
create table item_option
(
    id                   bigint auto_increment primary key comment 'ID',
    item_option_group_id bigint null comment '상품 옵션 그룹 ID',
    ordering             tinyint(3) not null comment '정렬순서',
    name                 varchar(30) not null comment '옵션명',
    price                int(11) not null comment '상품 옵션 가격'
);