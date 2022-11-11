/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : eprodavnica

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 31/08/2021 13:23:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kategorija_proizvoda
-- ----------------------------
DROP TABLE IF EXISTS `kategorija_proizvoda`;
CREATE TABLE `kategorija_proizvoda` (
  `kategorija_proizvoda_id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`kategorija_proizvoda_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kategorija_proizvoda
-- ----------------------------
BEGIN;
INSERT INTO `kategorija_proizvoda` VALUES (1, 'Laptop');
INSERT INTO `kategorija_proizvoda` VALUES (2, 'Desktop');
INSERT INTO `kategorija_proizvoda` VALUES (3, 'Mobile');
COMMIT;

-- ----------------------------
-- Table structure for korisnik
-- ----------------------------
DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE `korisnik` (
  `korisnik_id` int unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefon` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `adresa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`korisnik_id`),
  UNIQUE KEY `uq_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of korisnik
-- ----------------------------
BEGIN;
INSERT INTO `korisnik` VALUES (1, 'Ivan', 'Cvetkovic', 'cvetkovici@gmail.com', '063123456771', 'VV70bb', 'vani', 'vani12345');
INSERT INTO `korisnik` VALUES (2, 'Jovana', 'Vicic', 'jvicic123@gmail.com', '0635555555', 'Varvarin Selo broj 8012', 'jvicic', 'ljubav123');
COMMIT;

-- ----------------------------
-- Table structure for omiljene_kategorije_proizvoda
-- ----------------------------
DROP TABLE IF EXISTS `omiljene_kategorije_proizvoda`;
CREATE TABLE `omiljene_kategorije_proizvoda` (
  `omiljene_kategorije_proizvoda_id` int unsigned NOT NULL AUTO_INCREMENT,
  `kategorija_proizvoda_id` int unsigned NOT NULL,
  `korisnik_id` int unsigned NOT NULL,
  PRIMARY KEY (`omiljene_kategorije_proizvoda_id`),
  KEY `omiljeneKategorije_korisnik_id_fk` (`korisnik_id`),
  KEY `omiljeneKategorije_kategorija_id_fk` (`kategorija_proizvoda_id`),
  CONSTRAINT `omiljeneKategorije_kategorija_id_fk` FOREIGN KEY (`kategorija_proizvoda_id`) REFERENCES `kategorija_proizvoda` (`kategorija_proizvoda_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `omiljeneKategorije_korisnik_id_fk` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of omiljene_kategorije_proizvoda
-- ----------------------------
BEGIN;
INSERT INTO `omiljene_kategorije_proizvoda` VALUES (1, 1, 1);
INSERT INTO `omiljene_kategorije_proizvoda` VALUES (2, 2, 1);
INSERT INTO `omiljene_kategorije_proizvoda` VALUES (4, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for poruceni_proizvodi
-- ----------------------------
DROP TABLE IF EXISTS `poruceni_proizvodi`;
CREATE TABLE `poruceni_proizvodi` (
  `poruceni_proizvodi_id` int unsigned NOT NULL AUTO_INCREMENT,
  `kolicina` float(10,5) unsigned NOT NULL,
  `cena_porudzbine` float(10,5) unsigned NOT NULL,
  `proizvod_id` int unsigned NOT NULL,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `korisnik_id` int unsigned NOT NULL,
  PRIMARY KEY (`poruceni_proizvodi_id`) USING BTREE,
  KEY `poruceni_proizvod_id` (`proizvod_id`),
  KEY `poruceni_proizvod_korisnik_id_fk` (`korisnik_id`),
  CONSTRAINT `poruceni_proizvod_id` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`proizvod_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `poruceni_proizvod_korisnik_id_fk` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of poruceni_proizvodi
-- ----------------------------
BEGIN;
INSERT INTO `poruceni_proizvodi` VALUES (5, 1.00000, 1000.00000, 3, 'in transit', 1);
INSERT INTO `poruceni_proizvodi` VALUES (6, 2.00000, 1900.00000, 4, 'finished', 1);
INSERT INTO `poruceni_proizvodi` VALUES (12, 1.00000, 150.00000, 6, 'pending', 2);
INSERT INTO `poruceni_proizvodi` VALUES (13, 1.00000, 150.00000, 6, 'pending', 2);
COMMIT;

-- ----------------------------
-- Table structure for proizvod
-- ----------------------------
DROP TABLE IF EXISTS `proizvod`;
CREATE TABLE `proizvod` (
  `proizvod_id` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opis` text COLLATE utf8_unicode_ci NOT NULL,
  `cena` float(7,2) NOT NULL,
  `kategorija_proizvoda_id` int unsigned NOT NULL,
  `proizvodjac` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cover_image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zemlja_porekla` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`proizvod_id`),
  KEY `proizvod_kategorija_id_fk` (`kategorija_proizvoda_id`),
  KEY `proizvod_proizvodjac_id_fk` (`proizvodjac`),
  CONSTRAINT `proizvod_kategorija_id_fk` FOREIGN KEY (`kategorija_proizvoda_id`) REFERENCES `kategorija_proizvoda` (`kategorija_proizvoda_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of proizvod
-- ----------------------------
BEGIN;
INSERT INTO `proizvod` VALUES (3, 'Laptop Sony', 'Fast,light and elegant laptop computer from Sony', 1000.00, 1, 'Sony', 'http://vaniijo.tk/images/laptopSony.jpeg', 'Juzna Koreja');
INSERT INTO `proizvod` VALUES (4, 'Lenovo Laptop i5', 'Latest laptop from Lenovo', 950.00, 1, 'Lenovo', 'http://vaniijo.tk/images/lenovoLaptop.jpeg', 'Kina');
INSERT INTO `proizvod` VALUES (5, 'Samsung s8', 'Powerful samsung mobile', 500.00, 3, 'Samsung', 'http://vaniijo.tk/images/samsungS8.jpeg', 'JuznaKoreja');
INSERT INTO `proizvod` VALUES (6, 'Samsung J4', 'Cheap phone with good performance', 150.00, 3, 'Samsung', 'http://vaniijo.tk/images/samsungJ4.jpeg', 'Juzna Koreja');
INSERT INTO `proizvod` VALUES (7, 'Dell Desktop Core 2', 'Older cheap desktop with core 2 duo,good for work', 350.00, 2, 'Dell', 'http://vaniijo.tk/images/desktopDell1.jpeg', 'America');
INSERT INTO `proizvod` VALUES (8, 'Iphone 12', 'Latest phone from Apple', 750.00, 3, 'Apple', 'http://vaniijo.tk/images/iphone12.png', 'America');
INSERT INTO `proizvod` VALUES (9, 'Huawei P40 pro', 'One of the best cameras on smartphone market', 900.00, 3, 'Huawei', 'http://vaniijo.tk/images/huaweiP40pro.jpg', 'China');
INSERT INTO `proizvod` VALUES (10, 'Apple MacBook pro', 'Powerful lapotp form apple with eficient processor and long battery life', 1200.00, 1, 'Apple', 'http://vaniijo.tk/images/applemacbookpro.jpeg', 'America');
INSERT INTO `proizvod` VALUES (11, 'Gaming Computer ', 'Powerful gaming desktop computer with RGB lighting', 1300.00, 2, 'Origin', 'http://vaniijo.tk/images/desktopGaming1.jpeg', 'America');
INSERT INTO `proizvod` VALUES (12, 'Apple Imac Desktop', 'Apple\'s desktop computer designed for professionals', 2000.00, 2, 'Apple', 'http://vaniijo.tk/images/appleimacdesktop.jpeg', 'America');
COMMIT;

-- ----------------------------
-- Table structure for recenzija_proizvoda
-- ----------------------------
DROP TABLE IF EXISTS `recenzija_proizvoda`;
CREATE TABLE `recenzija_proizvoda` (
  `recenzija_proizvoda_id` int unsigned NOT NULL AUTO_INCREMENT,
  `ocena` float(3,2) unsigned NOT NULL,
  `komentar` text COLLATE utf8_unicode_ci,
  `korisnik_id` int unsigned NOT NULL,
  `proizvod_id` int unsigned NOT NULL,
  `ime` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`recenzija_proizvoda_id`),
  KEY `recenzija_korisnik_id` (`korisnik_id`),
  KEY `recenzija_proizvod_id_fk` (`proizvod_id`),
  CONSTRAINT `recenzija_korisnik_id` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `recenzija_proizvod_id_fk` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`proizvod_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of recenzija_proizvoda
-- ----------------------------
BEGIN;
INSERT INTO `recenzija_proizvoda` VALUES (1, 4.00, 'Nije Losa roba', 1, 3, 'Ivan');
INSERT INTO `recenzija_proizvoda` VALUES (4, 5.00, 'Odlican proizvod', 2, 6, 'Jovana');
INSERT INTO `recenzija_proizvoda` VALUES (6, 5.00, 'Odlican proizvod', 1, 5, 'Ivan');
INSERT INTO `recenzija_proizvoda` VALUES (8, 5.00, 'Super', 1, 6, 'Ivan');
INSERT INTO `recenzija_proizvoda` VALUES (9, 4.00, 'Jaka masina', 2, 4, 'Jovana');
INSERT INTO `recenzija_proizvoda` VALUES (10, 4.00, 'Dobar Proizvod', 2, 3, 'Jovana');
INSERT INTO `recenzija_proizvoda` VALUES (11, 4.00, 'Zadovoljna proizvodom', 2, 5, 'Jovana');
INSERT INTO `recenzija_proizvoda` VALUES (13, 5.00, 'Odlican laptop za gaming sa jakom grafikom', 1, 4, 'Ivan');
COMMIT;

-- ----------------------------
-- Table structure for slike_proizvoda
-- ----------------------------
DROP TABLE IF EXISTS `slike_proizvoda`;
CREATE TABLE `slike_proizvoda` (
  `slike_proizvoda_id` int unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proizvod_id` int unsigned NOT NULL,
  PRIMARY KEY (`slike_proizvoda_id`),
  KEY `slike_proizvoda_proizvod_id_fk` (`proizvod_id`),
  CONSTRAINT `slike_proizvoda_proizvod_id_fk` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`proizvod_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of slike_proizvoda
-- ----------------------------
BEGIN;
INSERT INTO `slike_proizvoda` VALUES (1, 'slika1', 3);
INSERT INTO `slike_proizvoda` VALUES (2, 'slika2', 3);
INSERT INTO `slike_proizvoda` VALUES (3, 'slika3', 3);
INSERT INTO `slike_proizvoda` VALUES (4, 'slika1', 4);
INSERT INTO `slike_proizvoda` VALUES (5, 'slika2', 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
