ALTER TABLE item_image
    MODIFY item_id bigint not null comment '상품 ID',
    MODIFY url VARCHAR(2500) comment '이미지 주소',
    MODIFY name VARCHAR(500) comment '이미지 파일 이름',
    ADD COLUMN ordering tinyint(1) comment '정렬 순서';

ALTER TABLE item_image ADD FOREIGN KEY(item_id) REFERENCES item(id);