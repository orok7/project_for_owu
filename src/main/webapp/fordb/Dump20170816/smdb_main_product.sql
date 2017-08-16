-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: smdb_main
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mainPicture` varchar(255) DEFAULT NULL,
  `measurementUnits` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `productGroup_id` int(11) DEFAULT NULL,
  `numberOfRatings` int(11) NOT NULL,
  `rating` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2isoigvh4s5tpg0j8ptgd71l8` (`article`),
  KEY `FKgqw2mtnkx4hn05e3b7u8iyisd` (`productGroup_id`),
  CONSTRAINT `FKgqw2mtnkx4hn05e3b7u8iyisd` FOREIGN KEY (`productGroup_id`) REFERENCES `productgroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'COLR40','Фурнітура для печатки діам. 40 мм.\r\nКраїна виробника - Австрія.','/imgdb/751795343.jpg','SHT','Фурнітура для печатки діам. 40 мм',170,12,2,4.5),(2,'TR4927','Фурнітура для штампу 60х40 мм\r\nКраїна виробника - Австрія.','/imgdb/313456837.jpg','SHT','Фурнітура для штампу 60х40 мм',310,12,0,0),(3,'TR4912','Фурнітура для штампу 47х18 мм\r\nКраїна виробника - Австрія.','/imgdb/579196285.jpg','SHT','Фурнітура для штампу 47х18 мм',155,12,0,0),(4,'COLR50','Фурнітура для печатки діам. 50 мм.\r\nКраїна виробника - Австрія.','/imgdb/751795374.jpg','SHT','Фурнітура для печатки діам. 50 мм',290,12,0,0),(5,'COLR45','Фурнітура для печатки діам. 45 мм.\r\nКраїна виробника - Австрія.','/imgdb/751795348.jpg','SHT','Фурнітура для печатки діам. 45 мм',205,12,0,0),(6,'TR4642','Фурнітура для печатки діам. 42 мм.\r\nКраїна виробника - Австрія.','/imgdb/401205113.png','SHT','Фурнітура для печатки діам. 42 мм',170,12,0,0),(7,'TR4924','Фурнітура для печатки діам. 40 мм.\r\nКраїна виробника - Австрія.','/imgdb/1409343615.jpg','SHT','Фурнітура для печатки діам. 40 мм',170,12,0,0),(8,'COLR17','Фурнітура для печатки діам. 17 мм.\r\nКраїна виробника - Австрія.','/imgdb/670789475.jpg','SHT','Фурнітура для печатки діам. 16 мм',165,12,1,5),(9,'COLMR40','Кишенькова фурнітура для печатки діам. 40 мм\r\nКраїна виробника - Австрія.','/imgdb/-1349878163.jpg','SHT','Кишенькова фурнітура для печатки діам. 40 мм',115,12,0,0),(10,'DJR40','Кишенькова фурнітура для печатки діам. 40 мм\r\nКраїна виробника - Україна.','/imgdb/-165719091.jpg','SHT','Кишенькова фурнітура для печатки діам. 40 мм',150,12,1,5),(11,'COLPR55','Фурнітура для штампу 60х40 мм\r\nКраїна виробника - Австрія.','/imgdb/-1424221668.jpg','SHT','Фурнітура для штампу 60х40 мм',310,12,0,0),(12,'TR4911','Фурнітура для штампу 48х14 мм\r\nКраїна виробника - Австрія.','/imgdb/579196284.jpg','SHT','Фурнітура для штампу 38х14 мм',135,12,0,0),(13,'TR4913','Фурнітура для штампу 58х22 мм\r\nКраїна виробника - Австрія.','/imgdb/579196286.jpg','SHT','Фурнітура для штампу 58х22 мм',210,12,0,0),(14,'PLOMB100','Пломби свинцеві діам. 10 мм 100 шт.\r\nКраїна виробника - Україна.','/imgdb/37242574.jpeg','PAC','Пломби свинцеві діам. 10 мм 100 шт.',65,11,0,0),(15,'PLOMBDRIT100','Дріт для пломбування 100 шт.\r\nКраїна виробника - Україна.','/imgdb/-133903024.png','PAC','Дріт для пломбування 100 шт.',65,11,0,0),(16,'PLOMB10B','Пломбіратор під свинцеві діам. 10 мм\r\nКраїна виробника - Україна.','/imgdb/778778773.jpg','SHT','Пломбіратор під свинцеві діам. 10 мм',250,11,0,0),(17,'PLOMB24L','Пломбіратор під пластилін діам. 24 мм латунь\r\nКраїна виробника - Україна.','/imgdb/-354349070.jpg','SHT','Пломбіратор під пластилін діам. 24 мм латунь',130,11,0,0),(18,'PLOMB24S','Пломбіратор під сургуч діам. 24 мм\r\nКраїна виробника - Україна.','/imgdb/-902034210.jpg','SHT','Пломбіратор під сургуч діам. 24 мм',150,11,0,0),(19,'PECH01','Печатка діам. 40 мм','/imgdb/-1742985642.jpg','SHT','Печатка діам. 40 мм',150,1,0,0),(20,'PECH02','Печатка діам. 40 мм','/imgdb/755030296.jpg','SHT','Печатка діам. 40 мм',150,1,0,0),(21,'PECH03','Печатка діам. 40 мм','/imgdb/-1041921062.jpg','SHT','Печатка діам. 40 мм',150,1,0,0),(22,'PECH04','Печатка діам. 40 мм','/imgdb/10649316.png','SHT','Печатка діам. 40 мм',165,1,0,0),(23,'PECH05','Печатка діам. 40 мм','/imgdb/-1786302042.png','SHT','Печатка діам. 40 мм',180,1,0,0),(24,'PECH07','Печатка діам. 40 мм','/imgdb/360208098.jpg','SHT','Печатка діам. 40 мм',165,1,0,0),(25,'PECH08','Печатка діам. 40 мм','/imgdb/-259928596.gif','SHT','Печатка діам. 40 мм',150,6,0,0),(27,'PECH09','Печатка діам. 40 мм','/imgdb/-2056879954.gif','SHT','Печатка діам. 40 мм',165,6,0,0),(28,'PECH10','Печатка діам. 40 мм','/imgdb/441136005.gif','SHT','Печатка діам. 40 мм',180,6,0,0),(29,'PECH11','Печатка діам. 40 мм','/imgdb/2014029988.jpg','SHT','Печатка діам. 40 мм',180,6,0,0),(30,'PECH12','Печатка діам. 45 мм','/imgdb/-1704983875.jpg','SHT','Печатка діам. 45 мм',190,5,0,0),(31,'PECH13','Печатка діам. 45 мм','/imgdb/793032063.jpg','SHT','Печатка діам. 45 мм',190,5,0,0),(32,'PECH14','Печатка діам. 45 мм','/imgdb/-78797340.gif','SHT','Печатка діам. 45 мм',190,5,0,0),(34,'PECH15','Печатка діам. 16 мм','/imgdb/-1082209856.jpg','SHT','Печатка діам. 16 мм',70,4,0,0),(35,'PECH16','Печатка діам. 19 мм','/imgdb/-381145277.jpg','SHT','Печатка діам. 19 мм',80,4,0,0),(36,'PECH17','Печатка діам. 22 мм','/imgdb/1415806083.jpg','SHT','Печатка діам. 22 мм',80,4,0,0),(37,'SHTMP01','Штамп 60х40 мм','/imgdb/930235509.GIF','SHT','Штамп 60х40 мм',115,3,0,0),(38,'SHTMP02','Штамп 50х30 мм','/imgdb/-1616768264.jpg','SHT','Штамп 50х30 мм',100,3,0,0),(39,'SHTMP03','Штамп 70х55 мм','/imgdb/672070073.GIF','SHT','Штамп 70х55мм',150,3,0,0),(40,'SHTMP04','Штамп 70х55 мм','/imgdb/-1229520105.jpg','SHT','Штамп 70х55мм',150,3,0,0),(41,'SHTMP05','Штамп 56х20мм','/imgdb/-1487685542.jpg','SHT','Штамп 56х20мм',80,3,0,0),(42,'FAX01','Факсиміле 50х30 мм','/imgdb/1746453323.gif','SHT','Факсиміле 50х30 мм',100,7,0,0),(43,'FAX02','Факсиміле 56х20 мм','/imgdb/527650864.jpg','SHT','Факсиміле 56х20 мм',80,7,0,0),(44,'FAX03','Факсиміле 60х40 мм','/imgdb/1549939820.gif','SHT','Факсиміле 60х40 мм',115,7,0,0),(45,'FAX04','Факсиміле 40х40 мм','/imgdb/30030407.png','SHT','Факсиміле 40х40 мм',115,7,0,0),(46,'EXLIB01','Ex Libris 60х40 мм','/imgdb/-223512692.jpg','SHT','Ex Libris 60х40 мм',115,9,0,0),(48,'EXLIB03','Ex Libris 60х40 мм','/imgdb/-94429971.jpg','SHT','Ex Libris 60х40 мм',115,9,0,0),(49,'EXLIB02','Ex Libris 60х40 мм','/imgdb/34652747.jpg','SHT','Ex Libris 60х40 мм',115,9,0,0),(50,'EXLIB04','Ex Libris 60х40 мм','/imgdb/163735468.jpg','SHT','Ex Libris 60х40 мм',115,9,0,0),(51,'EXLIB05','Ex Libris 73х36 мм','/imgdb/-1093172279.gif','SHT','Ex Libris 73х36 мм',145,9,0,0),(52,'EXLIB07','Ex Libris 120х80 мм','/imgdb/550983628.jpg','SHT','Ex Libris 120х80 мм',300,9,0,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-16 23:12:23
