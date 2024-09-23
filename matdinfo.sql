-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.4.28-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour matdinfo
CREATE DATABASE IF NOT EXISTS `matdinfo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `matdinfo`;

-- Listage de la structure de la table matdinfo. appuser
CREATE TABLE IF NOT EXISTS `appuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.appuser : ~1 rows (environ)
REPLACE INTO `appuser` (`id`, `enabled`, `password`, `username`) VALUES
	(1, b'1', '$2y$10$S2HMdkdwuI13UZyYU6JCpOaMFtNPuVUVtN3ALh8p0lLi0PAG5LmXa', 'dinfo');

-- Listage de la structure de la table matdinfo. mouvement
CREATE TABLE IF NOT EXISTS `mouvement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datee` datetime(6) DEFAULT NULL,
  `observation` varchar(255) DEFAULT NULL,
  `position_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `stock_id` bigint(20) NOT NULL,
  `dateentree` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvrquwwym5kayfttektxw1vr4` (`position_id`),
  KEY `FKaxd0ynm7atmf296vfrmpwjn3i` (`status_id`),
  KEY `FK53htvuwdp7hwfldbph68qd0lj` (`stock_id`),
  CONSTRAINT `FK53htvuwdp7hwfldbph68qd0lj` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
  CONSTRAINT `FKaxd0ynm7atmf296vfrmpwjn3i` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKhvrquwwym5kayfttektxw1vr4` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.mouvement : ~23 rows (environ)
REPLACE INTO `mouvement` (`id`, `datee`, `observation`, `position_id`, `status_id`, `stock_id`, `dateentree`) VALUES
	(3, '2024-09-13 09:27:51.000000', 'test', 3, 3, 31, NULL),
	(4, '2024-09-13 09:47:37.000000', '', 1, 3, 29, NULL),
	(6, '2024-09-13 09:48:02.000000', '', 4, 3, 41, NULL),
	(7, NULL, 'rr', 4, 4, 41, '2024-09-13 13:43:14.000000'),
	(8, '2024-09-13 09:47:37.000000', 'tteee', 1, 4, 29, '2024-09-13 13:56:28.000000'),
	(9, '2024-09-13 09:27:51.000000', 'aaaaaa', 3, 4, 31, '2024-09-13 14:04:57.000000'),
	(13, '2024-09-14 08:18:52.000000', 'visionconference', 2, 3, 41, NULL),
	(14, '2024-09-14 08:18:52.000000', 'retour', 2, 4, 41, '2024-09-14 08:19:03.000000'),
	(15, '2024-09-14 09:04:53.000000', 'VISIONCONFERENCE 12', 5, 3, 30, NULL),
	(16, '2024-09-14 09:04:53.000000', 'RETOUR', 5, 4, 30, '2024-09-14 09:05:24.000000'),
	(17, '2024-09-14 09:55:57.000000', '', 4, 3, 31, NULL),
	(18, '2024-09-14 09:55:57.000000', 'retour', 4, 1, 31, '2024-09-14 09:56:09.000000'),
	(19, '2024-09-14 10:15:15.000000', 'aaaaa', 2, 3, 41, NULL),
	(20, '2024-09-14 10:15:15.000000', 'aaaaaaaaaaaaaaaaaaaaaaa', 2, 1, 41, '2024-09-15 10:15:32.000000'),
	(21, '2024-09-14 10:28:52.000000', '', 3, 3, 35, NULL),
	(22, '2024-09-17 09:09:21.000000', 'aaa', 4, 3, 36, NULL),
	(23, '2024-09-17 09:23:17.000000', 'ezrzerzerzr', 3, 3, 29, NULL),
	(24, '2024-09-17 09:23:17.000000', 'rfsdfsdf', 3, 1, 29, '2024-09-17 09:23:35.000000'),
	(25, '2024-09-17 09:44:31.000000', 'hhhhhh', 2, 3, 29, NULL),
	(26, '2024-09-17 09:44:31.000000', '', 2, 1, 29, '2024-09-17 09:47:18.000000'),
	(27, '2024-09-14 10:28:52.000000', 'ttt', 3, 1, 35, '2024-09-20 08:45:36.000000'),
	(28, '2024-09-17 09:09:21.000000', '', 4, 1, 36, '2024-09-20 09:34:45.000000'),
	(29, '2024-09-20 09:38:45.000000', '', 4, 3, 41, NULL),
	(30, '2024-09-21 08:30:48.000000', '', 3, 3, 43, NULL);

-- Listage de la structure de la table matdinfo. position
CREATE TABLE IF NOT EXISTS `position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.position : ~4 rows (environ)
REPLACE INTO `position` (`id`, `libelle`) VALUES
	(1, '3°BUREAU'),
	(2, '4°BUREAU'),
	(3, 'MESS OFFICIER'),
	(4, 'ECT'),
	(5, 'ANNEXE 4°BUREAU'),
	(6, 'azeazeaze');

-- Listage de la structure de la table matdinfo. responsable
CREATE TABLE IF NOT EXISTS `responsable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.responsable : ~0 rows (environ)

-- Listage de la structure de la table matdinfo. status
CREATE TABLE IF NOT EXISTS `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.status : ~4 rows (environ)
REPLACE INTO `status` (`id`, `libelle`) VALUES
	(1, 'DISPONIBLE'),
	(2, 'INDISPONIBLE'),
	(3, 'MISSION'),
	(4, 'ENTREE AU STOCK');

-- Listage de la structure de la table matdinfo. stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datee` datetime(6) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `nserie` varchar(255) DEFAULT NULL,
  `observation` varchar(255) DEFAULT NULL,
  `status_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3cqvoj87nxtyypkdxj01b38in` (`status_id`),
  KEY `FKau2gqum7ei4a2e612w6myyk17` (`type_id`),
  CONSTRAINT `FK3cqvoj87nxtyypkdxj01b38in` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKau2gqum7ei4a2e612w6myyk17` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.stock : ~13 rows (environ)
REPLACE INTO `stock` (`id`, `datee`, `designation`, `nserie`, `observation`, `status_id`, `type_id`) VALUES
	(29, '2024-09-13 08:57:05.000000', 'PC PORTABLE', 'SN56456454KF', 'I5 11th, 8GB RAM', 1, 6),
	(30, '2024-09-13 08:57:36.000000', 'CAMERA LOGITECH', 'SN89784564654A', 'CAMERA LOGITECH GROUP', 1, 1),
	(31, '2024-09-13 08:57:57.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(32, '2024-09-13 08:57:57.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(34, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(35, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(36, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(37, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(38, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(39, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(40, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(41, '2024-09-13 08:58:37.000000', 'SWITCH CISCO', 'SN89798789AQ', 'SWITCH CISCO 24 PORTS', 2, 2),
	(42, '2024-09-14 10:51:02.000000', 'LOGITECH 1080P PETIT', 'SN8978945641TF', '', 1, 1),
	(43, '2024-09-21 08:30:24.000000', 'Modem', 'SN87464564A', '', 2, 5);

-- Listage de la structure de la table matdinfo. type
CREATE TABLE IF NOT EXISTS `type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.type : ~6 rows (environ)
REPLACE INTO `type` (`id`, `libelle`) VALUES
	(1, 'CAMERA'),
	(2, 'SWITCH'),
	(3, 'ROTEUR'),
	(4, 'SERVEUR'),
	(5, 'MODEM'),
	(6, 'PC PORTABLE'),
	(7, 'PC BUREAU');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
