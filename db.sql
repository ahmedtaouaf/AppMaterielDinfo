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

-- Listage de la structure de la table matdinfo. adressip
CREATE TABLE IF NOT EXISTS `adressip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) NOT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `materiel` varchar(255) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `temporaire` bit(1) DEFAULT NULL,
  `division_designation` varchar(255) DEFAULT NULL,
  `organe_id` bigint(20) NOT NULL,
  `resaux_nom` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4va3s8ugotipbhqn2fmt8d673` (`ip`),
  KEY `FKn8099tpvhoftr88a26qqfwghg` (`division_designation`),
  KEY `FK5xynkcpimdf5cqhvjttbr500d` (`organe_id`),
  KEY `FKfwboe12bhqui1ym2ms87pp1o4` (`resaux_nom`),
  CONSTRAINT `FK5xynkcpimdf5cqhvjttbr500d` FOREIGN KEY (`organe_id`) REFERENCES `organe` (`id`),
  CONSTRAINT `FKfwboe12bhqui1ym2ms87pp1o4` FOREIGN KEY (`resaux_nom`) REFERENCES `resaux` (`nom`),
  CONSTRAINT `FKn8099tpvhoftr88a26qqfwghg` FOREIGN KEY (`division_designation`) REFERENCES `division` (`designation`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.adressip : ~158 rows (environ)
REPLACE INTO `adressip` (`id`, `ip`, `mac`, `materiel`, `responsable`, `service`, `temporaire`, `division_designation`, `organe_id`, `resaux_nom`, `designation`) VALUES
	(1, '172.16.0.111', NULL, 'MODEM ZYXCEL ', 'INSPECTEUR ADJOINT', 'WIFI-NAVIGATION', b'0', 'CABINET', 1, 'INTERNET', 'Trans_ADJ-INSPECTEUR-ZYXEL'),
	(2, '172.16.0.154', NULL, 'PC PORTABLE', 'INSPECTEUR ADJOINT', 'NAVIGATION', b'0', 'CABINET', 1, 'INTERNET', 'Trans_InspecteurAdj'),
	(3, '172.16.0.223', NULL, 'PC BUREAU', 'CHEF CABINET', 'NAVIGATION', b'0', 'CABINET', 1, 'INTERNET', 'Trans_ChefCabinet'),
	(4, '172.16.0.213', NULL, 'PC BUREAU', 'ATEELIER INSP.TRANS', 'NAVIGATION', b'0', 'ATTELIER DINFO', 1, 'INTERNET', 'Trans_DINFO_Atelier'),
	(5, '172.16.0.188', NULL, 'PC PORTABLE', 'CTI ', 'NAVIGATION', b'0', 'CTI', 1, 'INTERNET', 'Trans_CTI_ADMIN'),
	(6, '172.16.0.155', NULL, 'PC PORTABLE', 'CDT CTI', 'NAVIGATION', b'0', 'CTI', 1, 'INTERNET', 'TRANS_CTI_Cdt'),
	(7, '172.16.0.189', NULL, 'PC PORTABLE', 'SECRETARIAT CTI', 'NAVIGATION', b'0', 'CTI', 1, 'INTERNET', 'TRANS_CTI_SCRT'),
	(8, '172.16.0.244', NULL, 'PC PORTABLE', 'CHEF DT', 'NAVIGATION', b'0', 'DT', 1, 'INTERNET', 'Trans_ChefDT'),
	(9, '172.16.0.187/32', NULL, 'PC PORTABLE', 'ADJOINT DEE', 'NAVIGATION', b'0', 'DEE', 1, 'INTERNET', 'Trans_ADJ-DEE'),
	(10, '172.16.0.136', NULL, 'PC PORTABLE', 'CHEF DEE', 'NAVIGATION', b'0', 'DEE', 1, 'INTERNET', 'TRANS_DEE_CHEF'),
	(11, '172.16.0.186', NULL, 'PC PORTABLE', 'CHEF DEE', 'NAVIGATION', b'0', 'DEE', 1, 'INTERNET', 'Trans_DEE_CHEF'),
	(12, '172.16.0.133', NULL, 'PC PORTABLE', 'DGCD LT BALYAZID', 'NAVIGATION', b'0', 'DGCD', 1, 'INTERNET', 'Trans_DGCD_BALYAZID'),
	(13, '172.16.0.138', NULL, 'PC PORTABLE', 'DGCD_REPRODUCTION', 'NAVIGATION', b'0', 'DGCD', 1, 'INTERNET', 'Trans_DGCD_REPRODUCTION'),
	(14, '172.16.0.137', NULL, 'PC PORTABLE', 'CHEF DGCD', 'NAVIGATION', b'0', 'DGCD', 1, 'INTERNET', 'TRANS_DGCD_CHEF'),
	(15, '172.16.0.140', NULL, 'PC BUREAU', 'ATELIER DGE', 'NAVIGATION', b'0', 'DGE', 1, 'INTERNET', 'Trans_DGE_Atelier'),
	(16, '172.16.0.157', NULL, 'PC PORTABLE', 'CHEF DGE', 'NAVIGATION', b'0', 'DGE', 1, 'INTERNET', 'Trans_DGE_CHEF DGE'),
	(17, '172.16.0.143', NULL, 'PC PORTABLE', 'DGE CONTRÔLE', 'NAVIGATION', b'0', 'DGE', 1, 'INTERNET', 'Trans_DGE_CONTROLE'),
	(18, '172.16.0.182', NULL, 'PC PORTABLE', 'LT-COL OBILAT', 'NAVIGATION', b'0', 'DGE', 1, 'INTERNET', 'Trans-Lt-Col OBILAT'),
	(19, '172.16.0.179', NULL, 'PC PORTABLE', 'ADJOINT DI', 'NAVIGATION', b'0', 'DI', 1, 'INTERNET', 'Trans_Adjt_DI-ACHIBAN'),
	(20, '172.16.0.162', NULL, 'PC PORTABLE', 'CHEF DI', 'NAVIGATION', b'0', 'DI', 1, 'INTERNET', 'Trans_CHEF-DI'),
	(21, '172.16.0.247', NULL, 'PC PORTABLE', 'COLMAJOR MHAOUICHI', 'NAVIGATION', b'0', 'DI', 1, 'INTERNET', 'TRANS-DI-COLMAJOR MHAOUICHI'),
	(22, '172.16.0.10', NULL, 'PC BUREAU', 'WAF', 'SUPERVISION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_TEST_WAF'),
	(23, '172.16.0.11', NULL, 'PC BUREAU', 'WAF ', 'SUPERVISION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_TEST_WAF'),
	(24, '172.16.0.88', NULL, 'PC BUREAU', 'SERVEUR TEST', 'SUPERVISION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Dinfo-SRVTEST'),
	(25, '172.16.0.202', NULL, 'PC BUREAU', 'MULTIMEDIA S/C MARRI', 'SUPERVISION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_MULTIMEDIA-MARRI'),
	(26, '172.16.0.250', NULL, 'PC BUREAU', 'SITE RECRUTEMENT', 'SUPERVISION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_SITE RECRUTEMENT'),
	(27, '172.16.0.101', NULL, 'SERVEUR', 'ANTIVIRUS GDATA-RIG-CRISE', 'ANTIVIRUS GDATE', b'0', 'DINFO', 1, 'INTERNET', 'Trans_SRV-ANTIVURUS-GDATA-RIG-CRISE'),
	(28, '172.16.0.159', NULL, 'PC PORTABLE', 'TEST', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'TEST'),
	(29, '172.16.0.47', NULL, 'PC PORTABLE', 'ADJOINT DINFO', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_ADJDINFO'),
	(30, '172.16.0.17', NULL, 'PC BUREAU', 'ADMIN INTERNET2', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'TRANS_ADMIN-INTERNET2'),
	(31, '172.16.0.193', NULL, 'PC PORTABLE', 'CHEF_DINFO', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_CHEF_DINFO'),
	(32, '172.16.0.44', NULL, 'PC PORTABLE', 'CDT BENABDESSLAM', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_CDT_Benabdessalam'),
	(33, '172.16.0.151', NULL, 'PC PORTABLE', 'CDT BENABDESSLAM', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_CDT-SAIDI'),
	(34, '172.16.0.96', NULL, 'PC BUREAU', 'ADMIN INTERNET DINFO96', 'ADMINISTRATION INTERNET', b'0', 'DINFO', 1, 'INTERNET', 'TRANS_DINFO_admin96'),
	(35, '172.16.0.45', NULL, 'PC BUREAU', 'S/C AITKHMIM DINFO', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_DINFO_AITKHMIM'),
	(36, '172.16.0.48', NULL, 'PC BUREAU', 'CAL AKABLI PROVISOIRE', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Dinfo_AKEBLI (PROVISIOIRE)'),
	(37, '172.16.0.158', NULL, 'PC PORTABLE', 'LT GUERYCHE DINFO', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Dinfo_guryech'),
	(38, '172.16.0.46', NULL, 'PC BUREAU', 'SGT OUARDA', 'MULTIMEDIA', b'0', 'DINFO', 1, 'INTERNET', 'Trans_DINFO_OUARDA'),
	(39, '172.16.0.49', NULL, 'PC BUREAU', 'LT-COL ZAKRI', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_DINFO_ZAKRI'),
	(40, '172.16.0.95', NULL, 'PC BUREAU', 'ADMINISTRATEUR INTERNET 95', 'ADMINISTRATION INTERNET', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Dinfoadmin_2'),
	(41, '172.16.0.40', NULL, 'PC BUREAU', 'ADMINISTRATION DINFO SIP', 'ADMINISTRATION SIP', b'0', 'DINFO', 1, 'INTERNET', 'Trans_DINFO-Admin_SIP'),
	(42, '172.16.0.98', NULL, 'PC BUREAU', 'ADMINISTRATION DINFO 3CX', 'ADMINISTRATION 3CX', b'0', 'DINFO', 1, 'INTERNET', 'Trans_DINFO-admin-3CX'),
	(43, '172.16.0.41', NULL, 'PC BUREAU', 'SGT TAOUF ', 'DEVELOPPEMENT', b'0', 'DINFO', 1, 'INTERNET', 'Trans_DINFO-taouaf-41'),
	(44, '172.16.0.25', NULL, '', '', '', b'0', 'DINFO', 1, 'INTERNET', 'Trans_LT_DINFO'),
	(45, '172.16.0.26', NULL, '', '', '', b'0', 'DINFO', 1, 'INTERNET', 'Trans_LT_DINFO2'),
	(46, '172.16.0.24', NULL, 'SERVEUR', '', 'SERVEUR ', b'0', 'DINFO', 1, 'INTERNET', 'TRANS_Reserve-SERVA'),
	(47, '172.16.0.100', NULL, 'SERVEUR', 'SERVEUR ANTIVIRUS', 'ANTIVIRUS TRELLIX', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Serveur Antivirus Internet'),
	(48, '172.16.0.97', NULL, 'SERVEUR', 'SERVEUR Messagerie Principale', 'MESSAGERIE DOMINO LOTUS 12 PRINCIPAL', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Serveur_Messagerie_Principale'),
	(49, '172.16.0.99', NULL, 'SERVEUR', 'SERVEUR Messagerie Secondaire', 'MESSAGERIE DOMINO LOTUS 12 SECONDAIRE', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Serveur_Messagerie_Secondaire'),
	(50, '172.16.0.139', NULL, 'SERVEUR', 'SERVEUR MESSAGERIE TEST', 'MESSAGERIE DOMINO TEST', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Serveur_Messagerie_test'),
	(51, '172.16.0.197', NULL, 'SERVEUR', '', 'SERVEUR PROXMOX', b'0', 'DINFO', 1, 'INTERNET', 'Trans_Serveur_ProxMox'),
	(52, '172.16.0.27', NULL, 'PC BUREAU', 'SGT FAHMI', 'DEVELOPPEMENT', b'0', 'DINFO', 1, 'INTERNET', 'Trans_SgtBoulila-DINFO'),
	(53, '172.16.0.28', NULL, 'PC BUREAU', 'SGT HAJJAJI', 'EDUCATION DEVELOPPEMENT ', b'0', 'DINFO', 1, 'INTERNET', 'Trans_SgtFahmi-DINFO'),
	(54, '172.16.0.39', NULL, 'SERVEUR', 'SRVMATRIX CONFERENCE', 'CONFERENCE', b'0', 'DINFO', 1, 'INTERNET', 'Trans_SRVMATRIX-CONFERENCE'),
	(55, '172.16.0.169', NULL, 'SPC BUREAU', 'CAL AKABLI', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'TRANS-AKABLI'),
	(56, '172.16.0.15', NULL, 'PC BUREAU', 'CHEF DINFO', 'NAVIGATION ET ADMINISTRATION', b'0', 'DINFO', 1, 'INTERNET', 'TRANS-CHEF_DINFO_ITRANS'),
	(57, '172.16.0.196', NULL, 'PC BUREAU', 'SGT MERYAM DINFO', 'NAVIGATION ET DEVELOPPEMENT', b'0', 'DINFO', 1, 'INTERNET', 'TRANS-DINFO-MERYAM'),
	(58, '172.16.0.34', NULL, 'PC BUREAU', 'CNE OKKAN', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans-OSM_LT OKKAN'),
	(59, '172.16.0.43', NULL, 'SERVEUR', 'TEST LOTUS', 'MESSAGERIE ', b'0', 'DINFO', 1, 'INTERNET', 'Trans-TEST-LOTUS'),
	(60, '172.16.0.212', NULL, 'PC BUREAU', 'S/C MARRI', 'MULTIMEDIA', b'0', 'DINFO', 1, 'INTERNET', 'Trans_VMW-MULTIMEDIA'),
	(61, '172.16.0.215', NULL, 'PC BUREAU', 'VM WINDOWS 10', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_VMW-WINDOWS 10'),
	(62, '172.16.0.214', NULL, 'PC BUREAU', 'VM WINDOWS 10', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_VMW-WINDOWS 10 NAVIG'),
	(63, '172.16.0.21', NULL, 'MODEM CISCO', 'RESEAU', 'WIFI CISCO LINK SYSTEM', b'0', 'DINFO', 1, 'INTERNET', 'Trans_wifi Cisco Link Sys'),
	(64, '172.16.0.131', NULL, 'DRH', 'COL OUCHAKER', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'ADJT_DRH   Col OUCHKER'),
	(65, '172.16.0.74', NULL, 'FIREWALL', 'RESEAU IAM', 'RESEAU IAM', b'0', 'DINFO', 1, 'INTERNET', 'Test_LS_IAM'),
	(66, '172.16.0.141', NULL, 'PC PORTABLE', 'CHEF DLET', 'NAVIGATION', b'0', 'DINFO', 1, 'INTERNET', 'Trans_ChefDLET'),
	(67, '172.16.0.164', NULL, 'MEDEM TP-LINK', 'CHEF DLET', 'WIFI', b'0', 'DLET', 1, 'INTERNET', 'Trans_ChefDLET'),
	(68, '172.16.0.172', NULL, 'PC PORTABLE', 'ADJOINT DLET', 'NAVIGATION', b'0', 'DLET', 1, 'INTERNET', 'Trans_DLET_AdjChefDLET'),
	(69, '172.16.0.173', NULL, 'POC BUREAU', 'DLET GTPH', 'NAVIGATION ET MESSAGERIE ', b'0', 'DLET', 1, 'INTERNET', 'Trans_DLET-GTPH'),
	(70, '172.16.0.171', NULL, 'PC PORTBALE', 'LT AIT OUSAID ', 'NAVIGATION', b'0', 'DLET', 1, 'INTERNET', 'Trans_DLET-LT-AIT OUSAID'),
	(71, '172.16.0.145', NULL, 'PC PORTABLE', 'CHEF DMCPO', 'NAVIGATION', b'0', 'DMCPO', 1, 'INTERNET', 'Trans_chef-DMCPO'),
	(72, '172.16.0.152', NULL, 'PC PORTABLE', 'ADJOINT DMCPO', 'NAVIGATION', b'0', 'DMCPO', 1, 'INTERNET', 'Trans_Adjt_DMCPO'),
	(73, '172.16.0.147', NULL, 'PC PORTABLE', 'ADJOINT DP', 'NAVIGATION', b'0', 'DP', 1, 'INTERNET', 'Trans_DP_AdjChefDP'),
	(74, '172.16.0.142', NULL, 'PC BUREAU', 'CELLULE DP', 'NAVIGATION ET MESSAGERIE ', b'0', 'DP', 1, 'INTERNET', 'Trans_DP_DRMS'),
	(75, '172.16.0.225', NULL, 'PC PORTABLE', 'COL CHAAR', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC_COL_CHAAR'),
	(76, '172.16.0.176', NULL, 'PC PORTABLE', 'CHEF DPSIC', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_CHEF_DPSIC'),
	(77, '172.16.0.241', NULL, 'PC PORTABLE', 'CHEF DP', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_ChefDP'),
	(78, '172.16.0.191', NULL, 'PC PORTABLE', 'ADJOINT DPSIC', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC_ADJOINT'),
	(79, '172.16.0.190', NULL, 'PC PORTABLE', 'CDT ALLAMI', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC_Allami'),
	(80, '172.16.0.194', NULL, 'PC PORTABLE', 'CDT MOURAHIB', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC_mourahib'),
	(81, '172.16.0.192', NULL, 'SERVEUR', 'CELLULE DPSIC', 'VIRTUALISATION PROXMOX', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC_PROXMOX'),
	(82, '172.16.0.198', NULL, 'SERVEUR', 'CELLULE DPSIC', 'SYSTÈME SIEM', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC-SIEM-ETH0'),
	(83, '172.16.0.249', NULL, 'PC PORTABLE', 'CNE AABOU', 'NAVIGATION', b'0', 'DPSIC', 1, 'INTERNET', 'TRANS-DPSIC-CNE ABBOOU'),
	(84, '172.16.0.237', NULL, 'PC PORTABLE', 'CHEF DRH', 'NAVIGATION', b'0', 'DRH', 1, 'INTERNET', 'Trans_CHEF_DRH'),
	(85, '172.16.0.174', NULL, 'PC PORTABLE', 'ADJOINT DRH', 'NAVIGATION', b'0', 'DRH', 1, 'INTERNET', 'Trans_ADJ-DRH'),
	(86, '172.16.0.185', NULL, 'PC PORTABLE', 'COL BOUSSATA', 'NAVIGATION', b'0', 'DRH', 1, 'INTERNET', 'Trans_COL_BOUSSTA'),
	(87, '172.16.0.42', NULL, 'PC PORTABLE', 'COL-MAJOR REMIDI', 'NAVIGATION ET IPTV', b'0', 'DRH', 1, 'INTERNET', 'Trans_CM_ REMIDI'),
	(88, '172.16.0.170', NULL, 'PC PORTABLE', 'CDT HARRAK', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'TRANS-DRII-HARRAK'),
	(89, '172.16.0.177', NULL, 'PC PORTABLE', 'CHEF DRII', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_ChefDRII'),
	(90, '172.16.0.165', NULL, 'PC PORTABLE', 'ADJOINT DRII', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_AdjChefDRII'),
	(91, '172.16.0.33', NULL, 'PC PORTABLE', 'CDT BERGANOU', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_berganou'),
	(92, '172.16.0.153', NULL, 'PC PORTABLE', 'CDT AOULTIT', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_CDT_AOULTIT'),
	(93, '172.16.0.156', NULL, 'PC PORTABLE', 'CDT BERRAJI', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'TRANS_DRII_CDT_BERRAJI'),
	(94, '172.16.0.166', NULL, 'PC PORTABLE', 'LT-COL YOUNESS', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_CDT_YOUNESS'),
	(95, '172.16.0.146', NULL, 'PC PORTABLE', 'CELLULE DRII', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'TRANS_DRII_DRII FAR'),
	(96, '172.16.0.163', NULL, 'PC PORTABLE', 'CDT EZZAYITTI', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_Eziyati'),
	(97, '172.16.0.175', NULL, 'PC PORTABLE', 'DCELLULE FILE DRII', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_fil'),
	(98, '172.16.0.51', NULL, 'PC PORTABLE', 'LT-COL MELYANI', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_melyani'),
	(99, '172.16.0.167', NULL, 'PC PORTABLE', 'CELLULE DRII', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_SO'),
	(100, '172.16.0.248', NULL, 'PC PORTABLE', 'SUPERVISION DRII', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_supervision'),
	(101, '172.16.0.168', NULL, 'MODEM TP-LINK', 'CELLULE DRII', 'WIFI ET NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans_DRII_TV'),
	(102, '172.16.0.219', NULL, 'PC PORTBALE', 'CELLULE DRII', 'FORMATION PROVISOIRE', b'0', 'DRII', 1, 'INTERNET', 'FORMATION DRII'),
	(103, '172.16.0.148', NULL, 'PC PORTABLE', 'CDT BOUHIA', 'NAVIGATION', b'0', 'DRII', 1, 'INTERNET', 'Trans-DRII-CNE BOUHIA'),
	(104, '172.16.0.102', NULL, 'PC PORTABLE', 'ADJOINT DT', 'NAVIGATION', b'0', 'DT', 1, 'INTERNET', 'Trans_DT_ADJCHEFDT'),
	(105, '172.16.0.60', NULL, 'PC BUREAU', 'CDT MAHOUAR', 'NAVIGATION', b'0', 'DT', 1, 'INTERNET', 'TRANS-DT-CMD-MHAOUAR'),
	(106, '172.16.0.181', NULL, 'PC PORTABLE', 'ADJOINT DVE', 'NAVIGATION', b'0', 'DVE', 1, 'INTERNET', 'Trans_DVE_AdjtChefDVE'),
	(107, '172.16.0.38', NULL, 'PC PORTABLE', 'CNE BENHASSOU', 'NAVIGATION', b'0', 'DVE', 1, 'INTERNET', 'Trans_DVE_BENHASSOU'),
	(108, '172.16.0.183', NULL, 'PC PORTABLE', 'CDT HAMRIFOU', 'NAVIGATION', b'0', 'DVE', 1, 'INTERNET', 'Trans_DVE_HAMRIFOU'),
	(109, '172.16.0.180', NULL, 'PC BUREAU', 'CELLULE SIG DVE', 'ADMINISTRATION SIG', b'0', 'DVE', 1, 'INTERNET', 'Trans_DVE_SIG'),
	(110, '172.16.0.253', NULL, 'PC BUREAU', 'ATELIER DVE', 'NVIGATION', b'0', 'DVE', 1, 'INTERNET', 'Trans_DVE-ATELLIER'),
	(111, '172.16.0.144', NULL, 'PC BUREAU', 'CELLULE DVE', 'FORMATION IRIDIUM DVE', b'0', 'DVE', 1, 'INTERNET', 'Trans-DVE-FORMAT.IRIDIUM'),
	(112, '172.16.0.4', NULL, 'FORTIGAT 100 D', 'RESEAU FIREWALL', 'ADMINISTRATION FORTIGATE PRIMAIRE', b'0', 'FORTIGATE', 1, 'INTERNET', 'FORTIGATE - PRIMAIRE'),
	(113, '172.16.0.5', NULL, 'FORTIGAT 100 D', 'RESEAU FIREWALL', 'ADMINISTRATION FORTIGATE SECONDAIRE', b'0', 'FORTIGATE', 1, 'INTERNET', 'FORTIGATE - SECONDAIRE'),
	(114, '172.16.0.122', NULL, 'TV', 'INSPECTION ARTILLERIE', 'IPTV ET WATCHING', b'0', 'INSP.ART', 1, 'INTERNET', 'Trans_insp art-iptv'),
	(115, '172.16.32.147/32', NULL, 'PC BPORTABLE', 'INSPECTEUR DES TRANSMISSIONS', 'NAVIGATION ', b'0', 'INSPECTEUR', 1, 'INTERNET', 'Trans_Inspecteur_Trans'),
	(116, '172.16.0.211', NULL, 'TV VPLUS', 'INSPECTEUR DES TRANSMISSIONS', 'IPTV ET WATCHING', b'0', 'INSPECTEUR', 1, 'INTERNET', 'Tans_Inspecteur_Trans_TV_VPLUS'),
	(117, '172.16.0.230', NULL, 'MODEM TP-LINK', 'A/C HAMMI', 'WIFI ', b'0', 'MAGASIN', 1, 'INTERNET', 'Trans_MAGASIN-HAMI'),
	(118, '172.16.0.55', NULL, 'PC PORTABLE OU WIFI', 'ODR PERMANANCE', 'WIFI ET NAVIGATION', b'0', 'PERMANENCE', 1, 'INTERNET', 'Trans_permanence S/Off'),
	(119, '172.16.0.160', NULL, 'PC PORTABLE OU WIFI', 'OFFICIER PERMANANCE', 'WIFI ET NAVIGATION', b'0', 'PERMANENCE', 1, 'INTERNET', 'Trans_Permanence_REC_OFF'),
	(120, '172.16.0.195', NULL, 'PC PORTABLE', 'CELLEULE ORSPIC ', 'NAVIGATION ', b'0', 'ORPSIC', 1, 'INTERNET', 'Trans_RPSIC'),
	(121, '172.16.0.50', NULL, 'PC PORTABLE', 'SECRETARIAT COSSIC', 'NAVIGATION ', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_SCRT_COSSIC'),
	(122, '172.16.60.170 ', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(123, '172.16.60.171', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(124, '172.16.60.172', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(125, '172.16.60.173', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(126, '172.16.60.174', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(127, '172.16.60.175', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(128, '172.16.60.176', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(129, '172.16.60.177', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(130, '172.16.60.178', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(131, '172.16.60.179', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(132, '172.16.60.180', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(133, '172.16.60.181', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(134, '172.16.60.182', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(135, '172.16.60.183', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(136, '172.16.60.184', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(137, '172.16.60.185', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(138, '172.16.60.186', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(139, '172.16.60.187', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(140, '172.16.60.188', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(141, '172.16.60.189', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(142, '172.16.60.190', NULL, 'PC BUREAU', 'CELLULE COSSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'COSSIC', 1, 'INTERNET', 'Trans_COSSIC'),
	(143, '172.16.60.145', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(144, '172.16.60.146', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(145, '172.16.60.147', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(146, '172.16.60.148', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(147, '172.16.60.149', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(148, '172.16.60.150', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(149, '172.16.60.151', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(150, '172.16.60.152', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(151, '172.16.60.153', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(152, '172.16.60.154', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(153, '172.16.60.155', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(154, '172.16.60.156', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(155, '172.16.60.157', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(156, '172.16.60.158', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(157, '172.16.60.159', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC'),
	(158, '172.16.60.160', NULL, 'PC BUREAU', 'CELLULE DPSIC', 'NAVIGATION ET ADMINISTRATION', b'0', 'DPSIC', 1, 'INTERNET', 'Trans_DPSIC');

-- Listage de la structure de la table matdinfo. appuser
CREATE TABLE IF NOT EXISTS `appuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.appuser : ~0 rows (environ)
REPLACE INTO `appuser` (`id`, `enabled`, `password`, `username`) VALUES
	(1, b'1', '$2y$10$S2HMdkdwuI13UZyYU6JCpOaMFtNPuVUVtN3ALh8p0lLi0PAG5LmXa', 'dinfo');

-- Listage de la structure de la table matdinfo. appuser_roles
CREATE TABLE IF NOT EXISTS `appuser_roles` (
  `appuser_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`appuser_id`,`role_id`),
  KEY `FKhtjb40qbyjpoc8vrrsp5x1j07` (`role_id`),
  CONSTRAINT `FKhtjb40qbyjpoc8vrrsp5x1j07` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKqr2nj33n7sh6sd1amisotvynn` FOREIGN KEY (`appuser_id`) REFERENCES `appuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.appuser_roles : ~1 rows (environ)
REPLACE INTO `appuser_roles` (`appuser_id`, `role_id`) VALUES
	(1, 1);

-- Listage de la structure de la table matdinfo. division
CREATE TABLE IF NOT EXISTS `division` (
  `designation` varchar(255) NOT NULL,
  PRIMARY KEY (`designation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.division : ~23 rows (environ)
REPLACE INTO `division` (`designation`) VALUES
	('ATTELIER DINFO'),
	('CABINET'),
	('COSSIC'),
	('CTI'),
	('DEE'),
	('DGCD'),
	('DGE'),
	('DI'),
	('DINFO'),
	('DLET'),
	('DMCPO'),
	('DP'),
	('DPSIC'),
	('DRH'),
	('DRII'),
	('DT'),
	('DVE'),
	('FORTIGATE'),
	('INSP.ART'),
	('INSPECTEUR'),
	('MAGASIN'),
	('ORPSIC'),
	('PERMANENCE');

-- Listage de la structure de la table matdinfo. licence
CREATE TABLE IF NOT EXISTS `licence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateachat` datetime(6) DEFAULT NULL,
  `dateexpiration` datetime(6) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prixachat` double NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `situation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rtn5d6edwhw2l859ya9ah9w5q` (`reference`),
  KEY `FKassdc46d2vgudfxyhs113tsaf` (`situation_id`),
  CONSTRAINT `FKassdc46d2vgudfxyhs113tsaf` FOREIGN KEY (`situation_id`) REFERENCES `situation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.licence : ~6 rows (environ)
REPLACE INTO `licence` (`id`, `dateachat`, `dateexpiration`, `nom`, `prixachat`, `reference`, `situation_id`) VALUES
	(12, '2024-11-08 00:00:00.000000', '2025-11-08 00:00:00.000000', 'BIG IP', 15400, 'REFBG1574', 2),
	(13, '2024-11-08 00:00:00.000000', '2024-08-23 00:00:00.000000', 'FORTINET', 18741, 'REF7987', 1),
	(14, '2024-11-03 00:00:00.000000', '2024-11-20 00:00:00.000000', 'TEST', 1650, 'REF5645485', 3),
	(15, '2024-11-11 00:00:00.000000', '2024-11-12 00:00:00.000000', 'OOOOOO', 1452, 'REF79876', 3),
	(16, '2024-11-06 00:00:00.000000', '2025-01-17 00:00:00.000000', 'MAROUAN', 1600, 'AAEE', 2),
	(17, '2024-11-12 00:00:00.000000', '2031-06-12 00:00:00.000000', 'TESTF', 16000, 'REFAAA', 2),
	(18, '2024-11-22 00:00:00.000000', '2024-11-28 00:00:00.000000', 'AAAAAAAAAAAA', 1200, 'ZZAAA', 3);

-- Listage de la structure de la table matdinfo. logiciel
CREATE TABLE IF NOT EXISTS `logiciel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.logiciel : ~4 rows (environ)
REPLACE INTO `logiciel` (`id`, `nom`) VALUES
	(1, 'BASE DE DONNEES'),
	(2, 'SERVEUR MAIL'),
	(3, 'SERVEUR ANTIVIRUS'),
	(4, 'SERVEUR APPLICATION');

-- Listage de la structure de la table matdinfo. mouvement
CREATE TABLE IF NOT EXISTS `mouvement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datee` datetime(6) DEFAULT NULL,
  `observation` varchar(255) DEFAULT NULL,
  `position_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `stock_id` bigint(20) NOT NULL,
  `dateentree` datetime(6) DEFAULT NULL,
  `responsable_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvrquwwym5kayfttektxw1vr4` (`position_id`),
  KEY `FKaxd0ynm7atmf296vfrmpwjn3i` (`status_id`),
  KEY `FK53htvuwdp7hwfldbph68qd0lj` (`stock_id`),
  KEY `FKkeirbxcoauyyehe9dw9f57l65` (`responsable_id`),
  CONSTRAINT `FK53htvuwdp7hwfldbph68qd0lj` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
  CONSTRAINT `FKaxd0ynm7atmf296vfrmpwjn3i` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKhvrquwwym5kayfttektxw1vr4` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `FKkeirbxcoauyyehe9dw9f57l65` FOREIGN KEY (`responsable_id`) REFERENCES `responsable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.mouvement : ~36 rows (environ)
REPLACE INTO `mouvement` (`id`, `datee`, `observation`, `position_id`, `status_id`, `stock_id`, `dateentree`, `responsable_id`) VALUES
	(3, '2024-09-13 09:27:51.000000', 'test', 3, 3, 31, NULL, 1),
	(4, '2024-09-13 09:47:37.000000', '', 1, 3, 29, NULL, 1),
	(6, '2024-09-13 09:48:02.000000', '', 4, 3, 41, NULL, 1),
	(8, '2024-09-13 09:47:37.000000', 'tteee', 1, 3, 29, '2024-09-13 13:56:28.000000', 1),
	(9, '2024-09-13 09:27:51.000000', 'aaaaaa', 3, 3, 31, '2024-09-13 14:04:57.000000', 1),
	(13, '2024-09-14 08:18:52.000000', 'visionconference', 2, 3, 41, NULL, 1),
	(14, '2024-09-14 08:18:52.000000', 'retour', 2, 3, 41, '2024-09-14 08:19:03.000000', 1),
	(15, '2024-09-14 09:04:53.000000', 'VISIONCONFERENCE 12', 5, 3, 30, NULL, 1),
	(16, '2024-09-14 09:04:53.000000', 'RETOUR', 5, 3, 30, '2024-09-14 09:05:24.000000', 1),
	(17, '2024-09-14 09:55:57.000000', '', 4, 3, 31, NULL, 1),
	(18, '2024-09-14 09:55:57.000000', 'retour', 4, 1, 31, '2024-09-14 09:56:09.000000', 1),
	(19, '2024-09-14 10:15:15.000000', 'aaaaa', 2, 3, 41, NULL, 1),
	(20, '2024-09-14 10:15:15.000000', 'aaaaaaaaaaaaaaaaaaaaaaa', 2, 1, 41, '2024-09-15 10:15:32.000000', 1),
	(21, '2024-09-14 10:28:52.000000', '', 3, 3, 35, NULL, 1),
	(22, '2024-09-17 09:09:21.000000', 'aaa', 4, 3, 36, NULL, 1),
	(23, '2024-09-17 09:23:17.000000', 'ezrzerzerzr', 3, 3, 29, NULL, 1),
	(24, '2024-09-17 09:23:17.000000', 'rfsdfsdf', 3, 1, 29, '2024-09-17 09:23:35.000000', 1),
	(25, '2024-09-17 09:44:31.000000', 'hhhhhh', 2, 3, 29, NULL, 1),
	(26, '2024-09-17 09:44:31.000000', '', 2, 1, 29, '2024-09-17 09:47:18.000000', 1),
	(27, '2024-09-14 10:28:52.000000', 'ttt', 3, 1, 35, '2024-09-20 08:45:36.000000', 1),
	(28, '2024-09-17 09:09:21.000000', '', 4, 1, 36, '2024-09-20 09:34:45.000000', 1),
	(29, '2024-09-20 09:38:45.000000', '', 4, 3, 41, NULL, 1),
	(30, '2024-09-21 08:30:48.000000', '', 3, 3, 43, NULL, 1),
	(31, '2024-10-31 10:31:11.000000', '', 1, 3, 56, NULL, 1),
	(32, '2024-10-31 10:31:11.000000', '', 1, 1, 56, '2024-10-31 10:34:27.000000', 1),
	(33, '2024-10-31 11:19:53.000000', 'aa', 3, 4, 42, NULL, 1),
	(34, '2024-10-31 11:19:53.000000', 'aaaaaaa', 3, 1, 42, '2024-10-31 11:20:18.000000', 1),
	(35, '2024-09-21 08:30:48.000000', '', 3, 1, 43, '2024-10-31 11:24:53.000000', 1),
	(36, '2024-11-01 08:40:32.000000', '', 3, 3, 44, '2024-11-01 11:24:53.000000', 1),
	(37, '2024-11-01 11:28:03.000000', '', 3, 3, 65, NULL, 1),
	(38, '2024-11-01 11:28:03.000000', '', 3, 1, 65, '2024-11-01 11:29:34.000000', 1),
	(39, '2024-11-01 00:00:00.000000', '88', 2, 3, 36, NULL, 1),
	(40, '2024-11-01 00:00:00.000000', '88', 2, 1, 36, '2024-11-02 09:08:03.000000', 1),
	(41, '2024-11-02 00:00:00.000000', 'AQWZSX', 1, 3, 49, NULL, 1),
	(42, '2024-11-02 00:00:00.000000', '', 1, 1, 49, '2024-11-02 09:09:51.000000', 1),
	(43, '2024-11-01 08:40:32.000000', '1998', 3, 1, 44, '2024-11-05 00:00:00.000000', 1);

-- Listage de la structure de la table matdinfo. organe
CREATE TABLE IF NOT EXISTS `organe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.organe : ~2 rows (environ)
REPLACE INTO `organe` (`id`, `nom`) VALUES
	(1, 'INSP TRANS'),
	(2, '1°BUREAU');

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
	(5, 'ANNEXE 4°BUREAU');

-- Listage de la structure de la table matdinfo. resaux
CREATE TABLE IF NOT EXISTS `resaux` (
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.resaux : ~2 rows (environ)
REPLACE INTO `resaux` (`nom`) VALUES
	('INTERNET'),
	('INTRANET');

-- Listage de la structure de la table matdinfo. responsable
CREATE TABLE IF NOT EXISTS `responsable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.responsable : ~2 rows (environ)
REPLACE INTO `responsable` (`id`, `grade`, `nom`, `prenom`) VALUES
	(1, 'SERGENT', 'TAOUAF', 'AHMED');

-- Listage de la structure de la table matdinfo. role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.role : ~2 rows (environ)
REPLACE INTO `role` (`id`, `name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');

-- Listage de la structure de la table matdinfo. serveur
CREATE TABLE IF NOT EXISTS `serveur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresseip` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `hyperviseur` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nserie` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `resaux` varchar(255) DEFAULT NULL,
  `stockage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.serveur : ~8 rows (environ)
REPLACE INTO `serveur` (`id`, `adresseip`, `cpu`, `hyperviseur`, `nom`, `nserie`, `ram`, `resaux`, `stockage`) VALUES
	(5, '172.16.100.104', '24 CPUs x Intel(R) Xeon(R) Gold 5318Y CPU @ 2.10GHz', 'Vmware ESXI', 'Dell Inc PowerEdge R750xs', '****', '63,46 Go', 'INTERNET', '2,06 To'),
	(6, '172.16.100.100', '8 CPUs x Intel(R) Xeon(R) Bronze 3106 CPU @ 1.70GHz', 'VMWARE ESXI', 'HPE ProLiant DL380 Gen10 ', '****', '63,65 Go', 'INTERNET', '1,69 To'),
	(7, '172.16.100.32                    ', '4 CPUs x Intel(R) Xeon(R) CPU E5507 @ 2.27GHz', 'VMWARE ESXI', 'HPE ProLiant DL380 Gen10 ', '****', '15,96 GO', 'INTERNET', '827,75 GO'),
	(8, '172.16.100.102                ', '18 CPUs x Intel(R) Xeon(R) Gold 6240 CPU @ 2.60GHz', 'VMWARE ESXI', 'HPE ProLiant DL380 Gen10', '****', '63,66 GO', 'INTERNET', '1,51 TO'),
	(9, '172.16.100.19        ', '4 CPUs x Intel(R) Xeon(R) CPU E5-2407 0 @ 2.20GHz', 'VMWARE ESXI', 'DELL INC. PowerEdge R320', '****', '47,96 GO', 'INTERNET', '923 GO'),
	(10, '193.0.0.20', '24 CPUs x Intel(R) Xeon(R) Silver 4116 CPU @ 2.10GHz', 'Vmware ESXI', 'HPE ProLiant DL380 Gen10', '****', '127,65 Go', 'INTRANET', '22,14 To'),
	(11, '193.0.0.9', '10 CPUs x Intel(R) Xeon(R) Silver 4210 CPU @ 2.20GHz', 'Vmware ESXI', 'HPE ProLiant DL380 Gen10', '****', '31,66 Go', 'INTRANET', '1,08 To'),
	(12, '193.0.0.22', '24 CPUs x Intel(R) Xeon(R) Silver 4116 CPU @ 2.10GHz', 'Vmware ESXI', 'HPE  ProLiant DL380 Gen10', '****', '127,65 Go', 'INTRANET', '22,14 To'),
	(13, '192.154.52.1', 'AEARA', 'AAA', 'a', 'N987456', '56', 'INTERNET', '156');

-- Listage de la structure de la table matdinfo. situation
CREATE TABLE IF NOT EXISTS `situation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.situation : ~2 rows (environ)
REPLACE INTO `situation` (`id`, `nom`) VALUES
	(1, 'EXPIREE'),
	(2, 'EN COURS'),
	(3, 'PRESQUE');

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
	(4, 'EN PANNE');

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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.stock : ~36 rows (environ)
REPLACE INTO `stock` (`id`, `datee`, `designation`, `nserie`, `observation`, `status_id`, `type_id`) VALUES
	(29, '2024-09-13 08:57:05.000000', 'PC PORTABLEEe', 'SN56456454KF', 'I5 11th, 8GB RAM', 1, 7),
	(30, '2024-09-13 08:57:36.000000', 'CAMERA LOGITECH', 'SN89784564654A', 'CAMERA LOGITECH GROUP', 1, 1),
	(31, '2024-09-13 08:57:57.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(32, '2024-09-13 08:57:57.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(34, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(35, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(36, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 3),
	(37, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(38, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(39, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(40, '2024-09-13 08:57:58.000000', 'SOURIS', 'Sans', 'SOURIS', 1, 7),
	(41, '2024-09-13 08:58:37.000000', 'SWITCH CISCO', 'SN89798789AQ', 'SWITCH CISCO 24 PORTS', 2, 2),
	(42, '2024-09-14 10:51:02.000000', 'LOGITECH 1080P PETIT', 'SN8978945641TF', '', 1, 1),
	(43, '2024-09-21 08:30:24.000000', 'Modem', 'SN87464564A', '', 1, 5),
	(44, '2024-09-23 08:49:52.000000', 'LOGITECH GROUP', 'Sans', '', 1, 1),
	(45, '2024-09-23 08:49:53.000000', 'LOGITECH GROUP', 'Sans', '', 1, 1),
	(46, '2024-09-25 08:13:56.000000', 'MODEM TPLINK', 'SN_null', '', 1, 5),
	(47, '2024-09-25 08:13:56.000000', 'MODEM TPLINK', 'SN_null', '', 1, 5),
	(48, '2024-09-25 08:13:57.000000', 'MODEM TPLINK', 'SN_null', '', 1, 5),
	(49, '2024-09-25 08:13:57.000000', 'MODEM TPLINK', 'SN_null', '', 1, 5),
	(50, '2024-09-25 08:18:20.000000', 'CISCO', 'SN50', 'AA', 1, 5),
	(51, '2024-09-25 08:18:20.000000', 'CISCO', 'SN51', 'AA', 1, 5),
	(52, '2024-09-25 08:18:21.000000', 'CISCO', 'SN52', 'AA', 1, 5),
	(53, '2024-09-25 08:18:21.000000', 'CISCO', 'SN53', 'AA', 1, 5),
	(54, '2024-09-25 08:18:21.000000', 'CISCO', 'SN54', 'AA', 1, 5),
	(55, '2024-09-25 08:18:21.000000', 'CISCO', 'SN55', 'AA', 1, 5),
	(56, '2024-09-25 08:20:13.000000', 'LENOVO 11TH', 'SN_56', '', 1, 6),
	(57, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_57', '', 1, 6),
	(58, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_58', '', 1, 6),
	(59, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_59', '', 1, 6),
	(60, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_60', '', 1, 6),
	(61, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_61', '', 1, 6),
	(62, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_62', '', 1, 6),
	(63, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_63', '', 1, 6),
	(64, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_64', '', 1, 6),
	(65, '2024-09-25 08:20:14.000000', 'LENOVO 11TH', 'SN_65', '', 1, 6);

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

-- Listage de la structure de la table matdinfo. virtual_machine
CREATE TABLE IF NOT EXISTS `virtual_machine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresseip` varchar(255) DEFAULT NULL,
  `categorie` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `monitoring_path` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `stockage` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `logiciel_id` bigint(20) NOT NULL,
  `serveur_id` bigint(20) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpm44pg4nk8nyhtggmemo8l7g5` (`logiciel_id`),
  KEY `FKol0ulcljbh25sj73gcda81qh7` (`serveur_id`),
  CONSTRAINT `FKol0ulcljbh25sj73gcda81qh7` FOREIGN KEY (`serveur_id`) REFERENCES `serveur` (`id`),
  CONSTRAINT `FKpm44pg4nk8nyhtggmemo8l7g5` FOREIGN KEY (`logiciel_id`) REFERENCES `logiciel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table matdinfo.virtual_machine : ~46 rows (environ)
REPLACE INTO `virtual_machine` (`id`, `adresseip`, `categorie`, `cpu`, `designation`, `monitoring_path`, `os`, `ram`, `stockage`, `type`, `logiciel_id`, `serveur_id`, `status`) VALUES
	(4, '172.16.0.97', 'MESSAGERIE', '16 GO', 'SRVMAIL01-HCL', '', 'RED HAT', '24 GO', '1,02 To', 'V', 1, 5, b'1'),
	(5, '172.16.0.x', 'ANTIVIRUS', '8 GO', 'TEST SERVER TRELLIX INTERNET', '', 'WINDOWS SERV 12', '16 GO', '115 GO', 'V', 1, 5, b'1'),
	(6, '?172.16.0.46', 'PARTAGE ', '4 GO', 'NextCloud-server', '', 'Ubuntu?Linux ', '8 GO', '408,08 Go', 'V', 1, 5, b'1'),
	(7, '172.16.0.47', 'APPLICATION DEV', '4 GO', 'MEDO-OPS', '', 'Ubuntu?Linux ', '16 GO', '116,08 GO', 'V', 1, 5, b'1'),
	(8, '172.16.0.x', 'MESSAGERIE', '8 GO', 'SRVMAIL01', '', 'RED HAT', '24 GO', '700 Go', 'V', 1, 6, b'1'),
	(9, '172.16.0.139', 'MESSAGERIE', '8 GO', 'SRVMAIL01', '', 'RED HAT', '24 GO', '700 Go', 'V', 1, 6, b'1'),
	(10, '172.16.0.96', 'ADMINISTRATION INTERNET', '2 GO', 'Admin_Internet', '', 'WINDOWS 10', '8 GO', '92,43 Go', 'V', 1, 6, b'1'),
	(11, '172.16.0.x', 'ROUTAGE', '2 GO', 'SRV-DNS', '', 'WINDOWS SERVER 2012', '1 GO', '40 Go', 'V', 1, 6, b'1'),
	(12, '172.16.0.x', 'MESSAGERIE', '1 GO', 'domino', '', 'RED HAT', '4 GO', '400 GO', 'V', 1, 7, b'1'),
	(13, '172.16.0.100', 'ANTIVIRUS', '2 GO', 'SRVTRELLIX-INTERNET', '', 'WINDOWS SERV 12', '4 GO', '204,11 GO', 'V', 1, 7, b'1'),
	(14, '172.16.0.x', 'PARTAGE ', '4 GO', 'Nextcloud-server', '', 'Ubuntu Linux ', '8 GO', '100 Go', 'V', 1, 7, b'1'),
	(15, '192.168.0.99', 'MESSAGERIE', '4 GO', 'RelaisDomino', '', 'RED HAT', '8 GO', '58,08 Go', 'V', 1, 8, b'1'),
	(16, '192.168.0.97', 'ROUTAGE', '8 GO', 'DNS', '', 'RED HAT', '6 GO', '63,65 GO', 'V', 1, 8, b'1'),
	(17, '172.16.100.103             ', 'ADMINISTRATION ESXI', '4 GO', 'VMware vCenter Server', '', 'LINUX', '19 GO', '105,6 GO', 'V', 1, 8, b'1'),
	(18, '192.68.103.181', 'VISIOCONFERENCE', '4 GO', 'TRUE-CONF', '', 'WINDOWS SERV 12', '4 GO', '64,08 GO', 'V', 1, 8, b'1'),
	(19, '172.16.0.99', 'MESSAGERIE', '14 GO', 'SRVMAIL02-HCL', '', 'RED HAT', '12 GO', '1,01 TO', 'V', 1, 8, b'1'),
	(20, '192.168.0.97', 'MESSAGERIE', '4 GO', 'Messagerie_Internet_far.ma_P', '', 'RED HAT', '16 GO', '850 GO', 'V', 1, 9, b'1'),
	(24, '172.16.0.x', 'MESSAGERIE INTRANET', '4 GO', 'DominoRHEL-SRV', '', 'RED HAT', '8 GO', '20,17 Go', 'V', 1, 10, b'0'),
	(25, '193.0.0.201', 'MESSAGERIE INTRANET', '16 GO', 'MSG_INTRANET_SECONDAIRE', '', 'Red?Hat?Enterprise Linux?7 (64?bits)', '6 GO', '3,01 To', 'V', 1, 10, b'1'),
	(26, '193.0.0.246', 'MESSAGERIE INTRANET', '16 GO', 'MSG-INTRANET3', '', 'Red?Hat?Enterprise Linux?7 (64?bits)', '16 GO', '500 Go', 'V', 1, 10, b'1'),
	(27, '193.0.0.250', 'MESSAGERIE GE', '4 GO', 'GE.FAR', '', 'Red?Hat?Enterprise Linux?7 (64?bits)', '16 GO', '500 Go', 'V', 1, 10, b'1'),
	(28, '193.0.0.18', 'ADMINISTRATION GE\r\n', '10 GO\r\n', 'Administration-GE\r\n', NULL, 'Microsoft Windows 10 (64 bits) VBS non activé\r\n', '4 GO\r\n', '100 Go\r\n', 'V', 1, 10, b'1'),
	(29, '193.0.0.18', 'ADMINISTRATION RIGCRISE', '2 GO', 'ADMINISTRATION-RIGCRISE', NULL, 'Microsoft Windows 10 (64 bits) VBS non activé', '4 GO', '60 Go', 'V', 1, 10, b'1'),
	(30, '193.0.0.13', 'ADMINISTRATION INTRANET', '2 GO', 'Administration-intranet', NULL, 'Microsoft Windows 10 (64 bits) VBS non activé', '4 GO', '300 Go', 'V', 1, 10, b'1'),
	(31, '172.16.0.x', 'ARCHIVE-STOCKAGE', '4 GO', 'VEEAM_AIT_OLD', '', 'WINDOWS SERVER 2012', '4 GO', '2 To', 'V', 1, 10, b'0'),
	(32, '193.0.0.13', 'MESSAGERIE RIGCRISES', '6 GO', 'RIGCRISES-SECONDAIRE', '', 'REDHAT ', '16 GO', '500 Go', 'V', 1, 10, b'1'),
	(33, '193.0.0.2', 'ANTIVIRUS INTRANET', '8 GO', 'ANTIVIRUS-INTRANET', '', 'WINDOWS SERVER 2012', '8 GO', '200 GO', 'V', 1, 10, b'1'),
	(34, '193.0.0.11', 'VISIOCONFERENCE', '4 GO', 'TRUECONF-PRINCIPAL', '', 'WINDOWS SERVER 2012', '8 GO', '60 Go', 'V', 1, 10, b'1'),
	(35, '193.0.0.152', 'ANTIVIRUS RIGCRISE', '4 GO', 'ANTIVIRUS-RIGCRISE', '', 'WINDOWS SERVER 2012', '8 GO', '106,72 Go', 'V', 1, 10, b'1'),
	(36, '193.0.0.66', 'DEVELOPPEMENT', '4 GO', 'APPLICATION_TRANS', '', 'Ubuntu?Linux ', '8 GO', '40 Go', 'V', 1, 10, b'1'),
	(37, '193.0.0.25', 'ACTIVE DIRECTORY', '4 GO', 'ACTIVE-DIRECTORY-DINFO', '', 'WINDOWS SERVER 2012', '4 GO', '100 Go', 'V', 1, 10, b'0'),
	(38, '172.16.0.x', 'DEVELOPPEMENT', '16 GO', 'matrix VC', '', 'Ubuntu?Linux ', '8 GO', '200 Go', 'V', 1, 10, b'0'),
	(39, '193.0.0.248', 'DATABASE INTRANET', '2 GO', 'DATABASE-ORACLE', '', 'WINDOWS SERVER 2012', '8 GO', '500 GO', 'V', 1, 10, b'1'),
	(40, '172.16.0.x', 'DATABASE INTRANET', '6 GO', 'XP_BASE_DONNE', '', 'WINDOWS XP', '4 GO', '160 GO', 'V', 1, 10, b'1'),
	(41, '172.16.0.x', 'MESSAGERIE RIGCRISES', '6 GO', 'REGCRISE-PRINCIPAL', '', 'REDHAT ', '4 GO', '500 GO', 'V', 1, 10, b'0'),
	(42, '172.16.0.x', 'ACTIVE DIRECTORY', '2 GO', 'ADirectorie-trans', '', 'WINDOWS SERVER 2012', '8 GO', '40 GO', 'V', 1, 10, b'0'),
	(43, '193.0.0.247', 'APPLICATION RACLE', '2 GO', 'APPLICATION ORACLE', '', 'WINDOWS SERVER 2012', '8 GO', '260 GO', 'V', 1, 10, b'1'),
	(44, '193.0.0.150', 'MESSAGERIE RIGCRISES', '6 GO', 'SRV_REGCRISE_PRINCIPAL', '', 'REDHAT ', '16 GO', '500 GO', 'V', 1, 10, b'1'),
	(45, '172.16.0.x', 'DEVELOPPEMENT', '4 GO', 'DGE-APP', '', 'Ubuntu?Linux?', '8 GO', '40 Go', 'V', 1, 11, b'0'),
	(46, '172.16.0.x', 'DATABASE INTRANET', '2 GO', 'DATABASE-ORACLE2', '', 'WINDOWS SERVER 2012', '8 GO', '100 GO', 'V', 1, 11, b'0'),
	(47, '172.16.0.x', 'DEVELOPPEMENT', '8 GO', 'DEVOPS-TRANS', '', 'UNUNTU  LINUX', '16 GO', '100 Go', 'V', 1, 11, b'1'),
	(48, '193.0.0.242', 'ANTIVIRUS INTRANET', '2 GO', 'SRV TRELLIX', '', 'WINDOWS SERVER 2012', '4GO', '370 Go', 'V', 1, 11, b'1'),
	(49, '193.0.0.24', 'VCENTER', '2 GO', 'VMware vCenter intranet', '', 'LINUX', '12 GO', '48 GO', 'V', 1, 11, b'1'),
	(50, '193.0.0.179', 'APPLICATION DEE', '4 GO', 'GARANTIE-DEE', '', 'Ubuntu?Linux', '8 GO', '80 Go', 'V', 1, 12, b'1'),
	(51, '193.0.0.178', 'APPLICATION DEE', '4 GO', 'SOLAR_SCADA', '', 'Ubuntu?Linux', '8 GO', '80 Go', 'V', 1, 12, b'1'),
	(52, '193.0.0.177', 'DATABASE DEE', '4 GO', 'SOLAR_SCADA_BD', '', 'Ubuntu?Linux', '8 GO', '80 Go', 'V', 1, 12, b'1');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
