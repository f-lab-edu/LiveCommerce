ALTER TABLE item_image
    DROP COLUMN id,
    DROP COLUMN name,
    MODIFY item_id bigint not null comment '상품 ID',
    MODIFY url VARCHAR(2500) comment '이미지 주소',
    ADD COLUMN ordering tinyint(1) comment '정렬 순서',
    ADD COLUMN is_thumbnail BOOLEAN comment '썸네일 이미지',
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (item_id, ordering);

ALTER TABLE item_image ADD FOREIGN KEY(item_id) REFERENCES item(id);