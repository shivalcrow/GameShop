-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
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
  CONSTRAINT `FK_Active_game_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `game` (`Id_game`),
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
  CONSTRAINT `FK_Bill_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  CONSTRAINT `FK_Bill_detail_Id_bill_Bill_Id_bill` FOREIGN KEY (`Id_bill`) REFERENCES `bill` (`Id_bill`),
  CONSTRAINT `FK_Bill_detail_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `game` (`Id_game`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.bill_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;

-- Dumping structure for table megalodondb.blog
CREATE TABLE IF NOT EXISTS `blog` (
  `Id_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Title_blog` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Author_blog` int(11) NOT NULL,
  `Content_blog` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date_blog` date DEFAULT NULL,
  `Image_blog` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Id_view_blog` int(11) NOT NULL,
  PRIMARY KEY (`Id_blog`),
  KEY `FK_Blog_Author_blog_Users_Id_users` (`Author_blog`),
  KEY `FK_Blog_Id_view_blog_View_blog_Id_view_blog` (`Id_view_blog`),
  CONSTRAINT `FK_Blog_Author_blog_Users_Id_users` FOREIGN KEY (`Author_blog`) REFERENCES `users` (`Id_users`),
  CONSTRAINT `FK_Blog_Id_view_blog_View_blog_Id_view_blog` FOREIGN KEY (`Id_view_blog`) REFERENCES `view_blog` (`Id_view_blog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.blog: ~0 rows (approximately)
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;

-- Dumping structure for table megalodondb.category
CREATE TABLE IF NOT EXISTS `category` (
  `Id_category` int(11) NOT NULL AUTO_INCREMENT,
  `Name_category` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.category: ~0 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table megalodondb.comment_blog
CREATE TABLE IF NOT EXISTS `comment_blog` (
  `Id_commnet_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Content_comment_blog` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Name_user` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Id_blog` int(11) NOT NULL,
  `Id_users` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_commnet_blog`),
  KEY `FK_Comment_blog_Id_blog_Blog_Id_blog` (`Id_blog`),
  KEY `FK_Comment_blog_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Comment_blog_Id_blog_Blog_Id_blog` FOREIGN KEY (`Id_blog`) REFERENCES `blog` (`Id_blog`),
  CONSTRAINT `FK_Comment_blog_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.comment_blog: ~0 rows (approximately)
/*!40000 ALTER TABLE `comment_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_blog` ENABLE KEYS */;

-- Dumping structure for table megalodondb.comment_game
CREATE TABLE IF NOT EXISTS `comment_game` (
  `Id_comment_game` int(11) NOT NULL AUTO_INCREMENT,
  `Id_game` int(11) DEFAULT NULL,
  `Id_users` int(11) DEFAULT NULL,
  `Content_comment_game` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_comment_game`),
  KEY `FK_Comment_game_Id_game_Game_Id_game` (`Id_game`),
  KEY `FK_Comment_game_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Comment_game_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `game` (`Id_game`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.discount: ~0 rows (approximately)
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;

-- Dumping structure for table megalodondb.game
CREATE TABLE IF NOT EXISTS `game` (
  `Id_game` int(11) NOT NULL AUTO_INCREMENT,
  `Name_game` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Producter_game` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Publisher_game` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ReleaseYear_game` date DEFAULT NULL,
  `id_system` int(11) NOT NULL,
  `Description_game` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Link_video` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Price` decimal(18,0) DEFAULT NULL,
  `Price_fix` decimal(18,0) DEFAULT NULL,
  `Rate_game` float DEFAULT NULL,
  `Count_sell` int(11) DEFAULT NULL,
  `Link_game` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_discount` int(11) DEFAULT NULL,
  `count_rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_game`),
  KEY `FK_Game_id_system_System_requirements_Id_system_rerequirements` (`id_system`),
  KEY `FK_Game_Id_discount_Discount_Id_discount` (`Id_discount`),
  CONSTRAINT `FK_Game_Id_discount_Discount_Id_discount` FOREIGN KEY (`Id_discount`) REFERENCES `discount` (`Id_discount`),
  CONSTRAINT `FK_Game_id_system_System_requirements_Id_system_rerequirements` FOREIGN KEY (`id_system`) REFERENCES `system_requirements` (`Id_system_rerequirements`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.game: ~0 rows (approximately)
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;

-- Dumping structure for table megalodondb.game_category
CREATE TABLE IF NOT EXISTS `game_category` (
  `Id_game` int(11) NOT NULL,
  `Id_category` int(11) NOT NULL,
  KEY `FK_Game_Category_Id_game_Game_Id_game` (`Id_game`),
  KEY `FK_Game_Category_Id_category_Category_Id_category` (`Id_category`),
  CONSTRAINT `FK_Game_Category_Id_category_Category_Id_category` FOREIGN KEY (`Id_category`) REFERENCES `category` (`Id_category`),
  CONSTRAINT `FK_Game_Category_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `game` (`Id_game`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.game_category: ~0 rows (approximately)
/*!40000 ALTER TABLE `game_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_category` ENABLE KEYS */;

-- Dumping structure for table megalodondb.image_data
CREATE TABLE IF NOT EXISTS `image_data` (
  `Id_image` int(11) NOT NULL AUTO_INCREMENT,
  `Name_image` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_game` int(11) NOT NULL,
  PRIMARY KEY (`Id_image`),
  KEY `FK_Image_data_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Image_data_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `game` (`Id_game`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.image_data: ~0 rows (approximately)
/*!40000 ALTER TABLE `image_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `image_data` ENABLE KEYS */;

-- Dumping structure for table megalodondb.reply_comment_blog
CREATE TABLE IF NOT EXISTS `reply_comment_blog` (
  `Id_reply_comment_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Id_commnet_blog` int(11) NOT NULL,
  `Id_users` int(11) NOT NULL,
  `Content_comment` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_reply_comment_blog`),
  KEY `FK_Reply_comment_blog_Id_commnet_blog` (`Id_commnet_blog`),
  CONSTRAINT `FK_Reply_comment_blog_Id_commnet_blog` FOREIGN KEY (`Id_commnet_blog`) REFERENCES `comment_blog` (`Id_commnet_blog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.reply_comment_blog: ~0 rows (approximately)
/*!40000 ALTER TABLE `reply_comment_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply_comment_blog` ENABLE KEYS */;

-- Dumping structure for table megalodondb.reply_comment_game
CREATE TABLE IF NOT EXISTS `reply_comment_game` (
  `Id_reply_comment_game` int(11) NOT NULL AUTO_INCREMENT,
  `Id_comment_game` int(11) DEFAULT NULL,
  `Id_users` int(11) DEFAULT NULL,
  `Content_comment` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_reply_comment_game`),
  KEY `FK_Reply_comment_game_Idpppp` (`Id_comment_game`),
  CONSTRAINT `FK_Reply_comment_game_Idpppp` FOREIGN KEY (`Id_comment_game`) REFERENCES `comment_game` (`Id_comment_game`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.reply_comment_game: ~0 rows (approximately)
/*!40000 ALTER TABLE `reply_comment_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply_comment_game` ENABLE KEYS */;

-- Dumping structure for table megalodondb.role
CREATE TABLE IF NOT EXISTS `role` (
  `Id_role` int(11) NOT NULL AUTO_INCREMENT,
  `Name_role` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.role: ~0 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table megalodondb.slide_show
CREATE TABLE IF NOT EXISTS `slide_show` (
  `Id_slide_show` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_game` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_slide_show`),
  KEY `FK_Slide_show_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Slide_show_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `game` (`Id_game`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.slide_show: ~0 rows (approximately)
/*!40000 ALTER TABLE `slide_show` DISABLE KEYS */;
/*!40000 ALTER TABLE `slide_show` ENABLE KEYS */;

-- Dumping structure for table megalodondb.system
CREATE TABLE IF NOT EXISTS `system` (
  `Dowload_system` int(11) NOT NULL,
  `views_system` int(11) NOT NULL,
  `Date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.system: ~0 rows (approximately)
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
/*!40000 ALTER TABLE `system` ENABLE KEYS */;

-- Dumping structure for table megalodondb.system_requirements
CREATE TABLE IF NOT EXISTS `system_requirements` (
  `Id_system_rerequirements` int(11) NOT NULL AUTO_INCREMENT,
  `Processor` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RAM` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `VGA` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Storage` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id_system_rerequirements`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.system_requirements: ~0 rows (approximately)
/*!40000 ALTER TABLE `system_requirements` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_requirements` ENABLE KEYS */;

-- Dumping structure for table megalodondb.token_user
CREATE TABLE IF NOT EXISTS `token_user` (
  `Id_token_users` int(11) NOT NULL AUTO_INCREMENT,
  `value_token_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Id_users` int(11) NOT NULL,
  PRIMARY KEY (`Id_token_users`),
  KEY `FK_Token_user_Id_users_Users_Id_users` (`Id_users`),
  CONSTRAINT `FK_Token_user_Id_users_Users_Id_users` FOREIGN KEY (`Id_users`) REFERENCES `users` (`Id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.token_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `token_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `token_user` ENABLE KEYS */;

-- Dumping structure for table megalodondb.users
CREATE TABLE IF NOT EXISTS `users` (
  `Id_users` int(11) NOT NULL AUTO_INCREMENT,
  `Name_users` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Username_users` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password_users` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table megalodondb.view_blog
CREATE TABLE IF NOT EXISTS `view_blog` (
  `Id_view_blog` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `count_view` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_view_blog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.view_blog: ~0 rows (approximately)
/*!40000 ALTER TABLE `view_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `view_blog` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
