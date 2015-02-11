SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `GeoQuestDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `GeoQuestDB` ;

-- -----------------------------------------------------
-- Table `GeoQuestDB`.`GeoUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GeoQuestDB`.`GeoUser` ;

CREATE  TABLE IF NOT EXISTS `GeoQuestDB`.`GeoUser` (
  `idUser` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) NULL ,
  `password` VARCHAR(50) NULL ,
  `email` VARCHAR(100) NULL ,
  PRIMARY KEY (`idUser`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GeoQuestDB`.`tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GeoQuestDB`.`tour` ;

CREATE  TABLE IF NOT EXISTS `GeoQuestDB`.`tour` (
  `idtour` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `description` VARCHAR(500) NULL ,
  `difficulty` VARCHAR(10) NULL ,
  `badgeURL` VARCHAR(200) NULL ,
  `distance` FLOAT NULL ,
  `User_idUser` INT NOT NULL ,
  PRIMARY KEY (`idtour`) ,
  INDEX `fk_tour_User1` (`User_idUser` ASC) ,
  CONSTRAINT `fk_tour_User1`
    FOREIGN KEY (`User_idUser` )
    REFERENCES `GeoQuestDB`.`GeoUser` (`idUser` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `GeoQuestDB`.`waypoint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GeoQuestDB`.`waypoint` ;

CREATE  TABLE IF NOT EXISTS `GeoQuestDB`.`waypoint` (
  `idwaypoint` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `longitude` VARCHAR(30) NULL ,
  `latitude` VARCHAR(30) NULL ,
  `orderPosition` INT NULL ,
  `tour_idtour` INT NOT NULL ,
  PRIMARY KEY (`idwaypoint`) ,
  INDEX `fk_waypoint_tour1` (`tour_idtour` ASC) ,
  CONSTRAINT `fk_waypoint_tour1`
    FOREIGN KEY (`tour_idtour` )
    REFERENCES `GeoQuestDB`.`tour` (`idtour` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GeoQuestDB`.`souvenir`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GeoQuestDB`.`souvenir` ;

CREATE  TABLE IF NOT EXISTS `GeoQuestDB`.`souvenir` (
  `idsouvenir` INT NOT NULL AUTO_INCREMENT ,
  `souvenirURL` VARCHAR(200) NULL ,
  `downloads` INT NULL ,
  `tour_idtour` INT NOT NULL ,
  PRIMARY KEY (`idsouvenir`) ,
  INDEX `fk_souvenir_tour1` (`tour_idtour` ASC) ,
  CONSTRAINT `fk_souvenir_tour1`
    FOREIGN KEY (`tour_idtour` )
    REFERENCES `GeoQuestDB`.`tour` (`idtour` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GeoQuestDB`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GeoQuestDB`.`question` ;

CREATE  TABLE IF NOT EXISTS `GeoQuestDB`.`question` (
  `digitPosition` INT NOT NULL ,
  `question` VARCHAR(500) NULL ,
  `waypoint_idwaypoint` INT NOT NULL ,
  PRIMARY KEY (`digitPosition`, `waypoint_idwaypoint`) ,
  INDEX `fk_question_waypoint1` (`waypoint_idwaypoint` ASC) ,
  CONSTRAINT `fk_question_waypoint1`
    FOREIGN KEY (`waypoint_idwaypoint` )
    REFERENCES `GeoQuestDB`.`waypoint` (`idwaypoint` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GeoQuestDB`.`GeoUser_completed_tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GeoQuestDB`.`GeoUser_completed_tour` ;

CREATE  TABLE IF NOT EXISTS `GeoQuestDB`.`GeoUser_completed_tour` (
  `GeoUser_idUser` INT NOT NULL ,
  `tour_idtour` INT NOT NULL ,
  `lastCompletionDate` DATE NULL ,
  PRIMARY KEY (`GeoUser_idUser`, `tour_idtour`) ,
  INDEX `fk_GeoUser_has_tour_tour1` (`tour_idtour` ASC) ,
  INDEX `fk_GeoUser_has_tour_GeoUser1` (`GeoUser_idUser` ASC) ,
  CONSTRAINT `fk_GeoUser_has_tour_GeoUser1`
    FOREIGN KEY (`GeoUser_idUser` )
    REFERENCES `GeoQuestDB`.`GeoUser` (`idUser` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_GeoUser_has_tour_tour1`
    FOREIGN KEY (`tour_idtour` )
    REFERENCES `GeoQuestDB`.`tour` (`idtour` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
