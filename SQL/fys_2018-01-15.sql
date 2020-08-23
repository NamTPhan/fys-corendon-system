# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: fys
# Generation Time: 2018-01-15 10:08:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table airports
# ------------------------------------------------------------

DROP TABLE IF EXISTS `airports`;

CREATE TABLE `airports` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `airports` WRITE;
/*!40000 ALTER TABLE `airports` DISABLE KEYS */;

INSERT INTO `airports` (`id`, `name`, `location`, `state`, `country`, `created_at`, `updated_at`)
VALUES
	(1,'JFK','Kohlerview','-','United States','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(2,'Schiphol','Harbermouth','-','Netherlands','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(3,'Heathrow','London','-','England','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(4,'Aperiam sint at.','South Willowchester','Colorado','Gabon','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(5,'Voluptas quo omnis.','Creminstad','Arkansas','Slovenia','2018-01-15 10:01:06','2018-01-15 10:01:06');

/*!40000 ALTER TABLE `airports` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table clients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `place` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `postal_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;

INSERT INTO `clients` (`id`, `first_name`, `last_name`, `email`, `address`, `place`, `postal_code`, `phone_number`, `state`, `country`, `created_at`, `updated_at`)
VALUES
	(1,'Kaelyn','Stark','hintz.kirsten@smith.com','89261 Balistreri Pine Apt. 095\nEast Christelle, AR 21549-7517','Kathryntown','96897-3347','963.289.8911 x482','Wisconsin','Moldova','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(2,'Zackary','Bauch','kunze.dorthy@gmail.com','808 Konopelski Pike\nSouth Brisa, VA 64475-5062','Lake Kamronton','42140-4249','(979) 750-5252 x9002','Georgia','Syrian Arab Republic','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(3,'Joy','Rodriguez','marques56@yahoo.com','1665 Erdman Crescent Suite 875\nSouth Eliborough, NH 93809','West Bennie','99339','942.921.7511','New Mexico','Costa Rica','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(4,'Enid','McKenzie','bwiegand@gislason.com','17014 Doyle Bridge\nPort Flossieside, IL 28176-4986','West Lemuel','89313-3497','+12603793904','Wyoming','Lithuania','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(5,'Tracey','Quigley','ford.mayert@gmail.com','374 Donnelly Square Apt. 706\nEast Nick, WI 12086-7882','Hollymouth','62934-8631','+1-237-938-4717','Idaho','Afghanistan','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(6,'Hobart','Durgan','oconnell.ellsworth@hotmail.com','9451 Tyra Roads\nMakaylaland, WI 16654','Myamouth','43144-6949','1-238-280-5104 x271','Nebraska','Argentina','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(7,'Vesta','Jaskolski','margret44@yahoo.com','381 Glover Hills Suite 410\nLake Josianne, NY 59194','West Arleneside','15205-4049','(202) 443-9250 x462','Louisiana','Guam','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(8,'Derick','Kerluke','audreanne81@hotmail.com','2880 Mayra Lakes Apt. 526\nSchinnerchester, WY 50816-3259','Farrellchester','19015-3748','+1-647-736-2200','Oregon','Bahamas','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(9,'Elinore','Harvey','oohara@gulgowski.biz','492 Hudson Light Suite 730\nFrancisstad, GA 23671','Blakeside','08474-1517','(206) 591-7093 x521','Rhode Island','Mali','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(10,'Gayle','Beahan','mharvey@heller.com','738 Mabelle Course\nStrackeborough, IL 56620','North Breanaberg','09271','+18804085517','Montana','Brunei Darussalam','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(11,'Sibyl','Dooley','asa06@yahoo.com','492 Runte Ville Suite 977\nGoodwinland, NV 07876-9576','Hintztown','51885-7454','1-890-367-2461 x9084','Hawaii','Bhutan','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(12,'Genesis','Dach','monica65@simonis.com','484 Lesch Parks Apt. 134\nLake Garryland, WY 14082-9487','New Ralph','92960-1577','779.514.7140 x1664','Massachusetts','Uzbekistan','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(13,'Otilia','Daniel','karlie.hoeger@hotmail.com','245 Violet Inlet\nHeaneyfurt, WA 40746','Erynfurt','49465-4637','263.476.7010 x912','Arizona','Martinique','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(14,'Esta','Fadel','senger.fidel@yahoo.com','46330 Gusikowski Ridges\nEast Sandra, MT 69263-5802','West Barrettville','27627','+1-771-960-4516','Utah','Christmas Island','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(15,'Harvey','Mills','marvin.corwin@hotmail.com','65121 Emery Junction Apt. 376\nEast Carolynburgh, MA 10631','Kristafurt','27621-1159','+1-438-630-2687','Pennsylvania','Lithuania','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(16,'Fausto','Bergnaum','rkoch@nader.com','8666 Lyric Meadow\nNorth Jaylin, MS 64054-5711','Stephonland','18838-4113','+1.343.969.2036','Oregon','Bahrain','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(17,'Eleazar','Beer','xboyer@hotmail.com','82870 Kaitlin Meadows Apt. 894\nLake Einar, NM 46119-5659','Loweborough','03183','1-974-575-2391 x7671','Louisiana','Thailand','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(18,'Esmeralda','Connelly','ymurphy@satterfield.info','943 Carli Camp Suite 524\nNathanborough, MD 11714','Nitzscheview','59420','775.722.3717 x97453','Delaware','New Caledonia','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(19,'Prince','Schulist','monahan.daisha@bernhard.com','43066 Marques Inlet Suite 845\nKellenburgh, NC 90166','Patrickport','71725','1-710-288-9972','Arkansas','Vanuatu','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(20,'Alexie','Cassin','king.otis@schuppe.com','1541 Juwan Court\nNorth Lina, UT 11501-0214','Bernietown','62619','(623) 342-4790','Louisiana','Uzbekistan','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(21,'Octavia','Sipes','fritsch.otto@gmail.com','85996 Gisselle Crossroad\nWest Bobbietown, CT 15861-9805','Bettyville','01534-7272','349-755-0572 x24527','Minnesota','Greenland','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(22,'Elbert','Braun','jkub@ernser.com','993 Chanel Rue\nShawnatown, KY 49555','Lake Noraside','88668','(205) 632-4223','Oregon','Svalbard & Jan Mayen Islands','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(23,'Jason','Veum','arturo90@monahan.com','774 Carol Stravenue\nHilperttown, MI 20803-5923','Hodkiewiczmouth','43168','1-981-226-6629','Iowa','Hong Kong','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(24,'Howell','Crooks','erika.weber@wisozk.org','602 Annabelle Lake Apt. 982\nNew Dariana, WV 81827-1942','New Otisfort','18303-8632','551.591.4301','Rhode Island','Hungary','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(25,'Priscilla','Kunze','barney58@koepp.com','968 Novella Circles\nLowechester, NC 47700-3454','North Thoraborough','11685','956-651-0729 x469','Vermont','Mongolia','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(26,'Shayna','Fisher','mhackett@hotmail.com','1013 Hintz Village Suite 176\nPort Josh, NV 89766','Elmerfurt','44270-6969','895-208-1348','Minnesota','Brunei Darussalam','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(27,'Garrett','Kunze','jconroy@wehner.com','1777 Eulalia Drives\nBednarland, OR 79849','New Annamaemouth','74187','628.618.3684 x3227','Nevada','Guadeloupe','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(28,'Ettie','Schultz','lucius22@casper.com','169 Kenya Forges\nAuerchester, WI 19683','Veumport','79006-2084','1-298-422-0954','Missouri','Guadeloupe','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(29,'Stan','Bechtelar','theresia27@reichert.org','52037 Sabrina Corner\nMuellermouth, LA 76548-0557','Maevefurt','36156-2691','+1-804-343-9431','Wisconsin','Myanmar','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(30,'Adele','Considine','isaias.littel@rosenbaum.com','2373 Brice Isle Suite 497\nEast Bernieceside, CO 07478','Kassulkechester','35947','479.456.0204 x60367','Kentucky','Guadeloupe','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(31,'Darby','Kris','berneice05@gmail.com','328 Danial Locks\nLake Roy, CO 66736','Buckridgemouth','01497-7285','(490) 263-3315 x818','Utah','Congo','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(32,'Edison','Marks','reid02@mante.info','986 Crystel Bridge Suite 284\nPort Moseport, SD 89231-8431','Dexterfort','34725-5191','1-440-628-4474 x35545','Georgia','Uganda','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(33,'Sandrine','Breitenberg','wpfannerstill@vandervort.net','98919 Ryder Park\nEstrellaside, VA 24014','Altenwerthstad','13929-3254','345-589-1874','Idaho','Myanmar','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(34,'Rhett','Abernathy','lenna30@hotmail.com','58967 Hayden Valley Suite 547\nRonport, WA 26240','New Juanita','55316-3629','+1 (960) 451-9914','Virginia','Monaco','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(35,'Montana','Glover','elza36@hotmail.com','439 Macejkovic Trace\nO\'Connellfurt, MS 59508','Kirlinberg','31133-3733','1-483-728-1935','Georgia','Congo','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(36,'Simone','Douglas','collier.orville@hotmail.com','15032 Verlie Junctions Suite 557\nPenelopeside, AR 08064-0060','West Nicobury','24972-7256','+14308417374','Montana','Taiwan','2018-01-15 10:01:06','2018-01-15 10:01:06');

/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table issues
# ------------------------------------------------------------

DROP TABLE IF EXISTS `issues`;

CREATE TABLE `issues` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `luggage_id` int(11) DEFAULT NULL,
  `airport_id` int(11) NOT NULL DEFAULT '0',
  `status_id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `issues` WRITE;
/*!40000 ALTER TABLE `issues` DISABLE KEYS */;

INSERT INTO `issues` (`id`, `luggage_id`, `airport_id`, `status_id`, `client_id`, `created_at`, `updated_at`)
VALUES
	(1,1,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(2,2,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(3,3,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(4,4,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(5,5,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(6,6,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(7,7,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(8,8,1,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(9,9,1,2,1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(10,10,1,2,2,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(11,11,1,2,3,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(12,12,1,2,4,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(13,13,1,2,5,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(14,14,1,2,6,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(15,15,1,2,7,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(16,16,1,2,8,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(17,17,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(18,18,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(19,19,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(20,20,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(21,21,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(22,22,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(23,23,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(24,24,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(25,25,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(26,26,2,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(27,27,2,2,9,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(28,28,2,2,10,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(29,29,2,2,11,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(30,30,2,2,12,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(31,31,2,2,13,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(32,32,2,2,14,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(33,33,2,2,15,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(34,34,2,2,16,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(35,35,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(36,36,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(37,37,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(38,38,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(39,39,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(40,40,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(41,41,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(42,42,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(43,43,0,1,17,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(44,44,0,1,18,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(45,45,0,1,19,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(46,46,0,1,20,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(47,47,0,1,21,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(48,48,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(49,49,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(50,50,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(51,51,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(52,52,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(53,53,0,1,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(54,54,0,1,22,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(55,55,0,1,23,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(56,56,0,1,24,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(57,57,0,1,25,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(58,58,0,1,26,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(59,59,0,1,27,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(60,60,0,1,28,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(61,61,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(62,62,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(63,63,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(64,64,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(65,65,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(66,66,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(67,67,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(68,68,5,2,NULL,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(69,69,5,2,29,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(70,70,5,2,30,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(71,71,5,2,31,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(72,72,5,2,32,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(73,73,5,2,33,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(74,74,5,2,34,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(75,75,5,2,35,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(76,76,5,2,36,'2018-01-15 10:01:06','2018-01-15 10:01:06');

/*!40000 ALTER TABLE `issues` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table luggage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `luggage`;

CREATE TABLE `luggage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `brand` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `color` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'color',
  `label_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `flight_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `destination` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `extra_information` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `compensation` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `luggage` WRITE;
/*!40000 ALTER TABLE `luggage` DISABLE KEYS */;

INSERT INTO `luggage` (`id`, `type`, `brand`, `color`, `label_number`, `flight_number`, `destination`, `extra_information`, `compensation`, `created_at`, `updated_at`)
VALUES
	(1,'suitcase','eastpak','SeaShell','08013','NY16235','Liamborough','Soluta omnis quod doloribus exercitationem exercitationem.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(2,'handbag','gucci','Salmon','99227','IA91476-9016','South Simeonside','Ut quos quo minima pariatur porro perspiciatis sequi.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(3,'trolley','addidas','MintCream','21740-2987','AK59785','North Carmelshire','Alias illum sint pariatur aut aspernatur non.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(4,'rugsack','louis vuitton','Thistle','12743-3483','CA65342','New Jasonburgh','Esse tenetur ut ab earum hic quae et eum.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(5,'rugsack','gucci','MediumVioletRed','61065-7650','IN35704','Geovannychester','Totam ullam nobis adipisci ullam numquam.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(6,'handbag','addidas','MidnightBlue','40108','AK47188','Rueckershire','Tenetur nam eum magnam facilis temporibus voluptatem.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(7,'trolley','addidas','Fuchsia','29652','VT68230','Runolfssonmouth','Accusamus voluptas eos omnis aperiam quia.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(8,'handbag','eastpak','Purple','56897','DC76217-5987','Kuphalmouth','Odit mollitia ab deleniti et sed.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(9,'handbag','eastpak','IndianRed','38411','MS38449-2338','Okunevashire','Consequatur animi eos ipsum ut voluptas consequatur omnis.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(10,'rugsack','none','SeaGreen','32595-4796','OK84021-8493','Lake Keeganborough','Et ea repudiandae impedit voluptates ullam ducimus.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(11,'rugsack','gucci','DarkGray','37853-5667','AL15961','West Furmanview','Molestias illo sequi natus.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(12,'trolley','addidas','Peru','59867-8340','ID06594-2733','Flatleyport','Vel iste ipsa perferendis quis cupiditate itaque rerum quia.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(13,'suitcase','gucci','NavajoWhite','37347','CA38558','Tressiemouth','Quo saepe porro dolor.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(14,'handbag','addidas','DodgerBlue','06271','NC73415-8032','Nasirfurt','Corrupti quas voluptatem facere hic quo quam.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(15,'trolley','eastpak','Gray','41060-0751','AR65807-0540','Calebland','Temporibus numquam nobis ullam temporibus natus.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(16,'trolley','eastpak','DarkBlue','28230','CO38831','Olsonport','Nihil et tempore est ab quisquam repellat.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(17,'suitcase','gucci','Azure','35939-1758','MN83958','Stokesview','Voluptates quo beatae enim voluptatibus excepturi voluptatum qui.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(18,'suitcase','eastpak','MediumVioletRed','95973','OK56471-2411','New Serenitymouth','Omnis autem repellendus omnis praesentium delectus.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(19,'trolley','none','DimGrey','78128','OH40217','Shieldsstad','Aut quae autem placeat iste ab tenetur dolores.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(20,'rugsack','gucci','Gold','31943-4006','AL69188','Alexandreland','Non sit ea et id.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(21,'handbag','none','DeepSkyBlue','22502-1161','WI91757-4988','North Lucas','Facilis vitae amet inventore odit sed vitae.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(22,'rugsack','none','Snow','43864-3897','WY23672','Faystad','Qui numquam tempore quibusdam.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(23,'handbag','gucci','Brown','17348','NE30831-7163','Lake Destinee','Soluta dolor facere asperiores eveniet eos.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(24,'rugsack','nike','LightGray','10665','CO47945','Eloisestad','Omnis repellendus accusantium necessitatibus rerum.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(25,'handbag','addidas','Ivory','34976-7560','IN38071','New Alexzanderton','Quos ut minima nobis at iure iste a.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(26,'suitcase','louis vuitton','LightYellow','59260-0291','MD27903','Lake Annabel','Ea doloremque voluptas reiciendis eaque rerum.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(27,'suitcase','gucci','SkyBlue','68193-8031','CT69650','Jacobsonville','Illo tempora in asperiores aut.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(28,'handbag','nike','Green','50010','NJ93547','Aliyahbury','Ullam dolores facilis voluptate autem at doloribus officiis.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(29,'handbag','nike','Red','73583-4125','MD23890','Lake Brandy','Quam sapiente assumenda eos sint eaque sunt.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(30,'trolley','eastpak','PapayaWhip','57580','NY99147-5819','Breitenbergmouth','Quia eos sit quia perspiciatis.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(31,'suitcase','addidas','Brown','99059','GA84670-3666','West Tillman','Asperiores architecto dicta sit.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(32,'trolley','eastpak','Chocolate','16806','VA80599','North Williston','Tempore fugiat consequuntur ipsam minus non.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(33,'trolley','nike','PowderBlue','53774','GA43076','Rippinberg','Minus sit voluptatem est cupiditate quas.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(34,'handbag','nike','DarkBlue','62310-1743','WV78776-1187','Carrieberg','Est ab et iusto nulla sit adipisci.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(35,'handbag','none','MediumOrchid','85248-4655','ME89836-8565','Rafaelaside','Ea architecto consectetur corporis consequatur error cupiditate.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(36,'handbag','nike','Wheat','24842-7263','CT74071','Yesseniamouth','Sunt dolores debitis distinctio consectetur quos.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(37,'trolley','none','LightPink','33939-8562','KY62444','Shannafurt','Dolore quia consequatur minus quod molestiae voluptates tempora rerum.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(38,'handbag','nike','LightSkyBlue','21981','RI11944-6321','Lake Mertie','Accusantium quod officia laboriosam cumque.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(39,'rugsack','nike','MistyRose','24593-3622','WY33070','Bartellmouth','Reprehenderit doloremque saepe nisi asperiores.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(40,'trolley','louis vuitton','DarkTurquoise','13448','LA78569','Brownside','Rem aspernatur dolor reiciendis provident id voluptatem ratione.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(41,'suitcase','addidas','SeaGreen','77454-5190','GA98576-8931','New Jaleel','Est dolorem delectus nam id.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(42,'suitcase','gucci','Salmon','32440-7420','WA44320-9402','Carahaven','Assumenda laborum debitis tempora provident voluptatem.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(43,'handbag','eastpak','Navy','86996','MT15436-3194','Darronborough','Eum autem quidem voluptatem tenetur vero libero.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(44,'rugsack','louis vuitton','LavenderBlush','76334','MD71269-4839','East Roselyntown','Nesciunt saepe ab itaque.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(45,'suitcase','gucci','Crimson','88785','MD06564','South Tinachester','A odit eos est.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(46,'rugsack','gucci','Chartreuse','27915-1082','CA98247-5603','South Ezequielland','Qui dignissimos aut culpa aliquam.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(47,'rugsack','louis vuitton','DarkSlateGray','62394','IA63196','Reichertville','Voluptatibus quos in repellat sint voluptatem nihil sed.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(48,'rugsack','gucci','PaleTurquoise','68841','DC15476-6556','Vonside','Reiciendis dignissimos voluptas ut ullam necessitatibus.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(49,'rugsack','addidas','Wheat','21063-0191','MN79748','Rickyton','Et harum dolorum voluptate veritatis et est.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(50,'rugsack','none','OrangeRed','89298-1710','CO06826','Christiansenport','Eligendi voluptas ut aliquam delectus.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(51,'handbag','louis vuitton','SpringGreen','25591-0659','GA31105','New Virginieton','Voluptatibus et architecto dolor doloremque.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(52,'trolley','gucci','Thistle','37553-1158','NJ37705-5947','Port Angelitatown','Aliquid quis iure adipisci ratione velit.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(53,'trolley','gucci','DarkGray','84177-2149','HI64321','Lake Kali','Saepe sit sint omnis dolorem eos voluptatem qui.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(54,'handbag','addidas','WhiteSmoke','10531-5070','NY95817','Bertramborough','Est autem blanditiis facere.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(55,'trolley','none','PapayaWhip','34856-7117','KY19997-1523','Stoltenbergmouth','Eos sit dolor ducimus officiis quia rerum.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(56,'rugsack','addidas','Tan','21600','WV50589','East Alisonfurt','Provident voluptatum esse quasi.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(57,'handbag','gucci','MediumVioletRed','78765-7181','OK29785-7519','New Edna','Exercitationem quis quia libero dolorem dolores.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(58,'rugsack','addidas','LightGoldenRodYellow','86373-1809','AK79937','South Aaronmouth','Quidem ut perspiciatis exercitationem mollitia.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(59,'suitcase','nike','Aquamarine','16060','MO53359-1875','Port Chynastad','Quia quo et maxime consequatur rem rem ut.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(60,'handbag','addidas','PaleGoldenRod','36100','LA08114','Christiansenchester','Deleniti eligendi tenetur quo non dolores debitis quos.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(61,'rugsack','nike','LightSkyBlue','00021','IA50757-2579','Port Elody','Assumenda et fugit necessitatibus aut.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(62,'suitcase','nike','SpringGreen','88185-1868','WA11598-8689','Purdytown','Eius ab voluptate ad et.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(63,'rugsack','gucci','CadetBlue','46288-7556','LA90686-9095','New Irma','Adipisci necessitatibus expedita eum rem et dolorum provident.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(64,'trolley','nike','DarkSeaGreen','27821','NC45210','New Herminiotown','Tenetur dolorum aut dolorum facilis.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(65,'suitcase','addidas','Salmon','35144-0506','NJ60046-8262','Hermannshire','Optio aut dolores labore molestias occaecati.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(66,'suitcase','louis vuitton','MediumBlue','60550','KS14062-3085','New Drakemouth','Porro occaecati cupiditate et voluptatem.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(67,'rugsack','gucci','OliveDrab','55085','IL52929','Lake Elfrieda','Sapiente minima aspernatur aut eligendi doloribus vitae ut.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(68,'trolley','eastpak','PeachPuff','49215','NJ70133','Kemmerbury','Numquam esse modi et totam eos enim.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(69,'suitcase','none','ForestGreen','94516-9828','WA96648-3607','West Jamalfurt','Et qui voluptatem aut error qui aspernatur.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(70,'suitcase','none','Violet','44340-8200','IA00168','Tremainefort','Non minus optio neque cumque deleniti voluptate sed.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(71,'rugsack','eastpak','Silver','64764-9634','CT23336-7392','East Reinholdburgh','Hic cupiditate temporibus voluptatem nihil et ullam.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(72,'suitcase','louis vuitton','LightGray','54632-8225','KS12480-3879','North Joanfurt','Delectus animi animi ipsum voluptas quia nisi vitae.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(73,'suitcase','gucci','MidnightBlue','42088-8115','UT89650','New Gillianmouth','Soluta ab architecto qui.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(74,'suitcase','gucci','SeaGreen','02540-4853','ME28346','Lake Retta','Et sint et voluptate nihil.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(75,'trolley','louis vuitton','BurlyWood','35181-7690','OR03326-6494','Lake Justine','Saepe deserunt corrupti ex quo.',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(76,'suitcase','gucci','MediumBlue','78874-9405','NY00005','Krystalland','Quaerat sit dolorem id delectus debitis rerum repudiandae.',0,'2018-01-15 10:01:06','2018-01-15 10:01:06');

/*!40000 ALTER TABLE `luggage` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table statuses
# ------------------------------------------------------------

DROP TABLE IF EXISTS `statuses`;

CREATE TABLE `statuses` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;

INSERT INTO `statuses` (`id`, `name`, `created_at`, `updated_at`)
VALUES
	(1,'unsolved','2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(2,'solved','2018-01-15 10:01:06','2018-01-15 10:01:06');

/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `default_language` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `name`, `email`, `password`, `default_language`, `is_admin`, `created_at`, `updated_at`)
VALUES
	(1,'Prof. Michelle Barrows','admin@test.com','secret','in',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(2,'Evan Berge V','user@test','secret','rerum',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(3,'Monty Anderson V','ukuphal@example.net','secret','libero',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(4,'Kiel Cummings','kiara85@example.net','secret','in',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(5,'Dorothea Little','nedra.lang@example.com','secret','sint',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(6,'Mr. Michale Roob PhD','pollich.henry@example.org','secret','reprehenderit',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(7,'Miss Vilma Koch V','cummings.chanel@example.org','secret','quisquam',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(8,'Emmie Upton','hilpert.nikko@example.net','secret','iure',0,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(9,'Dr. Alden Sipes DDS','mylene64@example.net','secret','qui',1,'2018-01-15 10:01:06','2018-01-15 10:01:06'),
	(10,'Brice Labadie','therman@example.org','secret','excepturi',0,'2018-01-15 10:01:06','2018-01-15 10:01:06');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
