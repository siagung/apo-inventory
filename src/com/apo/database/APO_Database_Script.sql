SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `apo_db` DEFAULT CHARACTER SET latin1 ;
USE `apo_db` ;

-- -----------------------------------------------------
-- Table `apo_db`.`secret_question`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`secret_question` (
  `secret_id` INT(11) NOT NULL ,
  `question` VARCHAR(60) NULL DEFAULT NULL ,
  PRIMARY KEY (`secret_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`privilege`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`privilege` (
  `privilege_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `privilege_desc` TEXT NOT NULL ,
  PRIMARY KEY (`privilege_id`) ,
  UNIQUE INDEX `privilege_id_UNIQUE` (`privilege_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`user_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`user_type` (
  `type_name` VARCHAR(25) NOT NULL ,
  `privilege_id` INT(11) NOT NULL ,
  `privilege_toggle` INT(11) NOT NULL ,
  PRIMARY KEY (`type_name`, `privilege_id`) ,
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC) ,
  INDEX `privilege_id` (`privilege_id` ASC) ,
  CONSTRAINT `privilege_id`
    FOREIGN KEY (`privilege_id` )
    REFERENCES `apo_db`.`privilege` (`privilege_id` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`employee` (
  `employee_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `head` INT(11) NOT NULL DEFAULT '0' ,
  `deleted` INT(11) NOT NULL DEFAULT '0' ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(12) NOT NULL ,
  `user_type` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `middle_name` VARCHAR(45) NULL DEFAULT NULL ,
  `sss_id` INT(11) NOT NULL ,
  `employed_status` VARCHAR(20) NOT NULL ,
  `gender` VARCHAR(6) NOT NULL ,
  `date_hired` DATE NOT NULL ,
  `date_fired` DATE NULL DEFAULT NULL ,
  `secret_id` INT(11) NOT NULL ,
  `secret_answer` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`employee_id`, `revision_id`) ,
  INDEX `user_type` (`user_type` ASC) ,
  INDEX `secret_id` (`secret_id` ASC) ,
  CONSTRAINT `secret_id`
    FOREIGN KEY (`secret_id` )
    REFERENCES `apo_db`.`secret_question` (`secret_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_type`
    FOREIGN KEY (`user_type` )
    REFERENCES `apo_db`.`user_type` (`type_name` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`announcement_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`announcement_type` (
  `announcement_type_id` INT(11) NOT NULL ,
  `announcement_type_name` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`announcement_type_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`announcement`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`announcement` (
  `announce_id` INT(11) NOT NULL ,
  `announce_type_id` INT(11) NOT NULL ,
  `message` TEXT NOT NULL ,
  `employee_id` INT(11) NOT NULL ,
  PRIMARY KEY (`announce_id`) ,
  INDEX `doer` (`employee_id` ASC) ,
  INDEX `announcement_type` (`announce_type_id` ASC) ,
  CONSTRAINT `doer`
    FOREIGN KEY (`employee_id` )
    REFERENCES `apo_db`.`employee` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `announcement_type`
    FOREIGN KEY (`announce_type_id` )
    REFERENCES `apo_db`.`announcement_type` (`announcement_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer` (
  `customer_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `head` INT(11) NOT NULL DEFAULT '0' ,
  `deleted` INT(11) NOT NULL DEFAULT '0' ,
  `name` VARCHAR(45) NOT NULL ,
  `representative` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`customer_id`, `revision_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer_address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer_address` (
  `customer_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `address` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`customer_id`, `revision_id`, `address`) ,
  INDEX `customer_id` (`customer_id` ASC, `revision_id` ASC) ,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id` , `revision_id` )
    REFERENCES `apo_db`.`customer` (`customer_id` , `revision_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`kind`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`kind` (
  `kindName` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`kindName`) ,
  UNIQUE INDEX `kindName_UNIQUE` (`kindName` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer_contact`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer_contact` (
  `customer_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `kind` VARCHAR(20) NOT NULL ,
  `detail` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`customer_id`, `revision_id`, `kind`, `detail`) ,
  INDEX `id` (`customer_id` ASC, `revision_id` ASC) ,
  INDEX `contact_kind` (`kind` ASC) ,
  CONSTRAINT `contact_kind`
    FOREIGN KEY (`kind` )
    REFERENCES `apo_db`.`kind` (`kindName` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `id`
    FOREIGN KEY (`customer_id` , `revision_id` )
    REFERENCES `apo_db`.`customer` (`customer_id` , `revision_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer_invoice`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer_invoice` (
  `invoice_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `customer_id` INT(11) NOT NULL ,
  `head` INT(11) NOT NULL DEFAULT '0' ,
  `deleted` INT(11) NOT NULL DEFAULT '0' ,
  `invoice_num` VARCHAR(20) NOT NULL ,
  `currency` VARCHAR(20) NOT NULL DEFAULT 'PHP' ,
  `exchange_rate` DECIMAL(2,0) NOT NULL DEFAULT '1' ,
  `order_date` DATE NOT NULL ,
  `employee_id` INT(11) NOT NULL ,
  `order_type` VARCHAR(10) NOT NULL ,
  `payment_terms` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`invoice_id`, `revision_id`) ,
  INDEX `customer_id_order` (`customer_id` ASC) ,
  INDEX `employee_id_order` (`employee_id` ASC) ,
  CONSTRAINT `customer_id_order`
    FOREIGN KEY (`customer_id` )
    REFERENCES `apo_db`.`customer` (`customer_id` )
    ON UPDATE CASCADE,
  CONSTRAINT `employee_id_order`
    FOREIGN KEY (`employee_id` )
    REFERENCES `apo_db`.`employee` (`employee_id` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`product_category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`product_category` (
  `category_id` INT(11) NOT NULL ,
  `category_name` VARCHAR(45) NOT NULL ,
  `category_desc` TEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`category_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`product`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`product` (
  `product_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `head` INT(11) NOT NULL DEFAULT '0' ,
  `deleted` INT(11) NOT NULL DEFAULT '0' ,
  `product_name` VARCHAR(45) NOT NULL ,
  `model` VARCHAR(45) NULL DEFAULT NULL ,
  `brand` VARCHAR(45) NULL DEFAULT NULL ,
  `product_desc` TEXT NULL DEFAULT NULL ,
  `category_id` INT(11) NOT NULL ,
  `srprice` DECIMAL(2,0) NOT NULL ,
  `stock` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `unit` VARCHAR(5) NOT NULL DEFAULT 'PCS.' ,
  PRIMARY KEY (`product_id`, `revision_id`) ,
  INDEX `category` (`category_id` ASC) ,
  CONSTRAINT `category`
    FOREIGN KEY (`category_id` )
    REFERENCES `apo_db`.`product_category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer_invoice_item`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer_invoice_item` (
  `order_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  `quantity` DECIMAL(2,0) NOT NULL ,
  `soldPrice` DECIMAL(2,0) NOT NULL ,
  PRIMARY KEY (`order_id`, `revision_id`, `product_id`) ,
  INDEX `customer_order` (`order_id` ASC, `revision_id` ASC) ,
  INDEX `product_customer_item` (`product_id` ASC) ,
  CONSTRAINT `customer_order`
    FOREIGN KEY (`order_id` , `revision_id` )
    REFERENCES `apo_db`.`customer_invoice` (`invoice_id` , `revision_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_customer_item`
    FOREIGN KEY (`product_id` )
    REFERENCES `apo_db`.`product` (`product_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer_order_payment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer_order_payment` (
  `payment_id` INT(11) NOT NULL ,
  `order_id` INT(11) NOT NULL ,
  `mode_of_payment` VARCHAR(10) NOT NULL ,
  `amount_paid` DECIMAL(2,0) NOT NULL ,
  `check_number` INT(11) NULL DEFAULT NULL ,
  `check_date` DATE NULL DEFAULT NULL ,
  `check_location` VARCHAR(45) NULL DEFAULT NULL ,
  `date_paid` DATE NOT NULL ,
  PRIMARY KEY (`payment_id`, `order_id`) ,
  INDEX `customer_order_payment` (`order_id` ASC) ,
  CONSTRAINT `customer_order_payment`
    FOREIGN KEY (`order_id` )
    REFERENCES `apo_db`.`customer_invoice` (`invoice_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`customer_visit`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`customer_visit` (
  `visit_id` INT(11) NOT NULL ,
  `visit_date` DATE NOT NULL ,
  `customer_id` INT(11) NOT NULL ,
  `employee_id` INT(11) NOT NULL ,
  `invoice_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`visit_id`) ,
  INDEX `customer_in_visit` (`customer_id` ASC) ,
  INDEX `employee_in_visit` (`employee_id` ASC) ,
  INDEX `invoice_in_visit` (`invoice_id` ASC) ,
  CONSTRAINT `customer_in_visit`
    FOREIGN KEY (`customer_id` )
    REFERENCES `apo_db`.`customer` (`customer_id` )
    ON UPDATE CASCADE,
  CONSTRAINT `employee_in_visit`
    FOREIGN KEY (`employee_id` )
    REFERENCES `apo_db`.`employee` (`employee_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `invoice_in_visit`
    FOREIGN KEY (`invoice_id` )
    REFERENCES `apo_db`.`customer_invoice` (`invoice_id` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`employee_address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`employee_address` (
  `employee_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `address` VARCHAR(140) NOT NULL ,
  PRIMARY KEY (`employee_id`, `revision_id`, `address`) ,
  INDEX `employee_id` (`employee_id` ASC) ,
  CONSTRAINT `employee_id`
    FOREIGN KEY (`employee_id` )
    REFERENCES `apo_db`.`employee` (`employee_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`employee_contact`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`employee_contact` (
  `employee_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `kind` VARCHAR(20) NOT NULL ,
  `detail` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`employee_id`, `revision_id`, `kind`, `detail`) ,
  INDEX `kind` (`kind` ASC) ,
  INDEX `employee` (`employee_id` ASC) ,
  CONSTRAINT `employee`
    FOREIGN KEY (`employee_id` )
    REFERENCES `apo_db`.`employee` (`employee_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `kind`
    FOREIGN KEY (`kind` )
    REFERENCES `apo_db`.`kind` (`kindName` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`supplier`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`supplier` (
  `supplier_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `head` INT(11) NOT NULL DEFAULT '0' ,
  `deleted` INT(11) NOT NULL DEFAULT '0' ,
  `name` VARCHAR(45) NOT NULL ,
  `representative` VARCHAR(60) NULL DEFAULT NULL ,
  PRIMARY KEY (`supplier_id`, `revision_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`supplier_address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`supplier_address` (
  `supplier_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `address` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`supplier_id`, `revision_id`, `address`) ,
  INDEX `supplier` (`supplier_id` ASC, `revision_id` ASC) ,
  CONSTRAINT `supplier`
    FOREIGN KEY (`supplier_id` , `revision_id` )
    REFERENCES `apo_db`.`supplier` (`supplier_id` , `revision_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`supplier_contact`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`supplier_contact` (
  `supplier_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `kind` VARCHAR(20) NOT NULL ,
  `detail` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`supplier_id`, `revision_id`, `kind`, `detail`) ,
  INDEX `supplier_info` (`supplier_id` ASC, `revision_id` ASC) ,
  INDEX `supplier_contact_kind` (`kind` ASC) ,
  CONSTRAINT `supplier_info`
    FOREIGN KEY (`supplier_id` , `revision_id` )
    REFERENCES `apo_db`.`supplier` (`supplier_id` , `revision_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `supplier_contact_kind`
    FOREIGN KEY (`kind` )
    REFERENCES `apo_db`.`kind` (`kindName` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`supplier_invoice`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`supplier_invoice` (
  `invoice_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `head` INT(11) NOT NULL DEFAULT '0' ,
  `deleted` INT(11) NOT NULL DEFAULT '0' ,
  `supplier_id` INT(11) NOT NULL ,
  `invoice_num` VARCHAR(20) NOT NULL ,
  `currency` VARCHAR(20) NOT NULL DEFAULT 'PHP' ,
  `exchange_rate` DECIMAL(2,0) NOT NULL DEFAULT '1' ,
  `order_date` DATE NOT NULL ,
  PRIMARY KEY (`invoice_id`, `revision_id`) ,
  INDEX `supplier_id` (`supplier_id` ASC) ,
  CONSTRAINT `supplier_id`
    FOREIGN KEY (`supplier_id` )
    REFERENCES `apo_db`.`supplier` (`supplier_id` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `apo_db`.`supplier_invoice_item`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `apo_db`.`supplier_invoice_item` (
  `invoice_id` INT(11) NOT NULL ,
  `revision_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  `po_id` VARCHAR(45) NOT NULL ,
  `quantity` DECIMAL(2,0) NOT NULL ,
  `sold_price` DECIMAL(2,0) NOT NULL ,
  PRIMARY KEY (`invoice_id`, `revision_id`, `product_id`, `po_id`) ,
  INDEX `order_id` (`invoice_id` ASC) ,
  INDEX `product_id` (`product_id` ASC) ,
  CONSTRAINT `order_id`
    FOREIGN KEY (`invoice_id` , `revision_id` )
    REFERENCES `apo_db`.`supplier_invoice` (`invoice_id` , `revision_id` )
    ON UPDATE CASCADE,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id` )
    REFERENCES `apo_db`.`product` (`product_id` )
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
