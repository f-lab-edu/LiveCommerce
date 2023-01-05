alter table seller
    ADD COLUMN role varchar(255) not null comment '권한',
    ADD COLUMN password varchar(255) not null comment '판매자 비밀번호';
