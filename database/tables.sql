DROP DATABASE IF EXISTS `alexcoffee`;
CREATE DATABASE IF NOT EXISTS `alexcoffee` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `alexcoffee`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: alexcoffee
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `photos`
--

DROP TABLE IF EXISTS `photos`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photos` (
  `id`               INT UNSIGNED NOT NULL       AUTO_INCREMENT,
  `title`            VARCHAR(50)  NOT NULL,
  `photo_link_short` VARCHAR(100) NOT NULL       DEFAULT '',
  `photo_link_long`  VARCHAR(100) NOT NULL       DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id`          INT UNSIGNED                        NOT NULL         AUTO_INCREMENT,
  `role`        ENUM ('ADMIN', 'CLIENT', 'MANAGER') NOT NULL         DEFAULT 'CLIENT',
  `name`        VARCHAR(50)                         NOT NULL         DEFAULT '',
  `username`    VARCHAR(50)                         NOT NULL         DEFAULT '',
  `password`    VARCHAR(50)                         NOT NULL         DEFAULT '',
  `email`       VARCHAR(50)                         NOT NULL,
  `phone`       VARCHAR(20)                         NOT NULL,
  `vkontakte`   VARCHAR(50)                         NOT NULL         DEFAULT '',
  `facebook`    VARCHAR(50)                         NOT NULL         DEFAULT '',
  `skype`       VARCHAR(50)                         NOT NULL         DEFAULT '',
  `description` TEXT                                NOT NULL         DEFAULT '',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `url`         VARCHAR(50)  NOT NULL DEFAULT '',
  `title`       VARCHAR(50)  NOT NULL DEFAULT '',
  `description` TEXT         NOT NULL DEFAULT '',
  `photo_id`    INT UNSIGNED          DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id`          INT UNSIGNED           NOT NULL             AUTO_INCREMENT,
  `article`     INT UNSIGNED           NOT NULL,
  `title`       VARCHAR(100)           NOT NULL             DEFAULT '',
  `url`         VARCHAR(100)           NOT NULL             DEFAULT '',
  `parameters`  TEXT                   NOT NULL             DEFAULT '',
  `description` TEXT                   NOT NULL             DEFAULT '',
  `category_id` INT UNSIGNED           NOT NULL,
  `photo_id`    INT UNSIGNED,
  `price`       DECIMAL(7, 2) UNSIGNED NOT NULL             DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  UNIQUE (`url`),
  UNIQUE (`article`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id`               INT UNSIGNED                                            NOT NULL             AUTO_INCREMENT,
  `number`           VARCHAR(10)                                             NOT NULL             DEFAULT '',
  `date`             VARCHAR(30)                                             NOT NULL             DEFAULT '',
  `status`           ENUM ('NEW', 'WORK', 'DELIVERY', 'CLOSED', 'REJECTION') NOT NULL             DEFAULT 'NEW',
  `client_id`        INT UNSIGNED                                                                 DEFAULT NULL,
  `manager_id`       INT UNSIGNED                                                                 DEFAULT NULL,
  `shipping_address` TEXT                                                    NOT NULL             DEFAULT '',
  `shipping_details` TEXT                                                    NOT NULL             DEFAULT '',
  `description`      TEXT                                                    NOT NULL             DEFAULT '',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`),
  UNIQUE (`number`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `id`         INT UNSIGNED NOT NULL       AUTO_INCREMENT,
  `order_id`   INT UNSIGNED                DEFAULT NULL,
  `product_id` INT UNSIGNED                DEFAULT NULL,
  `number`     INT UNSIGNED NOT NULL       DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2017-07-05 11:31:42
