SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema electronic_car_service_administrator
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema electronic_car_service_administrator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `electronic_car_service_administrator` DEFAULT CHARACTER SET utf8 ;
USE `electronic_car_service_administrator` ;

-- -----------------------------------------------------
-- Table `electronic_car_service_administrator`.`craftsman`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electronic_car_service_administrator`.`craftsman` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `electronic_car_service_administrator`.`garage_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electronic_car_service_administrator`.`garage_place` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `number` INT NULL,
  `space` DECIMAL(12,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `electronic_car_service_administrator`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electronic_car_service_administrator`.`order_status` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `order_status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `electronic_car_service_administrator`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electronic_car_service_administrator`.`orders` (
  `id` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(12,2) NULL,
  `submission_date` DATETIME NULL,
  `start_date` DATETIME NULL,
  `completion_date` DATETIME NULL,
  `garage_place_id` BIGINT(4) NOT NULL,
  `order_status_id` BIGINT(4) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_garage_place1_idx` (`garage_place_id` ASC) VISIBLE,
  INDEX `fk_order_order_status1_idx` (`order_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_garage_place1`
    FOREIGN KEY (`garage_place_id`)
    REFERENCES `electronic_car_service_administrator`.`garage_place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `electronic_car_service_administrator`.`order_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `electronic_car_service_administrator`.`order_craftsman`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electronic_car_service_administrator`.`order_craftsman` (
  `order_id` BIGINT(4) NOT NULL,
  `craftsman_id` BIGINT(4) NOT NULL,
  PRIMARY KEY (`order_id`, `craftsman_id`),
  INDEX `fk_order_has_craftsman_craftsman1_idx` (`craftsman_id` ASC) VISIBLE,
  INDEX `fk_order_has_craftsman_order_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_craftsman_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `electronic_car_service_administrator`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_craftsman_craftsman1`
    FOREIGN KEY (`craftsman_id`)
    REFERENCES `electronic_car_service_administrator`.`craftsman` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
