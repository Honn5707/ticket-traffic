-- MySQL dump 10.13  Distrib 8.0.45, for Linux (aarch64)
--
-- Host: localhost    Database: ticket_db
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_reserved` bit(1) NOT NULL,
  `seat_number` int NOT NULL,
  `version` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,_binary '\0',1,0),(2,_binary '\0',2,0),(3,_binary '\0',3,0),(4,_binary '\0',4,0),(5,_binary '\0',5,0),(6,_binary '\0',6,0),(7,_binary '\0',7,0),(8,_binary '\0',8,0),(9,_binary '\0',9,0),(10,_binary '\0',10,0),(11,_binary '\0',1,0),(12,_binary '\0',2,0),(13,_binary '\0',3,0),(14,_binary '\0',4,0),(15,_binary '\0',5,0),(16,_binary '\0',6,0),(17,_binary '\0',7,0),(18,_binary '\0',8,0),(19,_binary '\0',9,0),(20,_binary '\0',10,0),(21,_binary '\0',1,0),(22,_binary '\0',2,0),(23,_binary '\0',3,0),(24,_binary '\0',4,0),(25,_binary '\0',5,0),(26,_binary '\0',6,0),(27,_binary '\0',7,0),(28,_binary '\0',8,0),(29,_binary '\0',9,0),(30,_binary '\0',10,0),(31,_binary '\0',11,0),(32,_binary '\0',12,0),(33,_binary '\0',13,0),(34,_binary '\0',14,0),(35,_binary '\0',15,0),(36,_binary '\0',16,0),(37,_binary '\0',17,0),(38,_binary '\0',18,0),(39,_binary '\0',19,0),(40,_binary '\0',20,0),(41,_binary '\0',21,0),(42,_binary '\0',22,0),(43,_binary '\0',23,0),(44,_binary '\0',24,0),(45,_binary '\0',25,0),(46,_binary '\0',26,0),(47,_binary '\0',27,0),(48,_binary '\0',28,0),(49,_binary '\0',29,0),(50,_binary '\0',30,0),(51,_binary '\0',31,0),(52,_binary '\0',32,0),(53,_binary '\0',33,0),(54,_binary '\0',34,0),(55,_binary '\0',35,0),(56,_binary '\0',36,0),(57,_binary '\0',37,0),(58,_binary '\0',38,0),(59,_binary '\0',39,0),(60,_binary '\0',40,0),(61,_binary '\0',41,0),(62,_binary '\0',42,0),(63,_binary '\0',43,0),(64,_binary '\0',44,0),(65,_binary '\0',45,0),(66,_binary '\0',46,0),(67,_binary '\0',47,0),(68,_binary '\0',48,0),(69,_binary '\0',49,0),(70,_binary '\0',50,0),(71,_binary '\0',51,0),(72,_binary '\0',52,0),(73,_binary '\0',53,0),(74,_binary '\0',54,0),(75,_binary '\0',55,0),(76,_binary '\0',56,0),(77,_binary '\0',57,0),(78,_binary '\0',58,0),(79,_binary '\0',59,0),(80,_binary '\0',60,0),(81,_binary '\0',61,0),(82,_binary '\0',62,0),(83,_binary '\0',63,0),(84,_binary '\0',64,0),(85,_binary '\0',65,0),(86,_binary '\0',66,0),(87,_binary '\0',67,0),(88,_binary '\0',68,0),(89,_binary '\0',69,0),(90,_binary '\0',70,0),(91,_binary '\0',71,0),(92,_binary '\0',72,0),(93,_binary '\0',73,0),(94,_binary '\0',74,0),(95,_binary '\0',75,0),(96,_binary '\0',76,0),(97,_binary '\0',77,0),(98,_binary '\0',78,0),(99,_binary '\0',79,0),(100,_binary '\0',80,0),(101,_binary '\0',81,0),(102,_binary '\0',82,0),(103,_binary '\0',83,0),(104,_binary '\0',84,0),(105,_binary '\0',85,0),(106,_binary '\0',86,0),(107,_binary '\0',87,0),(108,_binary '\0',88,0),(109,_binary '\0',89,0),(110,_binary '\0',90,0),(111,_binary '\0',91,0),(112,_binary '\0',92,0),(113,_binary '\0',93,0),(114,_binary '\0',94,0),(115,_binary '\0',95,0),(116,_binary '\0',96,0),(117,_binary '\0',97,0),(118,_binary '\0',98,0),(119,_binary '\0',99,0),(120,_binary '\0',100,0);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-25 13:16:29
