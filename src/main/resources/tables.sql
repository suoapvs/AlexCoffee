USE AlexCoffee;

DROP TABLE IF EXISTS `Statuses`;
CREATE TABLE `Statuses` (
  `id`          INT UNSIGNED                                            NOT NULL AUTO_INCREMENT,
  `title`       ENUM ('NEW', 'WORK', 'DELIVERY', 'CLOSED', 'REJECTION') NOT NULL,
  `description` TEXT                                                             DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Roles`;
CREATE TABLE `Roles` (
  `id`          INT UNSIGNED                      NOT NULL AUTO_INCREMENT,
  `title`       ENUM ('CLIENT', 'ADMIN', 'MANAGER') NOT NULL,
  `description` TEXT                              NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Photos`;
CREATE TABLE `Photos` (
  `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`            VARCHAR(50)  NOT NULL,
  `photo_link_short` VARCHAR(100) NOT NULL,
  `photo_link_long`  VARCHAR(100)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id`     INT UNSIGNED NOT NULL,
  `name`        VARCHAR(50)  NOT NULL,
  `username`    VARCHAR(50)           DEFAULT NULL,
  `password`    VARCHAR(50)           DEFAULT NULL,
  `email`       VARCHAR(50)  NOT NULL,
  `phone`       VARCHAR(20)  NOT NULL,
  `vkontakte`   VARCHAR(50)           DEFAULT NULL,
  `facebook`    VARCHAR(50)           DEFAULT NULL,
  `skype`       VARCHAR(50)           DEFAULT NULL,
  `description` TEXT                  DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `Roles` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Categories`;
CREATE TABLE `Categories` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `url`         VARCHAR(50)  NOT NULL,
  `title`       VARCHAR(50)  NOT NULL,
  `description` TEXT         NOT NULL,
  `photo_id`    INT UNSIGNED          DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `Photos` (`id`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Products`;
CREATE TABLE `Products` (
  `id`          INT UNSIGNED           NOT NULL AUTO_INCREMENT,
  `article`     INT UNSIGNED           NOT NULL,
  `title`       VARCHAR(100)           NOT NULL,
  `url`         VARCHAR(100)           NOT NULL,
  `parameters`  TEXT                            DEFAULT NULL,
  `description` TEXT                            DEFAULT NULL,
  `category_id` INT UNSIGNED           NOT NULL,
  `photo_id`    INT UNSIGNED                    DEFAULT NULL,
  `price`       DECIMAL(7, 2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `Categories` (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `Photos` (`id`),
  UNIQUE (`url`),
  UNIQUE (`article`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number`           VARCHAR(10)  NOT NULL,
  `date`             VARCHAR(30)  NOT NULL,
  `status_id`        INT UNSIGNED NOT NULL,
  `client_id`        INT UNSIGNED NOT NULL,
  `manager_id`       INT UNSIGNED          DEFAULT NULL,
  `shipping_address` TEXT                  DEFAULT NULL,
  `shipping_details` TEXT                  DEFAULT NULL,
  `description`      TEXT                  DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`status_id`) REFERENCES `Statuses` (`id`),
  FOREIGN KEY (`client_id`) REFERENCES `Users` (`id`),
  FOREIGN KEY (`manager_id`) REFERENCES `Users` (`id`),
  UNIQUE (`number`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `Sales`;
CREATE TABLE `Sales` (
  `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id`   INT UNSIGNED NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  `number`     INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`) REFERENCES `Orders` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `Products` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
