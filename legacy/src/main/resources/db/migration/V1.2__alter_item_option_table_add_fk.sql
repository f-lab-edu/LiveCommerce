ALTER TABLE item_option_group ADD FOREIGN KEY(item_id) REFERENCES item(id);

ALTER TABLE item_option
    ADD COLUMN item_id bigint not null comment '상품 ID',
    ADD FOREIGN KEY(item_option_group_id) REFERENCES item_option_group(id);

