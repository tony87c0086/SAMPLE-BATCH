/*==================== DB Upgrade Script Version 1.0.1 ====================*/
/*==================== Default SAMPLE DB initialization ====================*/

/*==================== SCHEMA: SAMPLE SCHEMA - CREATESCRIPT ====================*/
CREATE DATABASE IF NOT EXISTS `sample`;
USE `sample`;

/*==================== TABLE: TEMPLATE - CREATESCRIPT ====================*/
CREATE TABLE IF NOT EXISTS `template` (
  `template_id` INT(11) NOT NULL AUTO_INCREMENT,
  `template_name` VARCHAR(100) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NOW(),
  `active` TINYINT(1) UNSIGNED DEFAULT '1',
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==================== TABLE: NOTIFICATION_EMAIL - CREATESCRIPT ====================*/
CREATE TABLE IF NOT EXISTS `notification_email` (
  `notification_email_id` INT(11) NOT NULL AUTO_INCREMENT,
  `template_id` INT(11) DEFAULT NULL,
  `from_email` VARCHAR(100) DEFAULT NULL,
  `to_email` VARCHAR(100) DEFAULT NULL,
  `subject` VARCHAR(150) NOT NULL,
  `content` TEXT DEFAULT NULL,
  `created_date` DATETIME DEFAULT NOW(),
  `active` TINYINT(1) UNSIGNED DEFAULT '1',
  PRIMARY KEY (`notification_email_id`),
  KEY `FK_notification_email__template_id` (`template_id`),
  CONSTRAINT `FK_notification_email__template_id` FOREIGN KEY (`template_id`) REFERENCES `template` (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==================== DUMPY TABLE DATA ====================*/
DROP procedure IF EXISTS `sampleschemachange`;

DELIMITER $$
CREATE PROCEDURE `sampleschemachange` ()
BEGIN

INSERT INTO `template` (`template_id`,`template_name`,`active`)
VALUES (954598, 'NEWSLETTER.vm',1)
ON DUPLICATE KEY UPDATE
  `template_name` = VALUES(`template_name`),
  `active` = VALUES(`active`);
  
INSERT INTO `notification_email` (`notification_email_id`,`template_id`,`from_email`,`to_email`,`subject`,`content`,`active`)
VALUES (685485, 954598, 'sender@sample.com', 'receiver@sample.com', 'First Sample Email', 'Hello world.',1)
ON DUPLICATE KEY UPDATE
  `from_email` = VALUES(`from_email`),
  `to_email` = VALUES(`to_email`),
  `subject` = VALUES(`subject`),
  `content` = VALUES(`content`),
  `active` = VALUES(`active`);

INSERT INTO `notification_email` (`notification_email_id`,`template_id`,`from_email`,`to_email`,`subject`,`content`,`active`)
VALUES (585485, 954598, 'sender@sample.com', 'receiver@sample.com', 'Second Sample Email', 'Hello world, again.',1)
ON DUPLICATE KEY UPDATE
  `from_email` = VALUES(`from_email`),
  `to_email` = VALUES(`to_email`),
  `subject` = VALUES(`subject`),
  `content` = VALUES(`content`),
  `active` = VALUES(`active`);
  
END
$$

DELIMITER ;

CALL sampleschemachange();

DROP procedure IF EXISTS `sampleschemachange`;
