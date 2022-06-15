/* ****************************************************************************
** Author: Eunji Lee
** Created: June 03, 2022
** Description: As a database of Surpass Clinic, it creates and manages tables
** that contain information about accounts and appointments.
**************************************************************************** */

DROP SCHEMA IF EXISTS `clinicdb`;
CREATE SCHEMA IF NOT EXISTS `clinicdb` DEFAULT CHARACTER SET latin1;
USE `clinicdb`;

-- -----------------------------------------------------
-- Table `clinicdb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`account` (
  `account_id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(30) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `profile` VARCHAR(10) NOT NULL,
  `reset_password_uuid` VARCHAR(50),
  `salt` VARCHAR(50),
  PRIMARY KEY (`account_id`),
  CONSTRAINT `uk_account_user_name`
    UNIQUE (`user_name`));

-- -----------------------------------------------------
-- Table `clinicdb`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`administrator` (
  `admin_id` INT(10) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(80) NOT NULL,
  `last_name` VARCHAR(80) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `mobile_phone` VARCHAR(50) NOT NULL,
  `alt_phone` VARCHAR(50),
  `pref_contact_type` VARCHAR(12) NOT NULL,
  `account_id` INT(10) NOT NULL,
  `gender` VARCHAR(12) NOT NULL,
  `birth_date` DATE NOT NULL,
  `street_address` VARCHAR(100) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `province` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`admin_id`),
  CONSTRAINT `fk_administrator_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `clinicdb`.`account` (`account_id`));

-- -----------------------------------------------------
-- Table `clinicdb`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`doctor` (
  `doctor_id` INT(10) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(80) NOT NULL,
  `last_name` VARCHAR(80) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `mobile_phone` VARCHAR(50) NOT NULL,
  `alt_phone` VARCHAR(50),
  `pref_contact_type` VARCHAR(12) NOT NULL,
  `account_id` INT(10) NOT NULL,
  `gender` VARCHAR(12) NOT NULL,
  `birth_date` DATE NOT NULL,
  `street_address` VARCHAR(100) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `province` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`doctor_id`),
  CONSTRAINT `fk_doctor_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `clinicdb`.`account` (`account_id`));

-- -----------------------------------------------------
-- Table `clinicdb`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`patient` (
  `patient_id` INT(10) NOT NULL AUTO_INCREMENT,
  `healthcare_id` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(80) NOT NULL,
  `last_name` VARCHAR(80) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `mobile_phone` VARCHAR(50) NOT NULL,
  `alt_phone` VARCHAR(50),
  `pref_contact_type` VARCHAR(12) NOT NULL,
  `doctor_id` INT(10) NOT NULL,
  `account_id` INT(10) NOT NULL,
  `gender` VARCHAR(12) NOT NULL,
  `birth_date` DATE NOT NULL,
  `street_address` VARCHAR(100) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `province` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`patient_id`),
  CONSTRAINT `fk_patient_doctor`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `clinicdb`.`doctor` (`doctor_id`),
  CONSTRAINT `fk_patient_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `clinicdb`.`account` (`account_id`));

-- -----------------------------------------------------
-- Table `clinicdb`.`calendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`calendar` (
  `date_time` DATETIME NOT NULL,
  `clinic_open` CHAR(1) NOT NULL,
  PRIMARY KEY (`date_time`));

-- -----------------------------------------------------
-- Table `clinicdb`.`appointment_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`appointment_type` (
  `type` INT(10) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(25) NOT NULL,
  `std_duration` INT(10) NOT NULL,
  PRIMARY KEY (`type`));

-- -----------------------------------------------------
-- Table `clinicdb`.`availability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`availability` (
  `doctor_id` INT(10) NOT NULL,
  `start_date_time` DATETIME NOT NULL,
  `duration` INT(10) NOT NULL,
  PRIMARY KEY (`doctor_id`, `start_date_time`),
  CONSTRAINT `fk_availability_doctor`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `clinicdb`.`doctor` (`doctor_id`),
  CONSTRAINT `fk_availability_calendar`
    FOREIGN KEY (`start_date_time`)
    REFERENCES `clinicdb`.`calendar` (`date_time`));

-- -----------------------------------------------------
-- Table `clinicdb`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`appointment` (
  `doctor_id` INT(10) NOT NULL,
  `start_date_time` DATETIME NOT NULL,
  `patient_id` INT(10) NOT NULL,
  `duration` INT(10) NOT NULL,
  `type` INT(10) NOT NULL,
  `reason` VARCHAR(100) NOT NULL,
  `patient_attended` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`doctor_id`, `start_date_time`),
  CONSTRAINT `fk_appointment_doctor`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `clinicdb`.`doctor` (`doctor_id`),
  CONSTRAINT `fk_appointment_calendar`
    FOREIGN KEY (`start_date_time`)
    REFERENCES `clinicdb`.`calendar` (`date_time`),
  CONSTRAINT `fk_appointment_patient`
    FOREIGN KEY (`patient_id`)
    REFERENCES `clinicdb`.`patient` (`patient_id`),
  CONSTRAINT `fk_appointment_appointment_type`
    FOREIGN KEY (`type`)
    REFERENCES `clinicdb`.`appointment_type` (`type`));
