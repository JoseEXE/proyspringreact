-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 09 oct. 2023 à 07:06
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `quickresto`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id` bigint(20) NOT NULL,
  `cod_postal` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `rue` varchar(255) DEFAULT NULL,
  `statut` tinyint(1) DEFAULT 1,
  `updated_on` datetime(6) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `id_client` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id`, `cod_postal`, `complement`, `created_on`, `rue`, `statut`, `updated_on`, `ville`, `id_client`) VALUES
(1, '75001', 'Devant la porte rouge', '2023-10-08 16:56:57.000000', '123 Rue de la République', 1, '2023-10-08 16:56:57.000000', 'Paris', 2),
(2, '75008 ', 'Pass.Cheval ', '2023-10-09 05:46:45.000000', '456 Avenue des Champs-Élysées', 1, '2023-10-09 05:46:45.000000', 'Paris', 10),
(3, '75011', 'République', '2023-10-09 05:52:19.000000', '789 Rue du Faubourg Saint-Antoine', 1, '2023-10-09 05:52:19.000000', 'Paris', 10),
(4, '75007', '', '2023-10-09 05:58:46.000000', '234 Boulevard Saint-Germain', 1, '2023-10-09 05:58:46.000000', 'Paris', 1),
(5, '75001', 'Isla de Francia', '2023-10-09 06:01:28.000000', '15 Rue de Rivoli', 1, '2023-10-09 06:01:28.000000', 'Paris', 7);

-- --------------------------------------------------------

--
-- Structure de la table `cat_produit`
--

CREATE TABLE `cat_produit` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(100) NOT NULL,
  `statut` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cat_produit`
--

INSERT INTO `cat_produit` (`id`, `description`, `nom`, `statut`) VALUES
(1, 'les sushis, sashimi...', 'Plat', 1),
(2, 'Coca, orangina, bieres, cocktails sans alcool', 'Boisson', 1),
(3, 'composition de plats, desserts et boissons', 'Menu', 1),
(4, 'lychee, glace...', 'Dessert', 1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `tel` varchar(100) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `statut` tinyint(1) DEFAULT 1,
  `updated_on` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `created_on`, `nom`, `tel`, `prenom`, `statut`, `updated_on`) VALUES
(1, '2023-10-07 14:35:49.000000', 'DUPONT', '0613218841', 'Pierre', 1, '2023-10-07 15:44:00.000000'),
(2, '2023-10-07 15:12:52.000000', 'MARTIN', '0613218842', 'Marie', 1, '2023-10-07 15:12:52.000000'),
(7, '2023-10-07 15:19:12.000000', 'DUBOIS', '0613218843', 'Antoine', 1, '2023-10-07 15:19:12.000000'),
(10, '2023-10-07 15:21:47.000000', 'MOREAU', '0613218844', 'Élise', 1, '2023-10-07 15:21:47.000000'),
(11, '2023-10-07 15:23:42.000000', 'LEFEBVRE', '0613218845', 'Jean', 1, '2023-10-07 15:23:42.000000'),
(12, '2023-10-07 15:24:25.000000', 'GIRARD', '0613218846', 'Camille', 1, '2023-10-07 15:24:25.000000'),
(13, '2023-10-07 15:37:46.000000', 'ROUX', '0613218847', 'Baptiste', 1, '2023-10-07 15:37:46.000000'),
(14, '2023-10-07 15:43:05.000000', 'LAURENT', '0613218848', 'Chloé', 1, '2023-10-07 15:43:05.000000');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `total_hl` double DEFAULT NULL,
  `type_paiement` varchar(255) DEFAULT NULL,
  `id_client` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `id_adresse` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `commentaire`, `created_on`, `etat`, `total`, `total_hl`, `type_paiement`, `id_client`, `id_user`, `id_adresse`) VALUES
(1, '', '2023-10-09 03:53:35.000000', NULL, 0, 0, 'Ticket Resto', 2, 2, 1),
(2, '', '2023-10-09 06:27:07.000000', '0', 0, 0, 'Carte Bancaire', 10, 2, 3),
(3, 'Commentaire', '2023-10-09 06:44:54.000000', '0', 0, 0, 'Carte Bancaire', 1, 2, 4),
(4, '', '2023-10-09 06:49:31.000000', '0', 0, 0, 'Carte Bancaire', 1, 2, 4),
(5, '', '2023-10-09 06:55:02.000000', '0', 0, 0, 'Carte Bancaire', 1, 2, 4);

-- --------------------------------------------------------

--
-- Structure de la table `detail_commande`
--

CREATE TABLE `detail_commande` (
  `id` bigint(20) NOT NULL,
  `prix_unitaire` double DEFAULT NULL,
  `quantite` int(11) NOT NULL,
  `id_commande` bigint(20) DEFAULT NULL,
  `id_produit` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

CREATE TABLE `etablissement` (
  `id` bigint(20) NOT NULL,
  `cod_postal` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `num_tel` varchar(100) NOT NULL,
  `rue` varchar(255) DEFAULT NULL,
  `siret` varchar(100) NOT NULL,
  `statut` tinyint(1) DEFAULT 1,
  `updated_on` datetime(6) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etablissement`
--

INSERT INTO `etablissement` (`id`, `cod_postal`, `created_on`, `nom`, `num_tel`, `rue`, `siret`, `statut`, `updated_on`, `ville`) VALUES
(1, '75011', '2023-09-12 16:20:16.000000', 'ETE EDO - Paris 11E', '0113218844', '14 RUE DE NICE', '123456789087654', 1, '2023-10-08 23:43:04.000000', 'Paris'),
(2, '30304', '2023-10-03 14:54:23.000000', 'Nombre de ejemplo', '8843120223', 'Rue', '1234567890', 0, '2023-10-08 23:42:59.000000', 'Loren');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix` double NOT NULL,
  `statut` tinyint(1) DEFAULT 1,
  `type_plat` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `id_cat_produit` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `code`, `created_on`, `description`, `nom`, `prix`, `statut`, `type_plat`, `updated_on`, `id_cat_produit`, `id_user`) VALUES
(1, 'PO01', '2023-09-25 11:25:16.000000', 'description', 'Nom de Prod', 0.7, 0, 'Froid', '2023-10-08 15:59:53.000000', 1, 2),
(2, 'P01x', '2023-09-25 11:25:19.000000', 'PokeBowl crevette x', 'Poke1 Tempura x', 12.5, 1, 'Froid', '2023-10-06 15:51:43.000000', 4, 2),
(3, 'P01', '2023-09-25 15:02:25.000000', 'PokeBowl crevette', 'Poke1 Tempura', 12, 0, 'Chaud', '2023-09-25 15:02:25.000000', 1, 2),
(20, 'PO01', '2023-09-26 01:14:35.000000', 'description', 'Nom de Prod', 0.5, 1, 'Chaud', '2023-09-26 01:14:35.000000', 1, 2),
(23, 'DO01', '2023-10-08 16:29:41.000000', 'description', 'Nom de Prod', 5, 1, 'Froid', '2023-10-08 16:29:41.000000', 4, 2),
(24, '15', '2023-09-25 11:25:16.000000', 'NÊMS AU POULET (5 PCS)', 'NÊMS AU POULET', 4.5, 0, 'Chaud', '2023-10-06 15:47:09.000000', 1, 2),
(25, '16', '2023-09-25 11:25:16.000000', 'RAVIOLIS JAPONAIS FRITS GYOZA (5 PCS)', 'RAVIOLIS JAPONAIS FRITS GYOZA', 4.8, 0, 'Chaud', '2023-10-06 15:47:09.000000', 1, 2),
(26, '17', '2023-09-25 11:25:16.000000', 'POULET BEIGNE (5 PIECES)', 'POULET BEIGNE', 4.8, 0, 'Chaud', '2023-10-06 15:47:09.000000', 1, 2),
(27, '1', '2023-09-25 11:25:16.000000', 'SALADE DE CHOUX', 'SALADE DE CHOUX', 2, 0, 'Froid', '2023-10-06 15:47:09.000000', 1, 2),
(28, '3', '2023-09-25 11:25:16.000000', 'EDAMAME', 'EDAMAME', 3.5, 0, 'Froid', '2023-10-06 15:47:09.000000', 1, 2),
(29, '4', '2023-09-25 11:25:16.000000', 'TARTARE SAUMON AVOCAT', 'TARTARE SAUMON AVOCAT', 5.6, 0, 'Froid', '2023-10-06 15:47:09.000000', 1, 2),
(30, 'M15', '2023-09-25 11:25:16.000000', 'chirachi saumon', 'M15', 10.8, 0, 'Froid', '2023-10-06 15:47:09.000000', 3, 2),
(31, 'M16', '2023-09-25 11:25:16.000000', 'chirachi saumon thon', 'M16', 12.8, 0, 'Froid', '2023-10-06 15:47:09.000000', 3, 2),
(32, 'M17', '2023-09-25 11:25:16.000000', 'chirachi assortiment, saumon thon daurade', 'M17', 0.7, 0, 'Froid', '2023-10-06 15:47:09.000000', 3, 2),
(33, 'PC11', '2023-09-25 11:25:16.000000', 'soupe, 1 salade, Poulet aux oignons', 'PC11', 10, 1, 'Chaud', '2023-10-06 15:47:09.000000', 3, 2),
(34, 'PC2', '2023-09-25 11:25:19.000000', '1 soupe, 1 salade, Boeuf aux oignons', 'PC2', 11, 1, 'Chaud', '2023-10-06 15:51:43.000000', 3, 2),
(35, 'PC3', '2023-09-25 15:02:25.000000', '1 soupe, 1 salade, Crevette au basilic à la thaïlandaise', 'PC3', 13, 1, 'Chaud', '2023-09-25 15:02:25.000000', 3, 2),
(36, 'M6', '2023-09-26 01:14:35.000000', '1 soupe, 1 salade, 1 riz 6 maki saumon, 3 sushi, 9 sashimi assorti', 'M6', 14.9, 1, 'Froid', '2023-09-26 01:14:35.000000', 3, 2),
(37, 'M6A', '2023-09-26 01:14:35.000000', '1 soupe, 1 salade, 1 riz, 8 california saumon avocat, 9 sashimi saumon', 'M6A', 13.5, 1, 'Froid', '2023-09-26 01:14:35.000000', 3, 2),
(38, 'M7', '2023-09-26 01:14:35.000000', '1 soupe, 1 salade, 1 riz, 16 sashimi saumon', 'M7', 14.5, 1, 'Froid', '2023-09-26 01:14:35.000000', 3, 2),
(39, 'CS1', '2023-09-26 01:14:35.000000', '1 soupe, 1 salade, 1 riz, 8 california saumon avocat, 4 nêms au poulet', 'CS1', 10, 1, 'Mix', '2023-09-26 01:14:35.000000', 3, 2),
(40, 'CS2', '2023-09-26 01:14:35.000000', '1 soupe, 1 salade, 1 riz, 4 nêms au poulet, 6 sushi assorti', 'CS2', 14.5, 1, 'Mix', '2023-09-26 01:14:35.000000', 3, 2),
(41, 'CS3', '2023-09-26 01:14:35.000000', '1 soupe, 1 salade, 8 caifornia, 6 sashimi saumon, 4 Gyoza', 'CS3', 0.5, 1, 'Mix', '2023-09-26 01:14:35.000000', 3, 2),
(42, 'D1', '2023-09-25 11:25:16.000000', 'LYCHÉE AU SIROP', 'LYCHÉE AU SIROP', 3, 0, 'Froid', '2023-10-06 15:47:09.000000', 4, 2),
(43, 'D2', '2023-09-25 11:25:16.000000', 'NOUGAT', 'NOUGAT', 3, 0, 'Froid', '2023-10-06 15:47:09.000000', 4, 2),
(44, 'D9', '2023-09-25 11:25:16.000000', 'HÄAGEN-DAZS(100ML)', 'HÄAGEN-DAZS', 3, 0, 'Froid', '2023-10-06 15:47:09.000000', 4, 2),
(45, 'D3', '2023-09-25 11:25:16.000000', 'PERLE DE COCO(2PCS)', 'PERLE DE COCO', 3, 0, 'Chaud', '2023-10-06 15:47:09.000000', 4, 2),
(46, 'D5', '2023-09-25 11:25:16.000000', 'DORAYAKI(GÂTEAU JAPONAIS/UNITÉ)', 'DORAYAKI', 3.5, 0, 'Chaud', '2023-10-06 15:47:09.000000', 4, 2),
(47, 'D7', '2023-09-25 11:25:16.000000', 'BRIOCHE À LA CRÈME DE LOTUS (2PCS)', 'BRIOCHE À LA CRÈME DE LOTUS', 3, 0, 'Chaud', '2023-10-06 15:47:09.000000', 4, 2),
(48, 'B01', '2023-09-25 11:25:16.000000', 'COCA COLA (33CL)', 'COCA COLA', 1.5, 0, 'Froid', '2023-10-06 15:47:09.000000', 2, 2),
(49, 'B02', '2023-09-25 11:25:16.000000', 'COCA ZÉRO (33CL)', 'COCA ZÉRO', 1.5, 0, 'Froid', '2023-10-06 15:47:09.000000', 2, 2),
(50, 'B03', '2023-09-25 11:25:16.000000', 'ICE TEA (33CL)', 'ICE TEA', 0.7, 0, 'Froid', '2023-10-06 15:47:09.000000', 2, 2),
(51, 'B04', '2023-09-25 11:25:16.000000', 'SAKÉ (30CL)', 'SAKÉ', 12.5, 0, 'Froid', '2023-10-06 15:47:09.000000', 2, 2),
(52, 'B05', '2023-09-25 11:25:16.000000', 'EVIAN (50CL)', 'EVIAN', 2, 0, 'Froid', '2023-10-06 15:47:09.000000', 2, 2),
(53, 'B06', '2023-09-25 11:25:16.000000', 'BADOIT (50CL)', 'BADOIT', 2, 0, 'Froid', '2023-10-06 15:47:09.000000', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nameu` varchar(12) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `statut` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `last_name`, `nameu`, `password`, `statut`) VALUES
(1, 'mail@mail.com', 'Jose', 'AQUINO', '$2a$10$Rd9KYXvk/hxWc8Vu6u1G2OeIU80d4deswe..InA8Qs03n5cLU9TT2', 1),
(2, 'admin@admin.com', 'Manager', 'Admin', '$2a$10$I7xETbTvGnvrvMyRkqNoLuLUL9n66IMMl8XH/ecmqC3NMy267sgKu', 1),
(6, 'test@test.com', 'Test', 'TEST', '$2a$10$0vVKFasl7JOjZo1qdiMize76lp4749/vm.L.ezC/iLb2Hg80rWWkO', 1),
(8, 'snejana@mailx.com', 'Snejana', 'HODOSOVA', '$2a$10$aN3onY6QmQOQ9McPfg5gO.vcf1/4tCfjj2veI9392L17VYvqCpC2O', 1),
(9, 'xxx@xxx.com', 'José', 'CASTRO', '$2a$10$rPRM2LNXFLNewqiHn1VA.u6lDSkPp1nFo39gtQc/N2S7gAobnnT2O', 1),
(10, 'test1@test1.com', 'test1', 'TEST1', '$2a$10$OuaHEoJ6aYWjjICHOQRNyuX5EaIbbPveHVm1g/Wm2Z47SxlrA8tX.', 1),
(11, 'test2@test2.com', 'Test2', 'TEST2', '$2a$10$Vf940Q0CLJ.jJuhG2zj0AOZYjbDQKSWDLye2WmLuESbqbVVHY2aHC', 1),
(12, 'test3@test3.com', 'Test3', 'TEST3', '$2a$10$rfjKuK3u22hN9.XbgJ4UMOQvSJo/Etnbj06q8CPk8kbThdhaPYzre', 1),
(13, 'test4@test4.com', 'Test4', 'TEST4', '$2a$10$8C/XlI7O624W32JGcUuWVu85PXw9WhTbAzZ57ztt3gSL79pgDg2Ty', 1),
(14, 'test5@test5.com', 'Test5', 'TEST5', '$2a$10$sZgVT.g09i2sWrH5Qww5yeMHEh8pYY7RohFLjxg6geVoIwvGishm6', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user_etablissement`
--

CREATE TABLE `user_etablissement` (
  `user_id` bigint(20) NOT NULL,
  `etablissement_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 1),
(2, 2),
(6, 1),
(6, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq6prsqjoj2be37jc7tycfocbv` (`id_client`);

--
-- Index pour la table `cat_produit`
--
ALTER TABLE `cat_produit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t6t8ss20fecxj2hkrd2as5xp2` (`nom`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_5isbr7w5x8vbapumswk3mmiql` (`tel`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfh667737scbwrhtkephs23hq` (`id_client`),
  ADD KEY `FKj8v3m3grnqrlxa55wflvjprp9` (`id_user`),
  ADD KEY `FKf5812opyxpshw6cl6iualkyvg` (`id_adresse`);

--
-- Index pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKak2cq6il1c6q8mtgj1jh03jhh` (`id_commande`),
  ADD KEY `FKpokyxoxbbf22v0i76ybyuif9t` (`id_produit`);

--
-- Index pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_arhc14gjy1gcamod9aahuiefl` (`num_tel`),
  ADD UNIQUE KEY `UK_rnicmtyxb59l46iokd7n3exdi` (`siret`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlpx10a47hs01mvq8iqpqwiax6` (`id_cat_produit`),
  ADD KEY `FKre72i7h3dshla8vqfedqjvnhv` (`id_user`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Index pour la table `user_etablissement`
--
ALTER TABLE `user_etablissement`
  ADD UNIQUE KEY `UKampq61qgx355whurpm8f4i542` (`user_id`,`etablissement_id`),
  ADD KEY `FKqa9kjflb3r709ynfsfxwhmkui` (`etablissement_id`);

--
-- Index pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD UNIQUE KEY `UK872xec3woupu3gw59b04pj3sa` (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `cat_produit`
--
ALTER TABLE `cat_produit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `etablissement`
--
ALTER TABLE `etablissement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FKq6prsqjoj2be37jc7tycfocbv` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FKf5812opyxpshw6cl6iualkyvg` FOREIGN KEY (`id_adresse`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FKfh667737scbwrhtkephs23hq` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKj8v3m3grnqrlxa55wflvjprp9` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  ADD CONSTRAINT `FKak2cq6il1c6q8mtgj1jh03jhh` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `FKpokyxoxbbf22v0i76ybyuif9t` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FKlpx10a47hs01mvq8iqpqwiax6` FOREIGN KEY (`id_cat_produit`) REFERENCES `cat_produit` (`id`),
  ADD CONSTRAINT `FKre72i7h3dshla8vqfedqjvnhv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user_etablissement`
--
ALTER TABLE `user_etablissement`
  ADD CONSTRAINT `FKqa9kjflb3r709ynfsfxwhmkui` FOREIGN KEY (`etablissement_id`) REFERENCES `etablissement` (`id`),
  ADD CONSTRAINT `FKs5wgap6blfquh45hk1c7xdbkr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
