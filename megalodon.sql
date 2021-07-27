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

-- Dumping data for table megalodondb.active_game: ~2 rows (approximately)
/*!40000 ALTER TABLE `active_game` DISABLE KEYS */;
INSERT INTO `active_game` (`Id_users`, `Id_game`, `Status`) VALUES
	(35, 9, NULL),
	(3, 9, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.bill: ~3 rows (approximately)
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`Id_bill`, `Id_users`, `Date`, `Total_price`) VALUES
	(7, 3, '2021-07-23 10:36:51', 340000),
	(8, 35, '2021-07-26 16:48:50', 300),
	(9, 3, '2021-07-26 18:46:29', 300);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.bill_detail: ~3 rows (approximately)
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
INSERT INTO `bill_detail` (`Id_bill_detail`, `Id_bill`, `Id_game`, `Price`) VALUES
	(2, 7, 9, 340000),
	(3, 8, 9, 300),
	(4, 9, 9, 300);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.blog: ~3 rows (approximately)
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` (`Id_blog`, `Title_blog`, `Author_blog`, `Content_blog`, `Date_blog`, `Image_blog`, `Views`) VALUES
	(9, 'Blog1', 1, 'Content', '2021-07-20', '12890.jpg', 0),
	(10, 'Blog2', 1, '<span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">💎</span><b style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Enjoy one of the best anime style multiplayer action RPG’s of 2020</b><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">💎</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Explore the world of SoulWorker on the latest release SoulWorker Anime Legends.. Through single player or collaborative gameplay, unearth challenging missions or team up to do PvP or PvE missions.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">SoulWorker Anime Legends is inspired by its successor SoulWorker on PC, embodying 6 heroes from PC version, a legendary gameplay experience with easy to use controls with an stunning environment. With SoulWorker Anime Legends, you can finally experience what an anime style online multiplayer gameplay has to offer within the palm of your hands!</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">It is your turn to write your own legends with unlikely heroes using soul as their weapon. Throughout this captivating story, lead your champion to success by using a sword, a scythe, or even a guitar! With Stella’s swift howling guitar lessons even, your enemies will tremble against the power of your soul!</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">🔥</span><b style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Key features:</b><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• ‘Playable anime’ graphics thanks to impressive cel-shadings</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Unique battle style with personalized combos</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Intensive PvP &amp; PvE content</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Easy to use on-screen controls</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Post-apocalyptic setting with a captivating story</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">🔥</span><b style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Characters:</b><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Includes 6 Legendary characters from original Soulworker PC version:</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Haru Estia, heads into battle wielding her mighty Soulum sword.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Lily Bloommerchen’s expression of her madness takes the form of a destructive Mist Scythe.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Stella Unibell defends herself from all attacks with the Howling Guitar.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Erwin Arclight blasts back enemies making use of his Gun Jazz.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Jin fights with both fists for justice – yet the shadows of his past constantly weigh on his shoulders.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Iris Yuma unleashes her innate wrath and the rage at the tragic fate of her family through her gigantic Hammer Stol.</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">🔥</span><b style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Pleasure for both eyes and ears alike:</b><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Enjoy unique atmosphere set by both anime style graphics and unique music alike</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Customize your character with beautiful costumes</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">• Engage in a realistic anime world and write your own legend</span><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><br style="-webkit-tap-highlight-color: transparent; color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;"><span style="color: rgb(51, 51, 51); font-family: Roboto, Arial, sans-serif;">Follow us on social media for latest news &amp; updates!</span>', '2021-07-20', '12906.jpg', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.comment_blog: ~3 rows (approximately)
/*!40000 ALTER TABLE `comment_blog` DISABLE KEYS */;
INSERT INTO `comment_blog` (`Id_commnet_blog`, `Content_comment_blog`, `Name_user`, `Id_blog`, `Id_users`, `date`) VALUES
	(9, 'Hello 1', '', 9, 3, '2021-07-20 15:34:41'),
	(25, '<p>Goog game</p>', NULL, 10, 3, '2021-07-26 00:00:00'),
	(26, '<p>hi</p>', NULL, 10, 3, '2021-07-26 00:00:00'),
	(27, '<p>nice</p>', NULL, 10, 1, '2021-07-26 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.discount: ~0 rows (approximately)
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` (`Id_discount`, `Date_start`, `Date_end`, `Value`, `Status`) VALUES
	(1, '2021-07-15', '2021-07-15', 0, 1),
	(2, '2021-07-23', '2021-07-25', 10, NULL),
	(3, '2021-07-26', '2021-07-26', 100, NULL);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;

-- Dumping structure for table megalodondb.games
CREATE TABLE IF NOT EXISTS `games` (
  `Id_game` int(11) NOT NULL AUTO_INCREMENT,
  `Name_game` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Producter_game` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Publisher_game` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ReleaseYear_game` date DEFAULT NULL,
  `Description_game` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Link_video` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.games: ~8 rows (approximately)
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` (`Id_game`, `Name_game`, `Producter_game`, `Publisher_game`, `ReleaseYear_game`, `Description_game`, `Link_video`, `Price`, `Price_fix`, `Rate_game`, `Count_sell`, `Link_game`, `Id_discount`, `count_rate`, `Processor`, `RAM`, `Free_storage`, `VGA`) VALUES
	(9, 'PUBG: BATTLEGROUNDS', ' KRAFTON, Inc.', ' KRAFTON, Inc.', '2017-12-21', '<div class="game_page_autocollapse_ctn">\r\n<div id="aboutThisGame" class="game_page_autocollapse">\r\n<div id="game_area_description" class="game_area_description">\r\n<h2>ABOUT THIS GAME</h2>\r\n<strong>PLAYERUNKNOWN\'S BATTLEGROUNDS</strong>&nbsp;is a battle royale shooter that pits 100 players against each other in a struggle for survival. Gather supplies and outwit your opponents to become the last person standing.<br /><br /><strong>PLAYERUNKNOWN</strong>, aka Brendan Greene, is a pioneer of the battle royale genre and the creator of the battle royale game modes in the ARMA series and H1Z1: King of the Kill. At PUBG Corp., Greene is working with a veteran team of developers to make PUBG into the world\'s premiere battle royale experience.<br /><br />Please refer to our&nbsp;<a href="https://steamcommunity.com/linkfilter/?url=https://www.pubg.com/privacy/" target="_blank" rel="noopener"><strong>Privacy Policy</strong></a>&nbsp;for any privacy related information.</div>\r\n</div>\r\n</div>\r\n<div class="game_page_autocollapse_ctn">\r\n<div class="game_page_autocollapse">\r\n<div id="game_area_content_descriptors" class="game_area_description">\r\n<h2>MATURE CONTENT DESCRIPTION</h2>\r\n<p>The developers describe the content like this:</p>\r\n<p><em>This Game may contain content not appropriate for all ages, or may not be appropriate for viewing at work: Frequent Violence or Gore, General Mature Content</em></p>\r\n</div>\r\n</div>\r\n</div>', 'https://cdn.cloudflare.steamstatic.com/steam/apps/256842241/movie480_vp9.webm?t=1625828895', 300, 300, 4.5, 3, 'Empty', 1, 2, 'Intel Core i5-4430 / AMD FX-6300', '8 GB RAM', '40 GB available space', ' NVIDIA GeForce GTX 960 2GB / AMD Radeon R7 370 2GB'),
	(12, 'Resident Evil Village', ' CAPCOM Co., Ltd.', ' CAPCOM Co., Ltd.', '2021-07-26', '<div class="long-description show-more-description">\r\n<div class="short-description">Experience survival horror like never before in Resident Evil Village. Elevate each desperate fight to survive by showcasing the most realistic and terrifying graphics to date. The all-new title is the eighth major installment in the storied Resident Evil franchise, which established the survival horror genre nearly 25 years ago. The title is currently in development using Capcom&rsquo;s proprietary RE Engine, used to create vivid gameplay experiences in hit titles such as Resident Evil&trade; 7 biohazard, Resident Evil&trade; 2, Resident Evil&trade; 3, and Devil May Cry&trade; 5. With hyper-detailed graphics, intense first-person action and masterful storytelling, the terror has never felt more realistic and inescapable.</div>\r\n<div class="full-description">\r\n<ul>\r\n<li>Set a few years after the horrifying events in the critically acclaimed Resident Evil 7 biohazard, the all-new storyline begins with Ethan Winters and his wife Mia living peacefully in a new location, free from their past nightmares. Just as they are building their new life together, tragedy befalls them once again. Chris Redfield, the legendary hero from the Resident Evil series, is reacquainted with the couple and horribly disrupts their life, spiraling Ethan into chaos. A devastated Ethan finds himself in a remote snow-capped village seeking answers after being thrown into an entirely new nightmare.</li>\r\n<li><strong>All New Resident Evil Experience</strong>&nbsp;Picking up where Resident Evil 7 biohazard left off, Resident Evil Village is the eight h major installment in the flagship Resident Evil series The game sees the reunited Ethan and Mia Winters living happily together and putting their shared night mares of the Baker&rsquo;s plantation behind them&hellip; until their life is upended and Ethan becomes the focal point of a new nightmare</li>\r\n<li><strong>Next Generation Technology</strong>&nbsp;RE Engine paired with next gen console power will deliver photorealistic graphics, bringing the shadowy village and its haunting residents to life. Resident Evil Village will showcase the most realistic survival horror experience to date.</li>\r\n<li><strong>First Person Action</strong>&nbsp;Players will assume the role of Ethan Winters and experience every up close battle and terrifying pursuit through a first person perspective.</li>\r\n<li><strong>Familiar Faces and New Foes</strong>&nbsp;Chris Red field has typically been a hero in the Resident Evil series , but his appearance in Resident Evil Village seemingly shrouds him in sinister motives. A host of new adversaries inhabiting the enigmatic village will relentlessly hunt Ethan and hinder his every move as he attempts to make sense of the new nightmare he finds himself in.</li>\r\n<li><strong>A Living, Breathing Village</strong>&nbsp;More than just a mysterious backdrop for the horrifying events that unfold in the game, the village is a character in its own right with mysteries for Ethan to uncover and terrors to escape from.</li>\r\n</ul>\r\n</div>\r\n</div>', 'https://cdn.akamai.steamstatic.com/steam/apps/256825282/movie480_vp9.webm?t=1615523833', 200, 180, 5, 0, 'empty', 2, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '20GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM'),
	(13, 'Monster Hunter Stories 2', ' CAPCOM Co., Ltd', ' CAPCOM Co., Ltd', '2021-07-07', '<h2>ABOUT THIS GAME</h2>\r\n<p>A new adventure awaits you in this second installment of the turn-based RPG series set in the world of Monster Hunter! Become a Rider and form bonds with friendly monsters known as Monsties to fight alongside them as you take part in an epic story.<br /><br />You play as the grandson of Red, a legendary Rider. The story begins with a fateful encounter with Ena, a Wyverian girl who has been entrusted with an egg with the potential to hatch into a legendary Rathalos which could wreak havoc if awakened to its destructive power.<br /><br />Embark on a journey which will test the bonds of friendship in a changing world, and discover the truth behind the legends of old.</p>', 'https://cdn.akamai.steamstatic.com/steam/apps/1277400/ss_fbbd608be7804aa499420ebe5596889b8909a392.1920x1080.jpg?t=1626120014', 250, 225, 5, 0, 'empty', 2, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '20GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM'),
	(14, 'New World', ' Amazon Games', ' Amazon Games', '2021-07-01', '<div class="game_page_autocollapse_ctn expanded">\r\n<div id="aboutThisGame" class="game_page_autocollapse">\r\n<div id="game_area_description" class="game_area_description">\r\n<h2>ABOUT THIS GAME</h2>\r\nExplore a thrilling, open-world MMO filled with danger and opportunity where you\'ll forge a new destiny for yourself as an adventurer shipwrecked on the supernatural island of Aeternum. Endless opportunities to fight, forage, and forge await you among the island\'s wilderness and ruins. Channel supernatural forces or wield deadly weapons in a classless, real-time combat system, and fight alone, with a small team, or in massed armies for PvE and PvP battles&mdash;the choices are all yours.\r\n<h2 class="bb_tag">KEY FEATURES:</h2>\r\n<br /><img src="https://cdn.akamai.steamstatic.com/steam/apps/1063730/extras/NW_Carve.gif?t=1626796967" /><br /><strong>CARVE YOUR DESTINY</strong><br />For thousands of years, Aeternum has been the source of fantastical legends&mdash;and now you&rsquo;ve found it. Shipwrecked and without supplies or allies, you&rsquo;ll need to make your way in a dangerous world where supernatural power has changed all the rules. In such a land, your destiny is whatever you make of it.<br /><br /><br /><img src="https://cdn.akamai.steamstatic.com/steam/apps/1063730/extras/NW_Land_Magic_2.gif?t=1626796967" /><br /><strong>A LAND SHAPED BY MAGIC</strong><br />Aeternum\'s mysteries run as deep and dark as its history. Delve into the world and uncover the secret truth of the island and its millennia of strange inhabitants. As you explore Aeternum, you&rsquo;ll discover beauty, danger, and opportunity at every turn. You\'ll need to use all your skills to take advantage of the island\'s bounty&mdash;and survive its horrors.<br /><br /><br /><img src="https://cdn.akamai.steamstatic.com/steam/apps/1063730/extras/NW_Sword_Sorcery_2.gif?t=1626796967" /><br /><strong>SWORDS, GUNS &amp; SORCERY</strong><br />Arm yourself with brutal melee weapons, ranged artillery, or supernatural powers and jump into New World\'s classless, real-time action combat system. As you progress you\'ll be able to determine what you want your gameplay experience to be like&mdash;will you act as a protective shield on the front lines of battle? Will you sling spells to support your allies from a safe distance? Only you can decide.<br /><br /><br /><img src="https://cdn.akamai.steamstatic.com/steam/apps/1063730/extras/NW_Stronger_Together.gif?t=1626796967" /><br /><strong>STRONGER TOGETHER</strong><br />At the core of the New World&rsquo;s social features are the three factions, organizations of like-minded players and non-player characters with their own motives and schemes for the island\'s future. It is as a member of one of these factions that you\'ll wage war and claim, defend, and develop your territory.</div>\r\n</div>\r\n</div>\r\n<div class="game_page_autocollapse_ctn">\r\n<div class="game_page_autocollapse">\r\n<div id="game_area_content_descriptors" class="game_area_description">\r\n<h2>MATURE CONTENT DESCRIPTION</h2>\r\n<p>The developers describe the content like this:</p>\r\n<p><em>This Game may contain content not appropriate for all ages, or may not be appropriate for viewing at work: Frequent Violence</em></p>\r\n</div>\r\n</div>\r\n</div>', 'https://cdn.akamai.steamstatic.com/steam/apps/256838913/movie480_vp9.webm?t=1623772836', 180, 180, 5, 0, 'empty', 1, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '50GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM'),
	(15, 'Counter-Strike', ' Valve', ' Valve', '2021-06-17', '<div class="game_page_autocollapse_ctn">\r\n<div id="aboutThisGame" class="game_page_autocollapse">\r\n<div id="game_area_description" class="game_area_description">\r\n<h2>ABOUT THIS GAME</h2>\r\nCounter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago.<br /><br />CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content (de_dust2, etc.).<br /><br />"Counter-Strike took the gaming industry by surprise when the unlikely MOD became the most played online PC action game in the world almost immediately after its release in August 1999," said Doug Lombardi at Valve. "For the past 12 years, it has continued to be one of the most-played games in the world, headline competitive gaming tournaments and selling over 25 million units worldwide across the franchise. CS: GO promises to expand on CS\' award-winning gameplay and deliver it to gamers on the PC as well as the next gen consoles and the Mac."</div>\r\n</div>\r\n</div>\r\n<div class="game_page_autocollapse_ctn">\r\n<div class="game_page_autocollapse">\r\n<div id="game_area_content_descriptors" class="game_area_description">\r\n<h2>MATURE CONTENT DESCRIPTION</h2>\r\n<p>The developers describe the content like this:</p>\r\n<p><em>Includes intense violence and blood.</em></p>\r\n</div>\r\n</div>\r\n</div>', 'https://cdn.akamai.steamstatic.com/steam/apps/81958/movie480.webm?t=1554409259', 0, 0, 5, 0, 'empty', 3, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '20GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM'),
	(16, 'Orcs Must Die! 3', ' Robot Entertainment', ' Robot Entertainment', '2021-07-14', '<h2>ABOUT THIS GAME</h2>\r\n<p><img src="https://cdn.cloudflare.steamstatic.com/steam/apps/1522820/extras/omd_steam_gif_v2.gif?t=1627049071" /><br /><strong>Orcs Must Die! 3</strong>&nbsp;ushers orc-slaying mayhem to a previously unimaginable scale. Solo or with a friend by your side, arm yourself with a massive arsenal of traps and weapons. Slice, burn, toss and zap hordes of repugnant orcs in this long-awaited successor to the award-winning series.<br /><br />New to the series, War Scenarios pit players against the largest orc armies ever assembled. Mountable War Machines give players the essential firepower to heave, stab, carbonize, and disarticulate the abominable intruders.<br /><br /><strong>More of Everything</strong>&nbsp;- Orcs Must Die! 3 is everything fans loved about the first two games and more. More orcs, more traps, more weapons, more upgrades and even better looking. It&rsquo;s cranked up to at least eleven.<br /><br /><strong>New Story</strong>&nbsp;- Play through a brand new story set more than 20 years after Orcs Must Die! 2, where the War Mage and Sorceress have rebuilt the order and trained new young apprentices.<br /><br /><strong>War Scenarios</strong>&nbsp;- All new War Scenarios deliver on the promise of massive scale first set out in Orcs Must Die! Confront overwhelming armies of orcs outside on the battlefields surrounding the castles. Thin out waves of orcs hundreds strong before they breach the walls and crash your rift.<br /><br /><strong>War Machines</strong>&nbsp;- You&rsquo;re going to need new weapons of death and destruction to handle these hordes. War Machines are traps on an oversized scale. Lay down your mega flip trap and launch dozens of ragdolling orcs. Mount your mega boom barrel launcher and unleash pyrotechnic glory.<br /><br /><strong>It Never Stops</strong>&nbsp;- The legions of orcs keep coming long after the story is completed. Etch your name into the orc-slaying hall of fame through Weekly Challenges or see how long you can survive in Endless Mode.<br /><br /><strong>Take Drastic Steps</strong>&nbsp;- Orcs Must Die! 3 comes with the Drastic Steps campaign and content for free, including terrifying flying enemies, heroic war guardians and of course more tools for orc destruction!<br /><br /><strong>Scramble to Survive</strong>&nbsp;- The new Scramble Mode pits you against vile orcs who evolve with increasingly difficult and sinister tricks up their chunky sleeves. But each level you survive, you collect your own modifiers to fight back with!</p>', 'https://cdn.akamai.steamstatic.com/steam/apps/256842963/movie480_vp9.webm?t=1627049067', 18, 18, 5, 0, 'empty', 1, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '20GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM'),
	(17, 'Days Gone', ' Bend Studio', ' PlayStation Mobile, Inc.', '2021-07-15', '<div class="game_page_autocollapse_ctn">\r\n<div id="aboutThisGame" class="game_page_autocollapse">\r\n<div id="game_area_description" class="game_area_description">\r\n<h2>ABOUT THIS GAME</h2>\r\nDays Gone is an open-world action-adventure game set in a harsh wilderness two years after a devastating global pandemic.<br /><br />Step into the dirt flecked shoes of former outlaw biker Deacon St. John, a bounty hunter trying to find a reason to live in a land surrounded by death. Scavenge through abandoned settlements for equipment to craft valuable items and weapons, or take your chances with other survivors trying to eke out a living through fair trade&hellip; or more violent means.<br /><br />KEY FEATURES<br />&bull; A striking setting: From forests and meadows, to snowy plains and desert lava fields, the Pacific Northwest is both beautiful and lethal. Explore a variety of mountains, caves, mines and small rural towns, scarred by millions of years of volcanic activity.<br />&bull; Brutal encounters: With vicious gangs and hordes of Freakers roaming the land, you&rsquo;ll need to make full use of a variety of customizable traps, weapons, and upgradable skills to stay alive. Don&rsquo;t forget your Drifter bike, an invaluable tool in a vast land.<br />&bull; An ever-changing environment: Jump on the saddle of Deacon&rsquo;s trusty motorbike and explore a dynamic world dramatically affected by the weather, a dramatic day/night cycle and the evolving Freakers, who adjust to their surroundings &ndash; and the people in it.<br />&bull; A compelling story: Lose yourself in a powerful tale of desperation, betrayal and regret, as Deacon St. John searches for hope after suffering a deep, personal loss. What makes us human when faced with the daily struggle for survival?<br /><br />INCLUDES<br />&bull; New Game Plus<br />&bull; Survival Mode<br />&bull; Challenge Mode<br />&bull; Bike Skins<br /><br />PC features include ultra-wide monitor support, unlocked framerates and improved graphics (increased level of details, field of view, foliage draw distances).</div>\r\n</div>\r\n</div>\r\n<div class="game_page_autocollapse_ctn">\r\n<div class="game_page_autocollapse">\r\n<div id="game_area_content_descriptors" class="game_area_description">\r\n<h2>MATURE CONTENT DESCRIPTION</h2>\r\n<p>The developers describe the content like this:</p>\r\n<p><em>This Game may contain content not appropriate for all ages, or may not be appropriate for viewing at work: Frequent Violence or Gore, General Mature Content</em></p>\r\n</div>\r\n</div>\r\n</div>', 'https://cdn.akamai.steamstatic.com/steam/apps/256835018/movie480_vp9.webm?t=1621341084', 210, 189, 5, 0, 'empty', 2, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '20GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM'),
	(18, 'Anima : The Reign of Darkness', ' Redeev', ' Redeev', '2021-07-26', '<div class="game_page_autocollapse_ctn expanded">\r\n<div id="aboutThisGame" class="game_page_autocollapse">\r\n<div id="game_area_description" class="game_area_description">\r\n<h2>ABOUT THIS GAME</h2>\r\nAnima is an action RPG (hack\'n slash) videogame inspired by the greatest old school games and made with passion by RPG lovers for RPG lovers.<br />After the great success achieved on mobile devices, anima finally arrives on PC with brand new graphics and gameplay!<br />Conquer the single player campaign with potentially infinite game difficulties, follow the storyline or simply go on, slash enemies, loot items and improve your character!<br />Go down and explore the abyss, Kills Demons, Beast, Dark knights and other demonic creatures that populate over 100 levels and then challenge your skills with engaging boss fight! Explore different dark scenarios, reveal hidden secrets and Explore unique locations!<img src="../plugins/tinymce/plugins/emoticons/img/smiley-embarassed.gif" alt="embarassed" /><br /><br /><strong>EXPLORE A DARK WORLD</strong><br /><br />- Suggestive Dark fantasy environment<br />- Single Player Campain<br />- Fast-paced Action<br />- 100+ different playable levels<br />- 10+ games difficulty to test your power<br />- Secret unique levels<br />- Exciting Boss fights<br />- Stunning soundtrack<br /><br /><strong>CUSTOMIZE YOUR CHARACTER AND TEST YOUR SKILLS</strong><br />Choose your specialization between Skirmish, Archery, Sorcery, Necromancy and Sanctity and try unique combo with the improved multiclass system. Level Up your character and learn new strong abilities through five different skill trees:<br />- Level up your character and assign attributes and skills point<br />- Unlock more than 60 unique skills<br />- Choose from five different specializations<br />- Create unique combo with the Multi-class system<br /><br /><strong>LOOT POWERFUL LEGENDARY EQUIPMENT</strong><br />Slash horde of monsters or bet your gold on the gambler to find ever powerful items and empower your equipment with the upgrade and the infuse systems. Adorn your equipment pieces with more than 8 different upgradable Gems.<br />- Find more than 200 items of different rarity (normal, magic, rare, set and legendary)<br />- Equip powerful legendary items with unique power<br />- Upgrade system to increase your item powers<br />- Infuse two leggendary items to create a new powerful one<br />- 8 different kind of precious gem with 10 level of rarity</div>\r\n</div>\r\n</div>\r\n<div class="game_page_autocollapse_ctn">\r\n<div class="game_page_autocollapse">\r\n<div id="game_area_content_descriptors" class="game_area_description">\r\n<h2>MATURE CONTENT DESCRIPTION</h2>\r\n<p>The developers describe the content like this:</p>\r\n<p><em>The fights in the game can result in violence and blood split</em></p>\r\n</div>\r\n</div>\r\n</div>', 'https://cdn.akamai.steamstatic.com/steam/apps/1629200/ss_27b22505488196f96ffc0fef05f6055a528ffcf9.1920x1080.jpg?t=1622626783', 100, 90, 5, 0, 'empty', 2, 1, 'AMD Ryzen 3 1200 ／ Intel Core i5-7500', '8GB RAM', '20GB', 'AMD Radeon RX 560 with 4GB VRAM ／ NVIDIA GeForce GTX 1050 Ti with 4GB VRAM');
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

-- Dumping data for table megalodondb.game_category: ~27 rows (approximately)
/*!40000 ALTER TABLE `game_category` DISABLE KEYS */;
INSERT INTO `game_category` (`Id_game`, `Id_category`) VALUES
	(9, 2),
	(9, 3),
	(9, 14),
	(9, 15),
	(9, 16),
	(12, 8),
	(12, 10),
	(12, 14),
	(12, 15),
	(13, 10),
	(13, 14),
	(13, 15),
	(14, 10),
	(14, 14),
	(14, 15),
	(14, 16),
	(15, 10),
	(15, 14),
	(15, 15),
	(15, 17),
	(16, 14),
	(16, 15),
	(17, 8),
	(17, 15),
	(17, 16),
	(18, 10),
	(18, 14),
	(18, 15);
/*!40000 ALTER TABLE `game_category` ENABLE KEYS */;

-- Dumping structure for table megalodondb.image_data
CREATE TABLE IF NOT EXISTS `image_data` (
  `Id_image` int(11) NOT NULL AUTO_INCREMENT,
  `Name_image` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Id_game` int(11) NOT NULL,
  PRIMARY KEY (`Id_image`),
  KEY `FK_Image_data_Id_game_Game_Id_game` (`Id_game`),
  CONSTRAINT `FK_Image_data_Id_game_Game_Id_game` FOREIGN KEY (`Id_game`) REFERENCES `games` (`Id_game`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.image_data: ~28 rows (approximately)
/*!40000 ALTER TABLE `image_data` DISABLE KEYS */;
INSERT INTO `image_data` (`Id_image`, `Name_image`, `Id_game`) VALUES
	(1, 'pubg1.jpg', 9),
	(2, 'pubg2.jpg', 9),
	(3, 'pubg3.jpg', 9),
	(4, 'pubg4.jpg', 9),
	(5, 'pubg5.jpg', 9),
	(6, 'pubg6.jpg', 9),
	(14, 'pubg1.jpg', 9),
	(17, 'game-resident-evil-villag.jpg', 12),
	(18, 'nhin.jpg', 12),
	(19, 'tai-resident-evil-village.jpg', 12),
	(20, 'MHS.jpg', 13),
	(21, 'MHS1.jpg', 13),
	(22, 'MHS3.jpg', 13),
	(23, 'MHS4.jpg', 13),
	(24, 'NW.png', 14),
	(25, 'NW1.jpg', 14),
	(26, 'NW2.jpg', 14),
	(27, 'CS.jpg', 15),
	(28, 'CS1.jpg', 15),
	(29, 'CS2.jpg', 15),
	(30, 'CS3.jpg', 15),
	(31, 'O.jpg', 16),
	(32, 'O1.jpg', 16),
	(33, 'O2.webp', 16),
	(34, 'DG3.jpg', 17),
	(35, 'DG.jpg', 17),
	(36, 'Dg1.png', 17),
	(37, 'DG2.jpg', 17),
	(38, 'anima.jpg', 18),
	(39, 'anima1.jpg', 18),
	(40, 'anima3.jpg', 18);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.reply_comment_blog: ~5 rows (approximately)
/*!40000 ALTER TABLE `reply_comment_blog` DISABLE KEYS */;
INSERT INTO `reply_comment_blog` (`Id_reply_comment_blog`, `Id_commnet_blog`, `Id_users`, `Content_comment`, `date`) VALUES
	(2, 9, 1, 'hi', '2021-07-20 16:28:29'),
	(11, 9, 1, '<p>hi</p>\r\n<p>hello</p>', '2021-07-23 00:00:00'),
	(12, 9, 1, '<p>hello</p>', '2021-07-23 00:00:00'),
	(13, 9, 3, '<p>123</p>', '2021-07-23 00:00:00'),
	(14, 9, 3, '<p>qưe</p>', '2021-07-23 00:00:00'),
	(15, 25, 3, '<p>good No goog</p>', '2021-07-26 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.slide_show: ~3 rows (approximately)
/*!40000 ALTER TABLE `slide_show` DISABLE KEYS */;
INSERT INTO `slide_show` (`Id_slide_show`, `image`, `Id_game`) VALUES
	(8, 'DG.jpg', 17),
	(9, 'CS1.jpg', 15),
	(10, 'nhin.jpg', 12);
/*!40000 ALTER TABLE `slide_show` ENABLE KEYS */;

-- Dumping structure for table megalodondb.systems
CREATE TABLE IF NOT EXISTS `systems` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Dowload_system` int(11) NOT NULL,
  `views_system` int(11) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.systems: ~6 rows (approximately)
/*!40000 ALTER TABLE `systems` DISABLE KEYS */;
INSERT INTO `systems` (`Id`, `Dowload_system`, `views_system`, `Date`) VALUES
	(6, 1, 69, '2021-07-15 18:42:17'),
	(7, 2, 29, '2021-07-16 08:24:22'),
	(8, 3, 2, '2021-07-19 10:10:49'),
	(9, 2, 1, '2021-07-22 10:25:47'),
	(10, 0, 47, '2021-07-23 00:57:59'),
	(11, 0, 1, '2021-07-24 00:40:04'),
	(12, 0, 82, '2021-07-26 08:11:23');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.token_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `token_user` DISABLE KEYS */;
INSERT INTO `token_user` (`Id_token_users`, `value_token_users`, `Date`, `Id_users`) VALUES
	(12, '7b725071-cc84-4b51-9619-cee2cfacd189', '2021-07-15 13:35:03', 3),
	(14, '98a044de-13e6-4172-ac7e-c590452a2a16', '2021-07-15 15:39:59', 1),
	(16, 'd24fcadc-6b56-430c-9f44-d967c53c69b7', '2021-07-26 16:43:11', 35);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table megalodondb.users: ~4 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`Id_users`, `Name_users`, `Username_users`, `Password_users`, `Email_users`, `Phone_users`, `Image_users`, `Address_users`, `Date_of_birthday`, `Gender`, `Id_role`, `Status`) VALUES
	(1, 'Admin ', 'admin', '$2a$10$XBItWMbc8FDN0IrMbAsO0uHNv2g74ZhmOp0uChaMRaNs00jcr9nlC', 'shivalcrow.nq@gmail.com', '123456', 'logox.jpg', 'admin page', '2021-07-08', 1, 1, 1),
	(3, 'customer', 'customer', '$2a$10$oxkN5IITXDYjjq7.kP3bFO7m47Pan9lLCPydaLoZC3Uqz9QvTp8GK', 'playgaming.q7@gmail.com', ' ', 'noavatar.png', 'adminpage', '2021-07-15', 1, 3, 1),
	(34, 'employee', 'employee', '$2a$10$7eQuzE80yD.1JB7/Ap6Ls.TdqmuGG/.NaOPJdpuL/IAFWGHxXjAwy', 'shivalcro2w.nq@gmail.com', '123', 'noavatar.png', 'employy', '2021-07-23', 1, 2, 1),
	(35, 'son', 'son', '$2a$10$WAzxCgWrK7msdNXA24gsuOf8QbW56z/cgToFTUPppPmT.BhOtbdZa', 'Conandold@gmail.com', ' ', 'noavatar.png', ' ', '2021-07-26', 1, 3, 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
