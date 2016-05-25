DROP DATABASE IF EXISTS `sistearth`;
CREATE DATABASE `sistearth`;
USE `sistearth`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` text,
  `created_at` date,
  `author` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  CONSTRAINT `posts_has_author` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
