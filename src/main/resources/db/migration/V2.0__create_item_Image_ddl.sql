CREATE TABLE item_image
(
    id      BIGINT        not null auto_increment primary key comment 'ID',
    item_id bigint not null comment '상품 ID',
    name    VARCHAR(500) NOT NULL comment '이미지 파일 이름',
    url     VARCHAR(2500) NOT NULL comment '이미지 주소',
    ordering tinyint(1) comment '정렬 순서',
    FOREIGN KEY(item_id) REFERENCES item(id)
);