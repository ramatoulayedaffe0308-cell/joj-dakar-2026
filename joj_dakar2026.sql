-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 08, 2026 at 08:36 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `joj_dakar2026`
--

-- --------------------------------------------------------

--
-- Table structure for table `athlete`
--

CREATE TABLE `athlete` (
  `idAthlete` int NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  `dateNaissance` date DEFAULT NULL,
  `idPays` int DEFAULT NULL,
  `idDiscipline` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `athlete`
--

INSERT INTO `athlete` (`idAthlete`, `nom`, `prenom`, `sexe`, `dateNaissance`, `idPays`, `idDiscipline`) VALUES
(1, 'Diop', 'Moussa', 'M', '2008-04-15', 1, 1),
(2, 'Martin', 'Lucas', 'M', '2007-09-20', 2, 1),
(3, 'El Amrani', 'Yassine', 'M', '2008-02-10', 3, 5),
(4, 'Course', 'Rama', 'f', '2003-08-03', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `competition`
--

CREATE TABLE `competition` (
  `idCompetition` int NOT NULL,
  `nomCompetition` varchar(100) NOT NULL,
  `dateCompetition` date DEFAULT NULL,
  `lieu` varchar(50) DEFAULT NULL,
  `idDiscipline` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `competition`
--

INSERT INTO `competition` (`idCompetition`, `nomCompetition`, `dateCompetition`, `lieu`, `idDiscipline`) VALUES
(1, 'Finale 100m', '2026-08-10', 'Dakar', 1),
(2, 'Finale Natation 100m', '2026-08-12', 'Diamniadio', 2),
(3, 'Tournoi Football', '2026-08-15', 'Saly', 3);

-- --------------------------------------------------------

--
-- Table structure for table `discipline`
--

CREATE TABLE `discipline` (
  `idDiscipline` int NOT NULL,
  `nomDiscipline` varchar(100) NOT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `discipline`
--

INSERT INTO `discipline` (`idDiscipline`, `nomDiscipline`, `description`) VALUES
(1, 'Athlétisme', 'Courses, sauts et lancers'),
(2, 'Natation', 'Compétitions de nage'),
(3, 'Football', 'Tournoi de football'),
(4, 'Basketball', 'Compétition de basketball'),
(5, 'Judo', 'Art martial');

-- --------------------------------------------------------

--
-- Table structure for table `pays`
--

CREATE TABLE `pays` (
  `idPays` int NOT NULL,
  `nomPays` varchar(100) NOT NULL,
  `continent` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pays`
--

INSERT INTO `pays` (`idPays`, `nomPays`, `continent`) VALUES
(1, 'Sénégal', 'Afrique'),
(2, 'France', 'Europe'),
(3, 'Maroc', 'Afrique'),
(4, 'Nigeria', 'Afrique');

-- --------------------------------------------------------

--
-- Table structure for table `resultat`
--

CREATE TABLE `resultat` (
  `idResultat` int NOT NULL,
  `idAthlete` int DEFAULT NULL,
  `idCompetition` int DEFAULT NULL,
  `score` varchar(50) DEFAULT NULL,
  `rang` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `resultat`
--

INSERT INTO `resultat` (`idResultat`, `idAthlete`, `idCompetition`, `score`, `rang`) VALUES
(1, 1, 1, '10.20 secondes', 1),
(2, 2, 1, '10.50 secondes', 2),
(3, 3, 1, '10.70 secondes', 3);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUtilisateur` int NOT NULL,
  `nomComplet` varchar(100) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nomComplet`, `login`, `password`, `role`) VALUES
(1, 'Administrateur JOJ', 'admin', '1234', 'ADMIN'),
(2, 'Agent Competition', 'agent', '1234', 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `athlete`
--
ALTER TABLE `athlete`
  ADD PRIMARY KEY (`idAthlete`),
  ADD KEY `idPays` (`idPays`),
  ADD KEY `idDiscipline` (`idDiscipline`);

--
-- Indexes for table `competition`
--
ALTER TABLE `competition`
  ADD PRIMARY KEY (`idCompetition`),
  ADD KEY `idDiscipline` (`idDiscipline`);

--
-- Indexes for table `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`idDiscipline`);

--
-- Indexes for table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`idPays`);

--
-- Indexes for table `resultat`
--
ALTER TABLE `resultat`
  ADD PRIMARY KEY (`idResultat`),
  ADD KEY `idAthlete` (`idAthlete`),
  ADD KEY `idCompetition` (`idCompetition`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUtilisateur`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `athlete`
--
ALTER TABLE `athlete`
  MODIFY `idAthlete` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `competition`
--
ALTER TABLE `competition`
  MODIFY `idCompetition` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `discipline`
--
ALTER TABLE `discipline`
  MODIFY `idDiscipline` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pays`
--
ALTER TABLE `pays`
  MODIFY `idPays` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `resultat`
--
ALTER TABLE `resultat`
  MODIFY `idResultat` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUtilisateur` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `athlete`
--
ALTER TABLE `athlete`
  ADD CONSTRAINT `athlete_ibfk_1` FOREIGN KEY (`idPays`) REFERENCES `pays` (`idPays`) ON DELETE CASCADE,
  ADD CONSTRAINT `athlete_ibfk_2` FOREIGN KEY (`idDiscipline`) REFERENCES `discipline` (`idDiscipline`) ON DELETE CASCADE;

--
-- Constraints for table `competition`
--
ALTER TABLE `competition`
  ADD CONSTRAINT `competition_ibfk_1` FOREIGN KEY (`idDiscipline`) REFERENCES `discipline` (`idDiscipline`) ON DELETE CASCADE;

--
-- Constraints for table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `resultat_ibfk_1` FOREIGN KEY (`idAthlete`) REFERENCES `athlete` (`idAthlete`) ON DELETE CASCADE,
  ADD CONSTRAINT `resultat_ibfk_2` FOREIGN KEY (`idCompetition`) REFERENCES `competition` (`idCompetition`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
