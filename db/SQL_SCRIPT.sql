-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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


-- -----------------------------------------------------
-- START OF DATA POPULATION
-- -----------------------------------------------------

INSERT INTO `Movie` (`movie_id`, `movie_title`, `price`, `genre`, `rating`, `duration`, `showDates`, `timeSlots`) VALUES
(1, 'Deadpool & Wolverine', 350.00, 'Action', 4.8, 127, '2025-01-15 18:00:00', '2025-01-15 18:00:00'),
(2, 'Inside Out 2', 300.00, 'Animation', 4.7, 96, '2025-01-16 16:00:00', '2025-01-16 16:00:00'),
(3, 'Dune: Part Two', 400.00, 'Sci-Fi', 4.9, 166, '2025-01-16 20:00:00', '2025-01-16 20:00:00'),
(4, 'Kingdom of the Planet of the Apes', 320.00, 'Action', 4.5, 145, '2025-01-17 19:00:00', '2025-01-17 19:00:00'),
(5, 'Furiosa: A Mad Max Saga', 350.00, 'Action', 4.6, 148, '2025-01-17 21:00:00', '2025-01-17 21:00:00'),
(6, 'Kung Fu Panda 4', 280.00, 'Animation', 4.3, 94, '2025-01-18 13:00:00', '2025-01-18 13:00:00'),
(7, 'Godzilla x Kong: The New Empire', 330.00, 'Sci-Fi', 4.4, 115, '2025-01-18 15:00:00', '2025-01-18 15:00:00'),
(8, 'Civil War', 310.00, 'Thriller', 4.2, 109, '2025-01-19 18:30:00', '2025-01-19 18:30:00'),
(9, 'The Fall Guy', 300.00, 'Action', 4.1, 126, '2025-01-19 21:00:00', '2025-01-19 21:00:00'),
(10, 'A Quiet Place: Day One', 320.00, 'Horror', 4.5, 100, '2025-01-20 20:00:00', '2025-01-20 20:00:00');

INSERT INTO `Cinema` (`cinema_id`, `name`, `location`, `total_slots`) VALUES
(1, 'Santos', 'Juan', '09171111111', 'ABC1234'),
(2, 'Reyes', 'Maria', '09172222222', 'DEF5678'),
(3, 'Cruz', 'Jose', '09173333333', 'GHI9012'),
(4, 'Garcia', 'Ana', '09174444444', 'JKL3456'),
(5, 'Lim', 'Kevin', '09175555555', 'MNO7890'),
(6, 'Tan', 'Jennifer', '09176666666', 'PQR1234'),
(7, 'Dizon', 'Mark', '09177777777', 'STU5678'),
(8, 'Ocampo', 'Sarah', '09178888888', 'VWX9012'),
(9, 'Flores', 'Michael', '09179999999', 'YZA3456'),
(10, 'Gonzales', 'Patricia', '09170000000', 'BCD7890');

INSERT INTO `Customer` (`customer_id`, `last_name`, `first_name`, `phoneNo`, `car_plate`) VALUES
(1, 'Starlight Drive-In', 'Downtown Manila', 50),
(2, 'Moonwalk Cinema', 'Pasay City', 80),
(3, 'Galaxy Park', 'Quezon City', 100);

-- (it depends on Cinema)
INSERT INTO `ParkingSlot` (`slot_id`, `slot_no`, `status`, `Cinema_cinema_id`) VALUES
(1, 101, 1, 1),
(2, 102, 1, 1), 
(3, 103, 1, 1),
(4, 104, 1, 1), 
(5, 201, 1, 2),
(6, 202, 1, 2), 
(7, 203, 1, 2), 
(8, 301, 1, 3),
(9, 302, 1, 3), 
(10, 303, 1, 3);

-- (it depends on all other tables)
-- Note: 'order_status' = 1 (e.g., "Confirmed")
INSERT INTO `Booking` (`booking_id`, `passengers_num`, `total_amount`, `time_slot`, `date`, `order_status`, `Movie_movie_id`, `Customer_customer_id`, `Cinema_cinema_id`, `ParkingSlot_slot_id`) VALUES
(2, 3, 900.00, '2025-01-16 16:00:00', '2025-01-15', 1, 2, 2, 2, 5), -- Cinema 2, Slot 5
(3, 2, 800.00, '2025-01-16 20:00:00', '2025-01-15', 1, 3, 3, 3, 8), -- Cinema 3, Slot 8
(4, 1, 320.00, '2025-01-17 19:00:00', '2025-01-16', 1, 4, 4, 1, 2), -- Cinema 1, Slot 2
(5, 4, 1400.00, '2025-01-17 21:00:00', '2025-01-16', 1, 5, 5, 2, 6), -- Cinema 2, Slot 6
(6, 2, 560.00, '2025-01-18 13:00:00', '2025-01-17', 1, 6, 6, 3, 9), -- Cinema 3, Slot 9
(7, 2, 660.00, '2025-01-18 15:00:00', '2025-01-17', 1, 7, 7, 1, 3), -- Cinema 1, Slot 3
(8, 2, 620.00, '2025-01-19 18:30:00', '2025-01-18', 1, 8, 8, 2, 7), -- Cinema 2, Slot 7
(9, 3, 900.00, '2025-01-19 21:00:00', '2025-01-18', 1, 9, 9, 3, 10), -- Cinema 3, Slot 10
(10, 2, 640.00, '2025-01-20 20:00:00', '2025-01-19', 1, 10, 10, 1, 4); -- Cinema 1, Slot 4

-- -----------------------------------------------------
-- END OF DATA POPULATION
-- -----------------------------------------------------

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;