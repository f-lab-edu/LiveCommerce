-- seller
create table seller
(
    id          bigint auto_increment primary key comment 'ID',
    name        varchar(255) not null comment '상점명',
    business_no varchar(255) not null comment '사업자등록번호',
    email       varchar(255) not null comment 'email'
);