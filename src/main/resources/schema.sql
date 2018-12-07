DROP TABLE IF EXISTS `request_event`;
DROP TABLE IF EXISTS `stop_event`;

CREATE TABLE `request_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `payload` VARCHAR(1024) NULL,
  `event_time` DATETIME NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `stop_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reached` VARCHAR(8) NULL,
  `event_time` DATETIME NULL,
  PRIMARY KEY (`id`));