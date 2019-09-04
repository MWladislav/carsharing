-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: carSharing
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `idAddress` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `house_number` int(11) NOT NULL,
  `flat_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'adminland','admincity','adminstreet',1,1),(2,'testcountry','testcity','teststreet',9,9),(3,'Belarus','Grodno','Vrublevskogo',38,32);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carProfile`
--

DROP TABLE IF EXISTS `carProfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `carProfile` (
  `idCar` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `body_type` varchar(45) NOT NULL,
  `engine_type` varchar(45) NOT NULL,
  `year_of_issue` int(11) NOT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idCar`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carProfile`
--

LOCK TABLES `carProfile` WRITE;
/*!40000 ALTER TABLE `carProfile` DISABLE KEYS */;
INSERT INTO `carProfile` VALUES (1,'honda','civic','sedan','fuel',2010,1),(2,'audi','r8','sedan','electric',2016,1),(3,'lada','priora','sedan','fuel',1980,1),(4,'testman','testmodel','wagon','fuel',1999,1);
/*!40000 ALTER TABLE `carProfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carimage`
--

DROP TABLE IF EXISTS `carimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `carimage` (
  `idCarImage` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(45) NOT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`idCarImage`,`car_id`),
  KEY `CarImageToCar_idx` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carimage`
--

LOCK TABLES `carimage` WRITE;
/*!40000 ALTER TABLE `carimage` DISABLE KEYS */;
INSERT INTO `carimage` VALUES (1,'/resources/imgs/audiR8_1.jpg',2),(2,'/resources/imgs/audiR8_2.jpg',2),(3,'/resources/imgs/audiR8_3.jpg',2),(4,'/resources/imgs/hondaCivic_1.png',1),(5,'/resources/imgs/hondaCivic_2.jpg',1),(6,'/resources/imgs/ladaPriora_1.jpg',3),(7,'/resources/imgs/testCar_1.jpg',4);
/*!40000 ALTER TABLE `carimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `confirmation_date` timestamp(2) NULL DEFAULT NULL,
  `payment_date` timestamp(2) NULL DEFAULT NULL,
  `orderDuration` int(11) NOT NULL,
  `order_status` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`,`user_id`,`car_id`),
  KEY `OrderToUser_idx` (`user_id`),
  KEY `OrderToCar_idx` (`car_id`),
  CONSTRAINT `OrderToCar` FOREIGN KEY (`car_id`) REFERENCES `carProfile` (`idcar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OrderToUser` FOREIGN KEY (`user_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2000-12-31 21:00:00.00','2000-12-31 21:00:00.00',6,'complete',200,1,3),(2,'2005-05-04 21:00:00.00','2005-05-04 21:00:00.00',10,'complete',123,1,2),(3,'2011-09-07 21:00:00.00','2011-09-07 21:00:00.00',2,'complete',123,2,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderadditionalinfo`
--

DROP TABLE IF EXISTS `orderadditionalinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orderadditionalinfo` (
  `idOrderAdditionalInfo` int(11) NOT NULL AUTO_INCREMENT,
  `info_details` varchar(45) NOT NULL,
  `payment_for_violation` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrderAdditionalInfo`),
  KEY `fk_cci_to_order_idx` (`order_id`),
  CONSTRAINT `fk_cci_to_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`idorder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderadditionalinfo`
--

LOCK TABLES `orderadditionalinfo` WRITE;
/*!40000 ALTER TABLE `orderadditionalinfo` DISABLE KEYS */;
INSERT INTO `orderadditionalinfo` VALUES (1,'light carProfile crash',100,1);
/*!40000 ALTER TABLE `orderadditionalinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passportdata`
--

DROP TABLE IF EXISTS `passportdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `passportdata` (
  `idPassportData` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `birth_date` date NOT NULL,
  `passport_number` varchar(45) NOT NULL,
  `identification_number` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `address_idAddress` int(11) NOT NULL,
  PRIMARY KEY (`idPassportData`,`address_idAddress`),
  KEY `fk_passportdata_address1_idx` (`address_idAddress`),
  CONSTRAINT `fk_passportdata_address1` FOREIGN KEY (`address_idAddress`) REFERENCES `address` (`idaddress`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passportdata`
--

LOCK TABLES `passportdata` WRITE;
/*!40000 ALTER TABLE `passportdata` DISABLE KEYS */;
INSERT INTO `passportdata` VALUES (1,'a','d','min','1999-05-22','1337','1337228','male',1),(3,'test_name','test_l_name','test_patr','2000-01-01','351213b21','123510','female',2),(4,'andrey','yaskelevich','olegovich','1998-05-16','231cz23q2321','31021160','male',3);
/*!40000 ALTER TABLE `passportdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `idRole` int(11) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(150) NOT NULL,
  `email` varchar(45) NOT NULL,
  `active` tinyint(1) DEFAULT '0',
  `passportdata_idPassportData` int(11) DEFAULT NULL,
  `role_idRole` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`role_idRole`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_passportdata1_idx` (`passportdata_idPassportData`),
  KEY `fk_user_role1_idx` (`role_idRole`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_idRole`) REFERENCES `role` (`idrole`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$11$MJtj2BFcyPyJJUMO0fYjoeGF1RgaD1uBTCSYyIDkfRrFVIjX329b2','admin@mail.ru',1,1,2),(2,'testuser1','$2a$11$X322vbhLNKHzwrl12g8csuhGeM6/AW/LNgWt.XjImA3iuwIN3us0K','test@mail.ru',1,2,1),(3,'testuser2','$2a$11$BDG.Bo/VKwrT38AsZdL3Zu4pNhAgnO7BSVQq6ppBwiNWYdKIfvVWy','test1@mail.ru',1,3,1),(6,'testuser3','$2a$11$e5E.WfdhBjy5Mq565FDxR.UqCY0krQZ6lLMAUls6lHqidISBOrvKK','test3@mail.ru',1,NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-20 17:17:25
