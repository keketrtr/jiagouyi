/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.35 : Database - sishu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sishu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sishu`;

/*Table structure for table `tb1_cart` */

DROP TABLE IF EXISTS `tb1_cart`;

CREATE TABLE `tb1_cart` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `customerUuid` int(11) DEFAULT NULL,
  `goodUuid` int(11) DEFAULT NULL,
  `buyNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1_cart` */

/*Table structure for table `tb1_customer` */

DROP TABLE IF EXISTS `tb1_customer`;

CREATE TABLE `tb1_customer` (
  `uuid` int(10) NOT NULL AUTO_INCREMENT,
  `customerId` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `showName` varchar(100) DEFAULT NULL,
  `trueName` varchar(100) DEFAULT NULL,
  `registerTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1_customer` */

/*Table structure for table `tb1_goods` */

DROP TABLE IF EXISTS `tb1_goods`;

CREATE TABLE `tb1_goods` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `imgPath` varchar(500) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1_goods` */

/*Table structure for table `tb1_order` */

DROP TABLE IF EXISTS `tb1_order`;

CREATE TABLE `tb1_order` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `orderTime` varchar(100) DEFAULT NULL,
  `totalMoney` float DEFAULT NULL,
  `saveMoney` float DEFAULT NULL,
  `state` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1_order` */

/*Table structure for table `tb1_orderdetail` */

DROP TABLE IF EXISTS `tb1_orderdetail`;

CREATE TABLE `tb1_orderdetail` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `orderUuid` int(11) DEFAULT NULL,
  `goodsUuid` int(11) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `money` float DEFAULT NULL,
  `saveMoney` float DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1_orderdetail` */

/*Table structure for table `tb1_store` */

DROP TABLE IF EXISTS `tb1_store`;

CREATE TABLE `tb1_store` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `goodsUuid` int(11) DEFAULT NULL,
  `storeNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1_store` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
