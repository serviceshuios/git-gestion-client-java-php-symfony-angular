-- MySQL dump 10.13  Distrib 5.5.37, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: gestion_client_php
-- ------------------------------------------------------
-- Server version	5.5.37-1

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
-- Table structure for table `BonDeCommande`
--

DROP TABLE IF EXISTS `BonDeCommande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BonDeCommande` (
  `id` varchar(54) NOT NULL,
  `client` varchar(50) NOT NULL,
  `commentaire` tinytext,
  `montant` decimal(6,2) NOT NULL DEFAULT '0.00',
  `date` date NOT NULL,
  `delais` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `client` (`client`),
  KEY `Idx_expiration` (`date`,`delais`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BonDeCommande`
--

LOCK TABLES `BonDeCommande` WRITE;
/*!40000 ALTER TABLE `BonDeCommande` DISABLE KEYS */;
INSERT INTO `BonDeCommande` VALUES ('nmace_001','admin','Premier bdc de Noel',8242.15,'2014-10-10',30),('nmace_002','admin','Second bdc de Noel',42.30,'2014-10-10',30),('rduck_001','riri','On a besoin de pèles et de sauts',200.00,'2014-10-10',30),('rduck_002','riri','Des sacs et des tentes',100.00,'2014-10-10',30),('rduck_003','riri','Ils sont où les livres ?',400.00,'2014-10-10',10),('rduck_004','riri','Des cordes',200.00,'2014-10-10',30),('fduck_001','fifi','Une corde à sauter',8000.00,'2014-10-10',60),('fduck_002','fifi','Un camion qui roule',200.00,'2014-10-10',30),('lduck_001','loulou','Un vélo qui roule',200.00,'2014-10-10',30),('lduck_002','loulou','Une pierre qui roule',200.00,'2014-10-10',30),('pquiroule_001','pir','On m\'a demandé ?',2.00,'2014-10-10',3),('jchirak_001','Chirack','De la potion magique',20.00,'2014-10-10',365);
/*!40000 ALTER TABLE `BonDeCommande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Client` (
  `login` varchar(50) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `commentaire` text,
  `icon` varchar(255) DEFAULT NULL,
  `expiration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`login`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `Unique_denom` (`nom`,`prenom`),
  KEY `Idx_login` (`login`(10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES ('admin','Macé','Noël','f71dbe52628a3f83a77ab494817525c6','Ceci est le compte de l\'administrateur général','admin.png','2015-10-10 12:16:48',1),('alf','onzi','alfred','8cce4963dba0d46453e4ca5e80db5440','Compte de alfred, commercial pour la société M2I','m2i.png','2015-10-10 12:16:48',0),('fifi','Duck','Figerald','724964fc95bfd140f06b84297635d150','Castor junior','castor.png','2015-10-10 12:16:48',0),('Jacko','Chirack','Jeaques','ef426d228915e33765908274a6788d57','Mangez des pommes','fr.png','2014-10-09 12:16:48',0),('jp','peste','jean','fcc78478744ae0fbc33f6bf8fac11282','Compte du DBAdmin pour le debugging','mysql.png','2015-10-10 12:16:48',1),('loulou','Duck','Louis','724964fc95bfd140f06b84297635d150','Castor junior','castor.png','2015-10-10 12:16:48',0),('pir','Quiroule','Pierre','65493b029cdcc927f65a1465e2582751','Sysadmin, pour le debugging','tux.png','2015-10-10 12:16:48',1),('riri','Duck','Richard','724964fc95bfd140f06b84297635d150','Castor junior','castor.png','2015-10-10 12:16:48',0);
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-10 14:18:29
