-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: mywallet.cu2iydrj9wq8.eu-north-1.rds.amazonaws.com    Database: mobile_wallet
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'Kuldeep','Bishnoi','9416375330','kuldeepbishnoi.2001@gmail.com','$10$kcd8h.fanglVxiGbb6U0V.pqO40d3lVqv.OacxeqHaMqzu6c1VPVG','USER'),(9,'XYZ','ABC','9007533000','kb2001@gmail.com','$10$kcd8h.fanglVxiGbb6U0V.pqO40d3lVqv.OacxeqHaMqzu6c1VPVG','USER'),(10,'afdfsf','dfsdf','9416375440','xyz@gmail.com','$10$kcd8h.fanglVxiGbb6U0V.pqO40d3lVqv.OacxeqHaMqzu6c1VPVG','USER'),(11,'afdfsf','dfsdf','9416375430','xyz@gmayl.com','$2a$10$Qx5aNvbucnrQR/5bVElbGuzZWPVAAojOhuqDDy9lei.54QCIYKwWq','USER'),(12,'from','hi','9679967988','from@gmail.com','$2a$10$.YC8dhzMgcgnqOLb2JJZOuw8gOX2JWC.V82S5ka/cxMJjao8/Yg5m','USER'),(13,'nita','Amabani','9485749097','premium@gmail.com','$2a$10$nrgIFbygmUPG.qL0uxNy2Ol9d/MjmxuOO/TdiUk.uI4JHx6AQ.21G','USER'),(14,'nit','Amaban','9485749096','premium@gmail.coa','$2a$10$CwrY3vdZBR5us3rBoHHT4eiZk1PF8HRZiAN1yZkpaByeeGMyKHm1i','USER'),(15,'ni','Amaba','9485749095','premium@gmaal.coa','$2a$10$mKhoQmgnlMCxTkg7cXloXOkdjQ8mqq90vGSzCwB4xKTiv6pWcvoQG','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-14 14:19:07
