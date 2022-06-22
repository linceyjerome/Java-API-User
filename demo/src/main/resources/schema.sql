CREATE TABLE IF NOT EXISTS `USER`
(
    `id`     int primary key auto_increment,
    `name`   varchar(50) not null,
    `phone`  varchar(50) not null,
    `email`  varchar(50) not null,
    `credit` decimal     not null
);






