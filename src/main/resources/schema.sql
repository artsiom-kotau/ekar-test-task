DROP TABLE IF EXISTS `ekar`.`request_event`;
DROP TABLE IF EXISTS `ekar`.`stop_event`;

CREATE TABLE `ekar`.`request_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `payload` VARCHAR(1024) NULL,
  `event_time` DATETIME NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ekar`.`stop_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_time` DATETIME NULL,
  PRIMARY KEY (`id`));