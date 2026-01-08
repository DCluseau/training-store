-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 05 jan. 2026 à 12:15
-- Version du serveur : 12.1.2-MariaDB
-- Version de PHP : 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `trainingstore`
--
CREATE DATABASE IF NOT EXISTS `trainingstore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `trainingstore`;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(2, 'Distanciel'),
(1, 'Présentiel');

-- --------------------------------------------------------

--
-- Structure de la table `is_category`
--

DROP TABLE IF EXISTS `is_category`;
CREATE TABLE IF NOT EXISTS `is_category` (
  `id_training` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_training`,`id_category`),
  KEY `id_category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `is_category`
--

INSERT INTO `is_category` (`id_training`, `id_category`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(6, 1),
(2, 2),
(5, 2),
(6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `training`
--

DROP TABLE IF EXISTS `training`;
CREATE TABLE IF NOT EXISTS `training` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` decimal(15,2) NOT NULL,
  `description` text DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `training`
--

INSERT INTO `training` (`id`, `name`, `price`, `description`) VALUES
(1, 'Poutrage d\'orcs', 100.00, 'Apprenez les techniques des plus grands aventuriers pour affronter les terribles créatures de Valbise ! (équipement non fourni)'),
(2, 'Piloter un vaisseau spatial', 1000.00, ''),
(3, 'Apprentissage de la force - niveau padawan', 200.50, 'Apprenez les bases de la Force et fabriquez votre premier sabrolaser !'),
(4, 'Apprentissage de la force - niveau maître', 401.31, 'Maîtrisez les pouvoirs avancés de la Force : manipulation mentale, lancement d\'éclairs, lévitation. Sabrolaser non fournis.'),
(5, 'Renverser une dictature', 6000.00, 'Apprenez à comploter contre une dictature et libérez tout un peuple grâce à votre dévouement.'),
(6, 'Art de la Voix', 0.00, 'Apprenez vos premiers mots draconiques et le langage des dragons.\r\nCours réservés aux Enfants de Dragon qui ont monté les sept mille marches jusqu\'au Haut-Hrothgar.');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `is_category`
--
ALTER TABLE `is_category`
  ADD CONSTRAINT `1` FOREIGN KEY (`id_training`) REFERENCES `training` (`id`),
  ADD CONSTRAINT `2` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
