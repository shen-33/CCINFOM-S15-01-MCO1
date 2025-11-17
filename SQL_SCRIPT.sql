-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ccinfom_s15_01
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ccinfom_s15_01
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ccinfom_s15_01` DEFAULT CHARACTER SET utf8 ;
USE `ccinfom_s15_01` ;

-- -----------------------------------------------------
-- Table `ccinfom_s15_01`.`Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ccinfom_s15_01`.`Movie` (
  `movie_id` INT NOT NULL,
  `movie_title` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  `genre` VARCHAR(45) NULL,
  `rating` FLOAT NULL,
  `duration` INT NULL,
  `showDates` DATETIME NULL,
  `timeSlots` DATETIME NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ccinfom_s15_01`.`Cinema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ccinfom_s15_01`.`Cinema` (
  `cinema_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  `total_slots` INT NULL,
  PRIMARY KEY (`cinema_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ccinfom_s15_01`.`ParkingSlot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ccinfom_s15_01`.`ParkingSlot` (
  `slot_id` INT NOT NULL,
  `slot_no` INT NULL,
  `status` TINYINT NULL,
  `Cinema_cinema_id` INT NOT NULL,
  PRIMARY KEY (`slot_id`),
  INDEX `fk_ParkingSlot_Cinema1_idx` (`Cinema_cinema_id` ASC) VISIBLE,
  CONSTRAINT `fk_ParkingSlot_Cinema1`
    FOREIGN KEY (`Cinema_cinema_id`)
    REFERENCES `ccinfom_s15_01`.`Cinema` (`cinema_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ccinfom_s15_01`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ccinfom_s15_01`.`Customer` (
  `customer_id` INT NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `phoneNo` VARCHAR(45) NULL,
  `car_plate` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ccinfom_s15_01`.`Booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ccinfom_s15_01`.`Booking` (
  `booking_id` INT NOT NULL,
  `passengers_num` INT NULL,
  `total_amount` DOUBLE NULL,
  `time_slot` DATETIME NULL,
  `date` DATE NULL,
  `order_status` TINYINT NULL,
  `Movie_movie_id` INT NOT NULL,
  `Customer_customer_id` INT NOT NULL,
  `Cinema_cinema_id` INT NOT NULL,
  `ParkingSlot_slot_id` INT NOT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `fk_Booking_Movie_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_Booking_Customer1_idx` (`Customer_customer_id` ASC) VISIBLE,
  INDEX `fk_Booking_Cinema1_idx` (`Cinema_cinema_id` ASC) VISIBLE,
  INDEX `fk_Booking_ParkingSlot1_idx` (`ParkingSlot_slot_id` ASC) VISIBLE,
  CONSTRAINT `fk_Booking_Movie`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `ccinfom_s15_01`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Customer1`
    FOREIGN KEY (`Customer_customer_id`)
    REFERENCES `ccinfom_s15_01`.`Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Cinema1`
    FOREIGN KEY (`Cinema_cinema_id`)
    REFERENCES `ccinfom_s15_01`.`Cinema` (`cinema_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_ParkingSlot1`
    FOREIGN KEY (`ParkingSlot_slot_id`)
    REFERENCES `ccinfom_s15_01`.`ParkingSlot` (`slot_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
