-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jjl
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES gbk */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jjlbill`
--

DROP TABLE IF EXISTS `jjlbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlbill` (
  `userid` int(10) unsigned NOT NULL,
  `orderNo` varchar(50) DEFAULT NULL,
  `shopname` varchar(500) DEFAULT NULL,
  `payAmount` varchar(100) DEFAULT NULL,
  `payType` varchar(50) DEFAULT NULL,
  `region` varchar(500) DEFAULT NULL,
  `DeviceNo` varchar(100) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlbill`
--

LOCK TABLES `jjlbill` WRITE;
/*!40000 ALTER TABLE `jjlbill` DISABLE KEYS */;
INSERT INTO `jjlbill` VALUES (16,'No.alipay1490519344801','番禺桥南店','15','alipay','番禺区','JJL00001','2017-03-26 17:09:47'),(16,'No.alipay1490519427284','番禺桥南店','10','alipay','番禺区','JJL00001','2017-03-26 17:11:03'),(16,'No.alipay1490538494074','番禺桥南店','10','alipay','番禺区','JJL00001','2017-03-26 22:29:24'),(16,'No.alipay1490539123647','番禺桥南店','10','alipay','番禺区','JJL00001','2017-03-26 22:39:04'),(17,'No.alipay1490580655251','广州番禺广场店','15','alipay','番禺区','JJL00001','2017-03-27 10:11:38'),(17,'No.alipay1490587814496','广州番禺广场店','10','alipay','番禺区','JJL00001','2017-03-27 12:10:35'),(17,'No.alipay1490587929512','广州番禺广场店','15','alipay','番禺区','JJL00001','2017-03-27 12:12:35'),(17,'No.alipay1490588116928','广州番禺广场店','2','alipay','番禺区','JJL00001','2017-03-27 12:15:32'),(17,'No.alipay1490590606014','广州番禺广场店','2','alipay','番禺区','JJL00001','2017-03-27 12:57:11'),(17,'NO.cash1490622646689','广州番禺广场店','15','现金','番禺区','JJL00001','2017-03-27 21:50:46'),(17,'No.alipay1490663328020','广州番禺广场店','15','alipay','番禺区','JJL00001','2017-03-28 09:09:25'),(17,'NO.cash1490663419906','广州番禺广场店','10','现金','番禺区','JJL00001','2017-03-28 09:10:19'),(17,'NO.cash1490663531951','广州番禺广场店','10','现金','番禺区','JJL00001','2017-03-28 09:12:11'),(17,'NO.cash1490663620611','广州番禺广场店','15','现金','番禺区','JJL00001','2017-03-28 09:13:40'),(17,'No.alipay1490682077850','广州番禺广场店','15','alipay','番禺区','JJL00001','2017-03-28 14:21:33'),(18,'NO.cash1490700686303','广州花城广场店','10','现金','天河区','JJL00001','2017-03-28 19:31:26'),(18,'No.alipay1490705673622','广州花城广场店','15','alipay','天河区','JJL00001','2017-03-28 20:54:54'),(16,'No.alipay1490723741057','番禺桥南店','1.0','alipay','番禺区','JJL00001','2017-03-29 01:56:07'),(16,'No.alipay1490723954410','番禺桥南店','1.5','alipay','番禺区','JJL00001','2017-03-29 01:59:36'),(18,'No.alipay1490743999654','广州花城广场店','0.02','alipay','天河区','JJL00001','2017-03-29 07:34:20'),(18,'No.alipay1490745960673','广州花城广场店','0.01','alipay','天河区','JJL00001','2017-03-29 08:06:36'),(18,'No.alipay1490746035564','广州花城广场店','0.02','alipay','天河区','JJL00001','2017-03-29 08:07:31'),(18,'No.alipay1490746134720','广州花城广场店','0.02','alipay','天河区','JJL00001','2017-03-29 08:09:55'),(18,'NO.cash1490746886489','广州花城广场店','15.0','cash','天河区','JJL00001','2017-03-29 08:21:26'),(18,'NO.cash1490746945862','广州花城广场店','10.0','cash','天河区','JJL00001','2017-03-29 08:22:25'),(18,'No.alipay1490747970039','广州花城广场店','15.0','alipay','天河区','JJL00001','2017-03-29 08:39:50'),(18,'No.alipay1490747993657','广州花城广场店','15.0','alipay','天河区','JJL00001','2017-03-29 08:40:04'),(18,'NO.cash1490748221885','广州花城广场店','15.0','cash','天河区','JJL00001','2017-03-29 08:43:41'),(18,'No.alipay1490760024131','广州花城广场店','15.0','alipay','天河区','JJL00001','2017-03-29 12:00:57'),(18,'No.alipay1490760096613','广州花城广场店','10.0','alipay','天河区','JJL00001','2017-03-29 12:01:52'),(18,'No.alipay1490763570301','广州花城广场店','2.0','alipay','天河区','JJL00001','2017-03-29 13:00:05'),(18,'No.alipay1490763641258','广州花城广场店','1.0','alipay','天河区','JJL00001','2017-03-29 13:00:56'),(18,'NO.cash1490773943292','广州花城广场店','200.0','cash','天河区','JJL00001','2017-03-29 15:52:23'),(18,'No.alipay1490780503605','广州花城广场店','1.0','alipay','天河区','JJL00001','2017-03-29 17:42:19'),(17,'NO.cash1490797339644','广州番禺广场店','15.0','cash','番禺区','JJL00001','2017-03-29 22:22:19'),(17,'NO.cash1490797378444','广州番禺广场店','10.0','cash','番禺区','JJL00001','2017-03-29 22:22:58'),(17,'NO.cash1490798034196','广州番禺广场店','12.0','cash','番禺区','JJL00001','2017-03-29 22:33:54'),(17,'NO.cash1490798082965','广州番禺广场店','8.0','cash','番禺区','JJL00001','2017-03-29 22:34:42'),(17,'No.alipay1490802989219','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-29 23:57:40'),(17,'No.alipay1490803096823','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-29 23:59:47'),(17,'No.alipay1490803527218','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:06:22'),(17,'No.alipay1490803604160','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:06:59'),(17,'No.alipay1490803627124','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:07:27'),(16,'No.alipay1490803971763','番禺桥南店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:13:12'),(17,'No.alipay1490804366964','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:19:57'),(17,'No.alipay1490804463710','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:21:19'),(17,'No.alipay1490804483316','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:21:38'),(17,'No.alipay1490805536632','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:39:27'),(17,'No.alipay1490805575681','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:39:46'),(17,'No.alipay1490805864961','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:44:50'),(17,'No.alipay1490805890821','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:45:01'),(17,'No.alipay1490805906402','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:45:21'),(17,'No.alipay1490805931481','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:45:42'),(17,'No.alipay1490806078213','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:48:13'),(17,'No.alipay1490806098867','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:48:29'),(17,'No.alipay1490806119638','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:48:50'),(17,'No.alipay1490806135727','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:49:06'),(16,'No.alipay1490806627309','番禺桥南店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:57:27'),(16,'No.alipay1490806677589','番禺桥南店','0.02','alipay','番禺区','JJL00002','2017-03-30 00:58:07'),(16,'No.alipay1490806694377','番禺桥南店','0.01','alipay','番禺区','JJL00002','2017-03-30 00:58:24'),(17,'No.alipay1490806972935','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:03:08'),(17,'No.alipay1490806991728','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:03:22'),(17,'No.alipay1490807011170','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:03:41'),(17,'No.alipay1490807025307','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:03:55'),(17,'No.alipay1490807039637','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:04:14'),(17,'No.alipay1490807058555','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:04:28'),(17,'No.alipay1490807101264','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:05:11'),(17,'No.alipay1490807117195','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:05:27'),(17,'No.alipay1490807133195','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:05:43'),(17,'No.alipay1490807146964','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:05:57'),(17,'No.alipay1490807160425','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:06:10'),(17,'No.alipay1490807173964','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:06:24'),(17,'No.alipay1490807187485','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:06:37'),(17,'No.alipay1490807202193','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:06:52'),(17,'No.alipay1490807223353','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 01:07:13'),(17,'No.alipay1490807236523','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:07:26'),(17,'No.alipay1490807249751','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 01:07:40'),(17,'No.alipay1490834977349','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 08:50:07'),(17,'No.alipay1490835018480','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 08:50:28'),(17,'No.alipay1490835034231','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:50:44'),(17,'No.alipay1490835047399','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:50:57'),(17,'No.alipay1490835063147','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:51:13'),(17,'No.alipay1490835081066','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:51:31'),(17,'No.alipay1490835097863','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:51:48'),(17,'No.alipay1490835112751','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:52:03'),(17,'No.alipay1490835126999','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:52:17'),(17,'No.alipay1490835145487','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:52:35'),(17,'No.alipay1490835159745','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 08:52:50'),(17,'NO.cash1490835911591','广州番禺广场店','15.0','cash','番禺区','JJL00002','2017-03-30 09:05:11'),(17,'NO.cash1490835949057','广州番禺广场店','10.0','cash','番禺区','JJL00002','2017-03-30 09:05:49'),(17,'No.alipay1490849213592','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 12:47:38'),(17,'No.alipay1490849262961','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 12:47:53'),(17,'No.alipay1490849278982','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 12:48:09'),(17,'No.alipay1490849295030','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 12:48:25'),(17,'No.alipay1490849310209','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-30 12:48:40'),(17,'NO.cash1490857984348','广州番禺广场店','10.0','cash','番禺区','JJL00002','2017-03-30 15:13:04'),(17,'No.alipay1490858019944','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 15:14:05'),(17,'No.alipay1490858053423','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 15:14:24'),(17,'No.alipay1490858979059','广州番禺广场店','0.01','alipay','番禺区','JJL00002','2017-03-30 15:30:29'),(17,'NO.cash1490859614746','广州番禺广场店','10.0','cash','番禺区','JJL00002','2017-03-30 15:40:14'),(17,'NO.cash1490859749778','广州番禺广场店','10.0','cash','番禺区','JJL00002','2017-03-30 15:42:29'),(17,'NO.cash1490859765349','广州番禺广场店','10.0','cash','番禺区','JJL00002','2017-03-30 15:42:45'),(17,'No.alipay1490860280048','广州番禺广场店','1.0','alipay','番禺区','JJL00002','2017-03-30 15:51:45'),(17,'NO.cash1490922474010','广州番禺广场店','15.0','cash','番禺区','JJL00002','2017-03-31 09:07:54'),(17,'No.alipay1490924294660','广州番禺广场店','0.02','alipay','番禺区','JJL00002','2017-03-31 09:38:50'),(16,'No.alipay1491008257694','番禺桥南店','0.02','alipay','番禺区','JJL00001','2017-04-01 08:58:08'),(16,'No.alipay1491008332868','番禺桥南店','0.01','alipay','番禺区','JJL00001','2017-04-01 08:59:08'),(16,'No.alipay1491008350842','番禺桥南店','0.01','alipay','番禺区','JJL00001','2017-04-01 08:59:21'),(16,'NO.cash1491099625439','番禺桥南店','10.0','cash','番禺区','JJL00001','2017-04-02 10:20:25'),(16,'No.alipay1491099855922','番禺桥南店','0.01','alipay','番禺区','JJL00001','2017-04-02 10:24:36'),(19,'No.alipay1491100479888','广州好又多店','0.02','alipay','广州区','JJL001','2017-04-02 10:35:50'),(16,'No.alipay1491101983520','番禺桥南店','0.01','alipay','番禺区','JJL00001','2017-04-02 11:01:33'),(19,'NO.cash1491105928875','广州好又多店','10.0','cash','广州区','JJL001','2017-04-02 12:05:28'),(19,'NO.cash1491106031557','广州好又多店','10.0','cash','广州区','JJL001','2017-04-02 12:07:11'),(19,'NO.cash1491106142319','广州好又多店','10.0','cash','广州区','JJL001','2017-04-02 12:09:02'),(19,'NO.cash1491106283341','广州好又多店','15.0','cash','广州区','JJL001','2017-04-02 12:11:23'),(19,'NO.cash1491107267651','广州好又多店','10.0','cash','广州区','JJL001','2017-04-02 12:27:47'),(19,'NO.cash1491107348041','广州好又多店','10.0','cash','广州区','JJL001','2017-04-02 12:29:08'),(19,'NO.cash1491304081153','广州好又多店','15.0','cash','广州区','JJL001','2017-04-04 19:08:01'),(19,'NO.cash1491441011148','广州好又多店','15.0','cash','广州区','JJL001','2017-04-06 09:10:11'),(19,'NO.cash1491463287886','广州好又多店','15.0','cash','广州区','JJL001','2017-04-06 15:21:27'),(19,'No.alipay1491525936048','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-07 08:45:46'),(19,'No.alipay1491526003066','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-07 08:47:08'),(19,'No.alipay1491709448686','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-09 11:44:59'),(19,'No.alipay1491709541068','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-09 11:45:51'),(19,'No.alipay1491709579932','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-09 11:46:35'),(19,'No.alipay1491709598506','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-09 11:46:53'),(19,'No.alipay1491709620725','广州好又多店','0.01','alipay','广州区','JJL001','2017-04-09 11:47:16');
/*!40000 ALTER TABLE `jjlbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlbillacceptor`
--

DROP TABLE IF EXISTS `jjlbillacceptor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlbillacceptor` (
  `userid` int(10) unsigned NOT NULL,
  `acceptMoney` varchar(50) NOT NULL,
  `acceptTime` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlbillacceptor`
--

LOCK TABLES `jjlbillacceptor` WRITE;
/*!40000 ALTER TABLE `jjlbillacceptor` DISABLE KEYS */;
/*!40000 ALTER TABLE `jjlbillacceptor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlchars`
--

DROP TABLE IF EXISTS `jjlchars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlchars` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlchars`
--

LOCK TABLES `jjlchars` WRITE;
/*!40000 ALTER TABLE `jjlchars` DISABLE KEYS */;
INSERT INTO `jjlchars` VALUES (1,'1','你知道吗?','普通人约有1,000,000根头发呢','2017-03-19 21:41:41'),(2,'1','你知道吗?','人头发的生长速度平均是每月1厘米','2017-03-19 21:42:33'),(3,'1','你知道吗?','不要怕梳头，因为常梳头会让你的头皮血液流通更好','2017-03-19 21:42:33'),(4,'1','你知道吗?','早晚各吃一口黑芝麻，滋养头发毛囊促进头发生长和乌黑健康','2017-03-19 21:42:33'),(5,'1','test','test tesd','2017-03-19 23:41:22');
/*!40000 ALTER TABLE `jjlchars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlcustomer`
--

DROP TABLE IF EXISTS `jjlcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlcustomer` (
  `userid` int(10) unsigned NOT NULL,
  `CustomerCategory` varchar(50) NOT NULL,
  `TicketQTY` varchar(50) DEFAULT NULL,
  `TicketPrice` varchar(50) DEFAULT NULL,
  `printNO` varchar(50) NOT NULL,
  `addTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlcustomer`
--

LOCK TABLES `jjlcustomer` WRITE;
/*!40000 ALTER TABLE `jjlcustomer` DISABLE KEYS */;
/*!40000 ALTER TABLE `jjlcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlexception`
--

DROP TABLE IF EXISTS `jjlexception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlexception` (
  `userid` int(10) unsigned NOT NULL,
  `shopname` varchar(500) DEFAULT NULL,
  `region` varchar(500) DEFAULT NULL,
  `DeviceNo` varchar(100) DEFAULT NULL,
  `ExceptionContent` varchar(500) DEFAULT NULL,
  `addTime` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlexception`
--

LOCK TABLES `jjlexception` WRITE;
/*!40000 ALTER TABLE `jjlexception` DISABLE KEYS */;
INSERT INTO `jjlexception` VALUES (16,'番禺桥南店','番禺区','JJL00001','钱箱被打开','2017-03-17 00:45:45'),(16,'番禺桥南店','番禺区','JJL00001','钱箱被打开','2017-03-17 00:50:18'),(16,'番禺桥南店','番禺区','JJL00001','钱箱被打开','2017-03-29 02:02:39'),(17,'广州番禺广场店','番禺区','JJL00001','钱箱被打开','2017-03-29 07:22:28'),(17,'广州番禺广场店','番禺区','JJL00001','钱箱被打开','2017-03-29 21:50:44'),(17,'广州番禺广场店','番禺区','JJL00001','钱箱被打开','2017-03-29 22:23:37'),(17,'广州番禺广场店','番禺区','JJL00002','钱箱被打开','2017-03-30 00:03:00'),(19,'广州好又多店','广州区','JJL001','钱箱被打开','2017-04-02 21:22:49'),(16,'番禺桥南店','番禺区','JJL00002','钱箱被打开','2017-04-07 01:42:09'),(16,'番禺桥南店','番禺区','JJL00002','钱箱被打开','2017-04-07 01:42:10'),(16,'番禺桥南店','番禺区','JJL00002','钱箱被打开','2017-04-07 01:47:44'),(16,'番禺桥南店','番禺区','JJL00002','钱箱被打开','2017-04-07 01:47:45');
/*!40000 ALTER TABLE `jjlexception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlimage`
--

DROP TABLE IF EXISTS `jjlimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlimage` (
  `userid` int(10) unsigned NOT NULL,
  `name` varchar(10000) DEFAULT NULL,
  `path` varchar(10000) DEFAULT NULL,
  `addTime` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlimage`
--

LOCK TABLES `jjlimage` WRITE;
/*!40000 ALTER TABLE `jjlimage` DISABLE KEYS */;
INSERT INTO `jjlimage` VALUES (16,'81u58PICTkw.jpg,72w58PICshJ.jpg','Image/chen/','2017-04-01 22:17:26'),(17,'',NULL,'');
/*!40000 ALTER TABLE `jjlimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlpayment`
--

DROP TABLE IF EXISTS `jjlpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlpayment` (
  `userid` int(10) unsigned NOT NULL,
  `payType` varchar(50) DEFAULT NULL,
  `payAmount` varchar(50) DEFAULT NULL,
  `addTime` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlpayment`
--

LOCK TABLES `jjlpayment` WRITE;
/*!40000 ALTER TABLE `jjlpayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `jjlpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlprinter`
--

DROP TABLE IF EXISTS `jjlprinter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlprinter` (
  `userid` int(10) unsigned NOT NULL,
  `printNO` varchar(50) DEFAULT NULL,
  `printBalance` varchar(50) DEFAULT NULL,
  `printTime` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlprinter`
--

LOCK TABLES `jjlprinter` WRITE;
/*!40000 ALTER TABLE `jjlprinter` DISABLE KEYS */;
/*!40000 ALTER TABLE `jjlprinter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlsettings`
--

DROP TABLE IF EXISTS `jjlsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlsettings` (
  `userid` int(10) unsigned NOT NULL,
  `ChildPrice` varchar(50) DEFAULT NULL,
  `AdultPrice` varchar(50) DEFAULT NULL,
  `dicounts` varchar(50) DEFAULT NULL,
  `TimePeriod` varchar(50) DEFAULT NULL,
  `MediaType` varchar(50) DEFAULT NULL,
  `ShowText` varchar(100) DEFAULT NULL,
  `addTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlsettings`
--

LOCK TABLES `jjlsettings` WRITE;
/*!40000 ALTER TABLE `jjlsettings` DISABLE KEYS */;
/*!40000 ALTER TABLE `jjlsettings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjluser`
--

DROP TABLE IF EXISTS `jjluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjluser` (
  `userid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `shopname` varchar(250) DEFAULT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `IDcard` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `DeviceNO` varchar(50) DEFAULT NULL,
  `addTime` varchar(50) NOT NULL,
  `loginTime` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjluser`
--

LOCK TABLES `jjluser` WRITE;
/*!40000 ALTER TABLE `jjluser` DISABLE KEYS */;
INSERT INTO `jjluser` VALUES (1,'all','123456','所有店','NULL','NULL','NULL','NULL','NULL','2017-02-26 13:14:21','2017-03-19 17:12:18'),(16,'chen','1234','番禺桥南店','12345678901','123456789012345678','番禺区','番禺区市桥','JJL00001','2017-02-26 13:14:21','2017-04-08 16:57:50'),(17,'chen002','12345678','广州番禺广场店','12345678901','123456789012345678','番禺区','番禺区市桥','JJL00002','2017-03-27 09:19:59','2017-03-30 20:24:44'),(18,'chen003','1234','广州花城广场店','12345678901','123456789012345678','天河区','天河区花城大道','JJL00003','2017-03-28 19:26:17','2017-03-30 16:17:20'),(19,'秋收','1234','广州好又多店','12345678901','123456789012345678','广州区','番禺区市桥','JJL001','2017-04-01 22:39:04','2017-04-02 11:22:12');
/*!40000 ALTER TABLE `jjluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jjlvideo`
--

DROP TABLE IF EXISTS `jjlvideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjlvideo` (
  `userid` int(10) unsigned NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `addTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jjlvideo`
--

LOCK TABLES `jjlvideo` WRITE;
/*!40000 ALTER TABLE `jjlvideo` DISABLE KEYS */;
INSERT INTO `jjlvideo` VALUES (16,'Lamborghini.mp4','Video/chen/','2017-03-12 14:46:34'),(17,'VTS_018.mp4','Video/chen002/','2017-04-01 22:09:05');
/*!40000 ALTER TABLE `jjlvideo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-11  1:06:14
