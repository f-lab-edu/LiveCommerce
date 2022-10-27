CREATE TABLE item_image
(
    id      BIGINT       not null auto_increment primary key comment 'ID',
    name    VARCHAR(300) NOT NULL,
    url     VARCHAR(300) NOT NULL,
    item_id INT          NOT NULL
);