-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for megalodondb
CREATE DATABASE IF NOT EXISTS `megalodondb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `megalodondb`;

-- Dumping structure for table megalodondb.active_game
CREATE TABLE IF NOT EXISTS `active_game` (
  `Id_users` int(11) NOT NULL,
  `Id_game` int(11) NOT NULL,
  `Status` int(11) DEFAULT NULL,
  KEY `FK_Active_game_Id_users_Users_Id_users` (`Id_users`),
  KEY `FK_Active_game_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Active_game_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`),
  CONSTRAINT `FK_Active_game_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.active_game: ~0 rows (approximately)
/*!40000 ALTER TABLE `active_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `active_game` ENABLE KEYS */;

-- Dumping structure for table megalodondb.bill
CREATE TABLE IF NOT EXISTS `bill` (
  `Id_bill` int(11) NOT NULL AUTO_INCREMENT,
  `Id_users` int(11) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Total_price` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`Id_bill`),
  KEY `FK_Bill_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Bill_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.bill: ~0 rows (approximately)
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;

-- Dumping structure for table megalodondb.bill_detail
CREATE TABLE IF NOT EXISTS `bill_detail` (
  `Id_bill_detail` int(11) NOT NULL AUTO_INCREMENT,
  `Id_bill` int(11) DEFAULT NULL,
  `Id_game` int(11) DEFAULT NULL,
  `Price` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`Id_bill_detail`),
  KEY `FK_Bill_detail_Id_bill_Bill_Id_bill` (`Id_bill`),
  KEY `FK_Bill_detail_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Bill_detail_Id_bill_Bill_Id_bill` FOREIGN KEY (`Id_bill`) REFERENCES `bill` (`Id_bill`) ON DELETE CASCADE,
  CONSTRAINT `FK_Bill_detail_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.bill_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;

-- Dumping structure for table megalodondb.blog
CREATE TABLE IF NOT EXISTS `blog` (
  `Id_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Title_blog` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Author_blog` int(11) NOT NULL,
  `Content_blog` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date_blog` date DEFAULT NULL,
  `Image_blog` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Views` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_blog`),
  KEY `FK_Blog_Author_blog_Users_Id_users` (`Author_blog`),
  CONSTRAINT `FK_Blog_Author_blog_Users_Id_users` FOREIGN KEY (`Author_blog`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.blog: ~3 rows (approximately)
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` (`Id_blog`, `Title_blog`, `Author_blog`, `Content_blog`, `Date_blog`, `Image_blog`, `Views`) VALUES
	(9, 'Blog1', 1, 'Content', '2021-07-20', 'img.png', 0),
	(10, 'Blog2', 1, '<div class="game_page_autocollapse_ctn">', '2021-07-20', 'img.png', 0),
	(11, 'Blog3', 1, '<div class="game_page_autocollapse_ctn">', '2021-07-20', 'img.png', 0);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;

-- Dumping structure for table megalodondb.category
CREATE TABLE IF NOT EXISTS `category` (
  `Id_category` int(11) NOT NULL AUTO_INCREMENT,
  `Name_category` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.category: ~18 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`Id_category`, `Name_category`) VALUES
	(1, 'Puzzle, find pairs'),
	(2, 'Racing'),
	(3, 'Sport'),
	(4, 'Countervailing'),
	(5, 'Tactic'),
	(6, 'Searching for objects'),
	(7, 'Playing cards, board games'),
	(8, 'Horror'),
	(9, 'Fashion'),
	(10, 'Role Playing - RPG'),
	(11, 'Wisdom'),
	(12, 'Simulation'),
	(13, 'Time management'),
	(14, 'Adventure'),
	(15, 'Action'),
	(16, 'Other'),
	(17, 'Free Games'),
	(18, 'Paid Games');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table megalodondb.comment_blog
CREATE TABLE IF NOT EXISTS `comment_blog` (
  `Id_commnet_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Content_comment_blog` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `Name_user` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_blog` int(11) NOT NULL,
  `Id_users` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_commnet_blog`),
  KEY `FK_Comment_blog_Id_blog_Blog_Id_blog` (`Id_blog`),
  KEY `FK_Comment_blog_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Comment_blog_Id_blog_Blog_Id_blog` FOREIGN KEY (`Id_blog`) REFERENCES `blog` (`Id_blog`),
  CONSTRAINT `FK_Comment_blog_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.comment_blog: ~2 rows (approximately)
/*!40000 ALTER TABLE `comment_blog` DISABLE KEYS */;
INSERT INTO `comment_blog` (`Id_commnet_blog`, `Content_comment_blog`, `Name_user`, `Id_blog`, `Id_users`, `date`) VALUES
	(9, 'Hello 1', '', 9, 3, '2021-07-20 15:34:41'),
	(10, 'Hello 2', '', 9, 2, '2021-07-20 15:34:59');
/*!40000 ALTER TABLE `comment_blog` ENABLE KEYS */;

-- Dumping structure for table megalodondb.comment_game
CREATE TABLE IF NOT EXISTS `comment_game` (
  `Id_comment_game` int(11) NOT NULL AUTO_INCREMENT,
  `Id_game` int(11) DEFAULT NULL,
  `Id_users` int(11) DEFAULT NULL,
  `Content_comment_game` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_comment_game`),
  KEY `FK_Comment_game_Id_game_Game_Id_game` (`Id_game`),
  KEY `FK_Comment_game_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Comment_game_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`),
  CONSTRAINT `FK_Comment_game_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.comment_game: ~0 rows (approximately)
/*!40000 ALTER TABLE `comment_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_game` ENABLE KEYS */;

-- Dumping structure for table megalodondb.discount
CREATE TABLE IF NOT EXISTS `discount` (
  `Id_discount` int(11) NOT NULL AUTO_INCREMENT,
  `Date_start` date DEFAULT NULL,
  `Date_end` date DEFAULT NULL,
  `Value` int(11) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_discount`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.discount: ~1 rows (approximately)
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` (`Id_discount`, `Date_start`, `Date_end`, `Value`, `Status`) VALUES
	(1, '2021-07-15', '2021-07-15', 0, 1);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;

-- Dumping structure for table megalodondb.games
CREATE TABLE IF NOT EXISTS `games` (
  `Id_game` int(11) NOT NULL AUTO_INCREMENT,
  `Name_game` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Producter_game` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Publisher_game` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ReleaseYear_game` date DEFAULT NULL,
  `Description_game` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Link_video` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Price` decimal(18,0) DEFAULT NULL,
  `Price_fix` decimal(18,0) DEFAULT NULL,
  `Rate_game` float DEFAULT NULL,
  `Count_sell` int(11) DEFAULT NULL,
  `Link_game` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_discount` int(11) DEFAULT NULL,
  `count_rate` int(11) DEFAULT NULL,
  `Processor` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RAM` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Free_storage` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `VGA` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_game`),
  KEY `FK_Game_Id_discount_Discount_Id_discount` (`Id_discount`),
  CONSTRAINT `FK_Game_Id_discount_Discount_Id_discount` FOREIGN KEY (`Id_discount`) REFERENCES `discount` (`Id_discount`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.games: ~2 rows (approximately)
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` (`Id_game`, `Name_game`, `Producter_game`, `Publisher_game`, `ReleaseYear_game`, `Description_game`, `Link_video`, `Price`, `Price_fix`, `Rate_game`, `Count_sell`, `Link_game`, `Id_discount`, `count_rate`, `Processor`, `RAM`, `Free_storage`, `VGA`) VALUES
	(9, 'PUBG: BATTLEGROUNDS', ' KRAFTON, Inc.', ' KRAFTON, Inc.', '2017-12-21', '<div class="game_page_autocollapse_ctn">\r\n<div id="aboutThisGame" class="game_page_autocollapse">\r\n<div id="game_area_description" class="game_area_description">\r\n<h2>ABOUT THIS GAME</h2>\r\n<strong>PLAYERUNKNOWN\'S BATTLEGROUNDS</strong>&nbsp;is a battle royale shooter that pits 100 players against each other in a struggle for survival. Gather supplies and outwit your opponents to become the last person standing.<br /><br /><strong>PLAYERUNKNOWN</strong>, aka Brendan Greene, is a pioneer of the battle royale genre and the creator of the battle royale game modes in the ARMA series and H1Z1: King of the Kill. At PUBG Corp., Greene is working with a veteran team of developers to make PUBG into the world\'s premiere battle royale experience.<br /><br />Please refer to our&nbsp;<a href="https://steamcommunity.com/linkfilter/?url=https://www.pubg.com/privacy/" target="_blank" rel="noopener"><strong>Privacy Policy</strong></a>&nbsp;for any privacy related information.</div>\r\n</div>\r\n</div>\r\n<div class="game_page_autocollapse_ctn">\r\n<div class="game_page_autocollapse">\r\n<div id="game_area_content_descriptors" class="game_area_description">\r\n<h2>MATURE CONTENT DESCRIPTION</h2>\r\n<p>The developers describe the content like this:</p>\r\n<p><em>This Game may contain content not appropriate for all ages, or may not be appropriate for viewing at work: Frequent Violence or Gore, General Mature Content</em></p>\r\n</div>\r\n</div>\r\n</div>', 'https://cdn.cloudflare.steamstatic.com/steam/apps/256842241/movie480_vp9.webm?t=1625828895', 340000, 340000, 5, 0, 'Empty', 1, 1, 'Intel Core i5-4430 / AMD FX-6300', '8 GB RAM', '40 GB available space', ' NVIDIA GeForce GTX 960 2GB / AMD Radeon R7 370 2GB'),
	(10, 'Phantasy Star Online 2 New Genesis', 'SEGA', 'SEGA', '2020-08-05', 'Phantasy Star Online 2 New Genesis, the latest chapter in the Phantasy Star Online 2 series, is here at last!', 'https://cdn.akamai.steamstatic.com/steam/apps/256840012/movie480_vp9.webm?t=1624366502', 200000, 20000, 4.9, 0, 'Emty', 1, 1, 'Intel Core i5-4430 / AMD FX-6300', '8 GB RAM', '40 GB available space', ' NVIDIA GeForce GTX 960 2GB / AMD Radeon R7 370 2GB');
/*!40000 ALTER TABLE `games` ENABLE KEYS */;

-- Dumping structure for table megalodondb.game_category
CREATE TABLE IF NOT EXISTS `game_category` (
  `Id_game` int(11) NOT NULL,
  `Id_category` int(11) NOT NULL,
  KEY `FK_Game_Category_Id_game_Game_Id_game` (`Id_game`),
  KEY `FK_Game_Category_Id_category_Category_Id_category` (`Id_category`),
  CONSTRAINT `FK_Game_Category_Id_category_Category_Id_category` FOREIGN KEY (`Id_category`) REFERENCES `category` (`Id_category`) ON DELETE CASCADE,
  CONSTRAINT `FK_Game_Category_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.game_category: ~7 rows (approximately)
/*!40000 ALTER TABLE `game_category` DISABLE KEYS */;
INSERT INTO `game_category` (`Id_game`, `Id_category`) VALUES
	(9, 2),
	(9, 3),
	(9, 14),
	(9, 15),
	(9, 16),
	(10, 15),
	(10, 14);
/*!40000 ALTER TABLE `game_category` ENABLE KEYS */;

-- Dumping structure for table megalodondb.image_data
CREATE TABLE IF NOT EXISTS `image_data` (
  `Id_image` int(11) NOT NULL AUTO_INCREMENT,
  `Name_image` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_game` int(11) NOT NULL,
  PRIMARY KEY (`Id_image`),
  KEY `FK_Image_data_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Image_data_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.image_data: ~9 rows (approximately)
/*!40000 ALTER TABLE `image_data` DISABLE KEYS */;
INSERT INTO `image_data` (`Id_image`, `Name_image`, `Id_game`) VALUES
	(1, 'pubg1.jpg', 9),
	(2, 'pubg2.jpg', 9),
	(3, 'pubg3.jpg', 9),
	(4, 'pubg4.jpg', 9),
	(5, 'pubg5.jpg', 9),
	(6, 'pubg6.jpg', 9),
	(8, 'f0.jpg', 10),
	(12, 'f1.jpg', 10),
	(13, 'f2.jpg', 10);
/*!40000 ALTER TABLE `image_data` ENABLE KEYS */;

-- Dumping structure for table megalodondb.reply_comment_blog
CREATE TABLE IF NOT EXISTS `reply_comment_blog` (
  `Id_reply_comment_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Id_commnet_blog` int(11) NOT NULL,
  `Id_users` int(11) NOT NULL,
  `Content_comment` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_reply_comment_blog`),
  KEY `FK_Reply_comment_blog_Id_commnet_blog` (`Id_commnet_blog`),
  KEY `FK_reply_comment_blog_users` (`Id_users`),
  CONSTRAINT `FK_Reply_comment_blog_Id_commnet_blog` FOREIGN KEY (`Id_commnet_blog`) REFERENCES `comment_blog` (`Id_commnet_blog`),
  CONSTRAINT `FK_reply_comment_blog_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.reply_comment_blog: ~6 rows (approximately)
/*!40000 ALTER TABLE `reply_comment_blog` DISABLE KEYS */;
INSERT INTO `reply_comment_blog` (`Id_reply_comment_blog`, `Id_commnet_blog`, `Id_users`, `Content_comment`, `date`) VALUES
	(2, 9, 1, 'hi', '2021-07-20 16:28:29'),
	(6, 9, 3, '<p>hi</p>', NULL),
	(7, 9, 3, '<p>123</p>', NULL),
	(8, 9, 3, '<p>222</p>', NULL),
	(9, 9, 3, '<p>v</p>', NULL),
	(10, 10, 3, '<p>222</p>', NULL);
/*!40000 ALTER TABLE `reply_comment_blog` ENABLE KEYS */;

-- Dumping structure for table megalodondb.reply_comment_game
CREATE TABLE IF NOT EXISTS `reply_comment_game` (
  `Id_reply_comment_game` int(11) NOT NULL AUTO_INCREMENT,
  `Id_comment_game` int(11) DEFAULT NULL,
  `Id_users` int(11) DEFAULT NULL,
  `Content_comment` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_reply_comment_game`),
  KEY `FK_Reply_comment_game_Idpppp` (`Id_comment_game`),
  KEY `FK_reply_comment_game_users` (`Id_users`),
  CONSTRAINT `FK_Reply_comment_game_Idpppp` FOREIGN KEY (`Id_comment_game`) REFERENCES `comment_game` (`Id_comment_game`),
  CONSTRAINT `FK_reply_comment_game_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.reply_comment_game: ~0 rows (approximately)
/*!40000 ALTER TABLE `reply_comment_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply_comment_game` ENABLE KEYS */;

-- Dumping structure for table megalodondb.role
CREATE TABLE IF NOT EXISTS `role` (
  `Id_role` int(11) NOT NULL AUTO_INCREMENT,
  `Name_role` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.role: ~3 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`Id_role`, `Name_role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_EMPLOYEE'),
	(3, 'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table megalodondb.slide_show
CREATE TABLE IF NOT EXISTS `slide_show` (
  `Id_slide_show` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_game` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_slide_show`),
  KEY `FK_Slide_show_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Slide_show_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.slide_show: ~4 rows (approximately)
/*!40000 ALTER TABLE `slide_show` DISABLE KEYS */;
INSERT INTO `slide_show` (`Id_slide_show`, `image`, `Id_game`) VALUES
	(4, '7_days.jpg', 9),
	(5, 'ArcheAge_sample.jpg', 9),
	(6, 'gwent3.jpg', 9),
	(7, 'little_nightmares2.jpg', 9);
/*!40000 ALTER TABLE `slide_show` ENABLE KEYS */;

-- Dumping structure for table megalodondb.systems
CREATE TABLE IF NOT EXISTS `systems` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Dowload_system` int(11) NOT NULL,
  `views_system` int(11) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.systems: ~3 rows (approximately)
/*!40000 ALTER TABLE `systems` DISABLE KEYS */;
INSERT INTO `systems` (`Id`, `Dowload_system`, `views_system`, `Date`) VALUES
	(6, 0, 69, '2021-07-15 18:42:17'),
	(7, 0, 29, '2021-07-16 08:24:22'),
	(8, 0, 2, '2021-07-19 10:10:49');
/*!40000 ALTER TABLE `systems` ENABLE KEYS */;

-- Dumping structure for table megalodondb.token_user
CREATE TABLE IF NOT EXISTS `token_user` (
  `Id_token_users` int(11) NOT NULL AUTO_INCREMENT,
  `value_token_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Id_users` int(11) NOT NULL,
  PRIMARY KEY (`Id_token_users`),
  KEY `FK_Token_user_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Token_user_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.token_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `token_user` DISABLE KEYS */;
INSERT INTO `token_user` (`Id_token_users`, `value_token_users`, `Date`, `Id_users`) VALUES
	(12, '7b725071-cc84-4b51-9619-cee2cfacd189', '2021-07-15 13:35:03', 3),
	(14, '41af3eac-3f9b-470e-900c-a50976bf7095', '2021-07-15 15:39:59', 1),
	(15, '0e2a8643-a2de-4a11-bd82-7333148dd2d8', '2021-07-15 13:38:47', 2);
/*!40000 ALTER TABLE `token_user` ENABLE KEYS */;

-- Dumping structure for table megalodondb.users
CREATE TABLE IF NOT EXISTS `users` (
  `Id_users` int(11) NOT NULL AUTO_INCREMENT,
  `Name_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Username_users` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password_users` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email_users` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Phone_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Image_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Address_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date_of_birthday` date DEFAULT NULL,
  `Gender` int(11) DEFAULT NULL,
  `Id_role` int(11) NOT NULL,
  `Status` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_users`),
  KEY `FK_Users_Id_role_Role_Id_role` (`Id_role`),
  CONSTRAINT `FK_Users_Id_role_Role_Id_role` FOREIGN KEY (`Id_role`) REFERENCES `role` (`Id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.users: ~3 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`Id_users`, `Name_users`, `Username_users`, `Password_users`, `Email_users`, `Phone_users`, `Image_users`, `Address_users`, `Date_of_birthday`, `Gender`, `Id_role`, `Status`) VALUES
	(1, 'Admin ', 'admin', '$2a$10$7eQuzE80yD.1JB7/Ap6Ls.TdqmuGG/.NaOPJdpuL/IAFWGHxXjAwy', 'shivalcrow.nq@gmail.com', '123456', 'noavatar.png', 'admin page', '2021-07-08', 1, 1, 1),
	(2, 'Employee', 'employee', '$2a$10$gtkuOC7u9EqeVZk0TenM3umWYxrnjwH/dWHLMGbpG9QrjJA1js6rC', 'conandold@gmail.com', '123456', 'noavatar.png', 'admin page', '2021-07-08', 1, 2, 1),
	(3, 'customer', 'customer', '$2a$10$oxkN5IITXDYjjq7.kP3bFO7m47Pan9lLCPydaLoZC3Uqz9QvTp8GK', 'playgaming.q7@gmail.com', ' ', 'noavatar.png', ' ', '2021-07-15', 1, 3, 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
