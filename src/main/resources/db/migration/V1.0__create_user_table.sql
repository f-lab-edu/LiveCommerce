CREATE TABLE user
(
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    password VARCHAR(255),
    nickname VARCHAR(255),
    PRIMARY KEY (id)
);