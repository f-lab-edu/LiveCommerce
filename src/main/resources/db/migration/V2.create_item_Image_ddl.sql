CREATE TABLE item_image
(
    id   INT          NOT NULL AUTO_INCREMENT,
    name VARCHAR(300) NOT NULL,
    url  VARCHAR(300) NOT NULL,
    item_id INT NOT NULL,
    PRIMARY KEY (id)
);