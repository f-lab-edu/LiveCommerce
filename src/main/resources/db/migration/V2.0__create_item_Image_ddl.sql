CREATE TABLE item_image
(
    id   INT          NOT NULL AUTO_INCREMENT,
    item_id INT NOT NULL,
    name VARCHAR(300) NOT NULL,
    url  VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);