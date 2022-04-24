-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema intercorp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema intercorp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `intercorp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `intercorp` ;

-- -----------------------------------------------------
-- Table `intercorp`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intercorp`.`customer` (
  `cust_number_doc` VARCHAR(50) NOT NULL,
  `cust_type_doc` VARCHAR(50) NOT NULL,
  `cust_name` VARCHAR(50) NOT NULL,
  `cust_last_name` VARCHAR(50) NOT NULL,
  `cust_email` VARCHAR(100) NOT NULL,
  `cust_birthday` DATETIME NOT NULL,
  `cust_status` VARCHAR(50) NOT NULL,
  `cust_creation_date` DATETIME NOT NULL,
  PRIMARY KEY (`cust_number_doc`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
