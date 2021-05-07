/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.28 : Database - usedbook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`usedbook` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `usedbook`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(22) NOT NULL,
  `accountPass` varchar(22) NOT NULL,
  `accountRole` int(11) NOT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`accountId`,`accountName`,`accountPass`,`accountRole`) values (1,'123456','123456',1),(2,'12345','123456',2);

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminEmail` varchar(22) DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`adminId`,`adminEmail`,`accountId`) values (1,'1677698157@qq.com',1);

/*Table structure for table `bookinfo` */

DROP TABLE IF EXISTS `bookinfo`;

CREATE TABLE `bookinfo` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(60) DEFAULT NULL,
  `bookauthor` varchar(45) DEFAULT NULL,
  `bookpublish` varchar(60) DEFAULT NULL,
  `bookISBN` varchar(45) DEFAULT NULL,
  `bookintroduction` blob,
  `booklanguage` varchar(12) DEFAULT NULL,
  `bookprice` decimal(12,0) DEFAULT NULL,
  `bookpubDate` date DEFAULT NULL,
  `booktypeId` int(11) DEFAULT NULL,
  `booknumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  KEY `bookty` (`booktypeId`),
  CONSTRAINT `bookinfo_ibfk_1` FOREIGN KEY (`booktypeId`) REFERENCES `booktype` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bookinfo` */

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `typeid` int(11) NOT NULL,
  `typename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `booktype` */

insert  into `booktype`(`typeid`,`typename`) values (1,'马克思主义'),(2,'哲学'),(3,'社会科学总论'),(4,'政治法律'),(5,'军事'),(6,'经济'),(7,'文化'),(8,'语言'),(9,'文学'),(10,'艺术'),(11,'历史地理'),(12,'自然科学总论'),(13,'数理科学和化学'),(14,'天文学、地球科学'),(15,'生物科学'),(16,'医药、卫生'),(17,'农业科学'),(18,'工业技术'),(19,'交通运输'),(20,'航空、航天'),(21,'环境科学'),(22,'综合');

/*Table structure for table `lendlist` */

DROP TABLE IF EXISTS `lendlist`;

CREATE TABLE `lendlist` (
  `L_id` int(11) NOT NULL AUTO_INCREMENT,
  `L_bookId` int(11) NOT NULL,
  `L_userId` int(11) NOT NULL,
  `L_lendDate` datetime DEFAULT NULL,
  `L_backDate` datetime DEFAULT NULL,
  PRIMARY KEY (`L_id`),
  KEY `bookidkey` (`L_bookId`),
  KEY `useridkey` (`L_userId`),
  CONSTRAINT `lendlist_ibfk_1` FOREIGN KEY (`L_bookId`) REFERENCES `bookinfo` (`bookid`),
  CONSTRAINT `lendlist_ibfk_2` FOREIGN KEY (`L_userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `lendlist` */

/*Table structure for table `storelog` */

DROP TABLE IF EXISTS `storelog`;

CREATE TABLE `storelog` (
  `putId` int(11) NOT NULL AUTO_INCREMENT,
  `pubDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pubAdminId` int(11) NOT NULL,
  `pubBookId` int(11) NOT NULL,
  PRIMARY KEY (`putId`),
  KEY `fk_admin` (`pubAdminId`),
  KEY `fk_book` (`pubBookId`),
  CONSTRAINT `fk_admin` FOREIGN KEY (`pubAdminId`) REFERENCES `admin` (`adminId`),
  CONSTRAINT `fk_book` FOREIGN KEY (`pubBookId`) REFERENCES `bookinfo` (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `storelog` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userFullName` varchar(11) DEFAULT NULL,
  `userEmail` varchar(20) DEFAULT NULL,
  `userRegistertime` datetime DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`userFullName`,`userEmail`,`userRegistertime`,`accountId`) values (1,'番薯头','1677698157@@.com','2021-05-05 00:00:00',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
