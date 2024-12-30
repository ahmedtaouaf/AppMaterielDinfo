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

-- Listage de la structure de la table matdinfo. article_vsat
CREATE TABLE IF NOT EXISTS `article_vsat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `type_article_vsat` varchar(255) NOT NULL,
  `poste_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3tlh4llyex14d9b3kf52vhvtk` (`poste_id`),
  CONSTRAINT `FK3tlh4llyex14d9b3kf52vhvtk` FOREIGN KEY (`poste_id`) REFERENCES `poste` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.article_vsat : ~36 rows (environ)
REPLACE INTO `article_vsat` (`id`, `libelle`, `status`, `type_article_vsat`, `poste_id`) VALUES
	(1, NULL, b'1', 'Parabole', 1),
	(2, NULL, b'1', 'LNB', 1),
	(3, NULL, b'1', 'MODEM TOOWAY', 1),
	(4, NULL, b'1', 'TPLINK', 1),
	(5, NULL, b'1', 'Parabole', 2),
	(6, NULL, b'1', 'LNB', 2),
	(7, NULL, b'1', 'MODEM TOOWAY', 2),
	(8, NULL, b'1', 'TPLINK', 2),
	(9, NULL, b'1', 'Parabole', 3),
	(10, NULL, b'1', 'LNB', 3),
	(11, NULL, b'1', 'MODEM TOOWAY', 3),
	(12, NULL, b'1', 'TPLINK', 3),
	(13, NULL, b'1', 'Parabole', 4),
	(14, NULL, b'1', 'LNB', 4),
	(15, NULL, b'1', 'MODEM TOOWAY', 4),
	(16, NULL, b'1', 'TPLINK', 4),
	(17, NULL, b'1', 'Parabole', 5),
	(18, NULL, b'1', 'LNB', 5),
	(19, NULL, b'1', 'MODEM TOOWAY', 5),
	(20, NULL, b'1', 'TPLINK', 5),
	(21, NULL, b'1', 'Parabole', 6),
	(22, NULL, b'1', 'LNB', 6),
	(23, NULL, b'1', 'MODEM TOOWAY', 6),
	(24, NULL, b'1', 'TPLINK', 6),
	(25, NULL, b'1', 'Parabole', 7),
	(26, NULL, b'1', 'LNB', 7),
	(27, NULL, b'1', 'MODEM TOOWAY', 7),
	(28, NULL, b'1', 'TPLINK', 7),
	(29, NULL, b'1', 'Parabole', 8),
	(30, NULL, b'1', 'LNB', 8),
	(31, NULL, b'1', 'MODEM TOOWAY', 8),
	(32, NULL, b'1', 'TPLINK', 8),
	(33, NULL, b'1', 'Parabole', 9),
	(34, NULL, b'1', 'LNB', 9),
	(35, NULL, b'1', 'MODEM TOOWAY', 9),
	(36, NULL, b'1', 'TPLINK', 9);

-- Listage de la structure de la table matdinfo. poste
CREATE TABLE IF NOT EXISTS `poste` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activatingkey` varchar(255) DEFAULT NULL,
  `adresseip` varchar(255) DEFAULT NULL,
  `dateactivation` varchar(255) DEFAULT NULL,
  `lastonline` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `typevsat` varchar(255) DEFAULT NULL,
  `uniteresp_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbjvbd8ua8qaje0y16krkuuu7s` (`uniteresp_id`),
  CONSTRAINT `FKbjvbd8ua8qaje0y16krkuuu7s` FOREIGN KEY (`uniteresp_id`) REFERENCES `unite_resp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.poste : ~9 rows (environ)
REPLACE INTO `poste` (`id`, `activatingkey`, `adresseip`, `dateactivation`, `lastonline`, `latitude`, `longitude`, `mac`, `nom`, `typevsat`, `uniteresp_id`) VALUES
	(1, '10911-wkudtwrp', '80.250.42.34 ', '02/05/2018', '24/02/2022', '30.966805', '-3.678661', '00:A0:BC:44:AC:04', 'MI12_MGAYOUN_RISSANI', 'KA', 1),
	(2, '10911-epwlcghz', '80.250.41.30 ', '02/05/2018', '24/02/2022', '30.950567', '-3.685701', '00:A0:BC:42:F7:00', 'E60_LMGHAYMIM', 'KA', 1),
	(3, '10911-cvdyicur', '80.250.42.99 ', '04/05/2018', '06/04/2022', '30.943488', '-3.687401', '00:A0:BC:49:0B:87', 'MI14_BOUKJIJANE_RISSANI', 'KA', 1),
	(4, '10911-dhftricc', '80.250.42.82 ', '03/05/2018', '08/06/2021', '30.638383', '-4.219064', '00:A0:BC:43:20:6B', 'E7', 'KA', 2),
	(5, '10911-xlffpgpg', '80.250.42.115', '01/05/2018', '03/06/2022', '30.637662', '-4.219214', '00:A0:BC:38:52:E7', 'MI3', 'KA', 2),
	(6, '10911-gsuuycsd', '80.250.42.19 ', '08/05/2018', '22/03/2022', '30.637386', '-4.219428', '00:A0:BC:3F:98:E3', 'E8', 'KA', 2),
	(7, '10911-scykhghj', '80.250.42.77', '06/03/2020', '03/06/2022', '30.587636', '-4.300218', '00:A0:BC:38:4D:1B', 'EA8 EL MOHER', 'KA', 3),
	(8, '10911-vssvplyc', '80.250.41.27 ', '06/03/2020', '11/03/2022', '30.583351', '-4.303308', '00:A0:BC:3B:2F:FD', 'M32 PRIME', 'KA', 3),
	(9, '10911-jtidahus', '80.250.41.17 ', '08/03/2020', '13/04/2022', '30.584053', '-4.299403', '00:A0:BC:3B:4E:B3', 'M34 SAFSAF 1', 'KA', 3);

-- Listage de la structure de la table matdinfo. unite_resp
CREATE TABLE IF NOT EXISTS `unite_resp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.unite_resp : ~3 rows (environ)
REPLACE INTO `unite_resp` (`id`, `latitude`, `longitude`, `nom`) VALUES
	(1, NULL, NULL, '4 BSF'),
	(2, NULL, NULL, '8 BSF'),
	(3, NULL, NULL, '11 BSF');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
