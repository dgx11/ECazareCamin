-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 28, 2023 at 10:25 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecazare`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_details`
--

CREATE TABLE `booking_details` (
  `userId` text NOT NULL,
  `colegCamera` text NOT NULL,
  `domiciliu` text NOT NULL,
  `an` text NOT NULL,
  `medieAnuala` text NOT NULL,
  `medieAdmitere` text NOT NULL,
  `medie` text NOT NULL,
  `faraTaxa` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking_details`
--

INSERT INTO `booking_details` (`userId`, `colegCamera`, `domiciliu`, `an`, `medieAnuala`, `medieAdmitere`, `medie`, `faraTaxa`) VALUES
('12', '13', 'Eforie', '1', '', '8.98', '8.98', 1),
('17', '21', 'Agigea', '2', '9.45', '', '9.45', 0),
('13', '21', 'Agigea', '1', '', '6.67', '6.67', 0),
('18', '28', 'Eforie Sud', '1', '', '7.89', '7.89', 1),
('44', '13', 'Techirghiol', '4', '4.56', '', '4.56', 0),
('53', '13', 'Soveja', '4', '9.20', '', '9.20', 1),
('57', '21', 'Eforie', '1', '', '9', '9', 1),
('58', '21', 'Eforie nord', '2', '9.98', '', '9.98', 1),
('60', '21', 'Eforie', '1', '', '6.78', '6.78', 1),
('61', '13', 'Eforie Nord', '2', '8.34', '', '8.34', 1),
('62', '13', 'Eforie nord', '3', '9.12', '9.23', '9.23', 1),
('63', '13', '', '2', '8.3', '9.83', '9.83', 1);

-- --------------------------------------------------------

--
-- Table structure for table `repartition_list`
--

CREATE TABLE `repartition_list` (
  `generated` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `repartition_list`
--

INSERT INTO `repartition_list` (`generated`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `students_details`
--

CREATE TABLE `students_details` (
  `userId` text NOT NULL,
  `nume` text NOT NULL,
  `prenume` text NOT NULL,
  `facultate` text NOT NULL,
  `anFacultate` text NOT NULL,
  `specializare` text NOT NULL,
  `CNP` text NOT NULL,
  `domiciliu` text NOT NULL,
  `tipDeStudii` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students_details`
--

INSERT INTO `students_details` (`userId`, `nume`, `prenume`, `facultate`, `anFacultate`, `specializare`, `CNP`, `domiciliu`, `tipDeStudii`) VALUES
('13', 'Andrei', 'Vali', 'Informatica', '2', 'Informatica-Engleza', '5012312312', 'Constanta', 'Cu Taxa'),
('21', 'Test', 'tet', 'test', 'test', 'tet', 'test', 'st', 'Fara Taxa'),
('21', 'MODIFICARE', 'tet', 'test', 'test', 'tet', 'test', 'st', 'Fara Taxa'),
('12', 'TEST EDIT', 'WEAEADS', 'FAC EDITATA', '4', 'specializare', '13213123', 'Constanta', 'Fara Taxa'),
('13', 'Andreitest', 'Vali', 'Informatica', '2', 'Informatica-Engleza', '5012312312', 'Constanta', 'Cu Taxa'),
('18', 'EDITED', 'test', 'test', 'test', 'test', 'test', 'test', 'Fara Taxa'),
('17', 'TEST ADAUGARE NUME', '', '', 'TEST  ADUGARE AN', '', '', '', 'Cu Taxa'),
('19', 'test adaugareooko', 'prenume', 'andrei', '3', 'ovidius', '213123', 'costinesti', 'Cu Taxa'),
('20', 'test add', 'prenume', 'asdasd', 'sd', 'OK ADD 08:36', '', '', 'Cu Taxa'),
('23', 'TEST ADD ', '', '', '', '', '', '', 'Fara Taxa'),
('25', 'TEST ADD 2132username1@yahoo.com', '', '', '', '', '', '', 'Fara Taxa'),
('27', 'TEST', '', '', '', '', '', '', 'Cu Taxa'),
('28', 'OK', 'OKO', 'K', 'OK', 'OK', 'OK', 'OK', 'Fara Taxa'),
('37', '', '', '', '', '', '5021129313455', '', 'Cu Taxa'),
('38', '', '', '', '', '', '5021129345123', '', 'Fara Taxa'),
('35', '', '', '', '', '', '5021123451243', '', 'Fara Taxa'),
('40', 'Andrei', 'Test', 'Facultate', '2', '', '5021129234131', '', 'Fara Taxa'),
('42', 'Andrei', 'Prenume', 'Facultate', '4', 'Matematica', '6020503016512', 'Eforie Nord', 'Cu Taxa'),
('43', '', '', '', '', '', '6020503016512', '', 'Fara Taxa'),
('44', 'TEST', 'TEST', 'TEST', 'TEST', 'TEST', '6020503016512', 'TEST', 'Cu Taxa'),
('49', 'TEST', 'TEST', 'TEST', 'TEST', 'TEST', '6020503016512', 'TEST', 'Fara Taxa'),
('50', 'TEST', 'TEST', 'TEST', 'TEST', 'TEST', '6020503016512', 'test', 'Fara Taxa'),
('51', 'TEST', 'TEST', '', 'test', 'test', '6020503016512', 'test', 'Fara Taxa'),
('52', 'test', 'test', 'test', 'test', 'test', '6020503016512', 'test', 'Fara Taxa'),
('53', 'Alex', 'G', 'Informatica', '4', 'Mate-Info', '6020503016512', 'Soveja', 'Fara Taxa'),
('57', '', '', '', '', '', '6020503016985', '', 'Fara Taxa'),
('58', '', '', '', '', '', '6020503016985', '', 'Fara Taxa'),
('60', '', '', '', '', '', '6020503016985', '', 'Fara Taxa'),
('61', '', '', '', '', '', '6020503016985', '', 'Fara Taxa'),
('62', '', '', '', '', '', '6020503016985', '', 'Fara Taxa'),
('63', 'TEST', '', '', '', '', '6020503016985', '', 'Fara Taxa');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `account_type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `account_type`) VALUES
(1, 'andrei', '', 'parola', 'student'),
(2, 'andrei', '', 'parola', 'student'),
(3, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(4, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(5, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(6, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(7, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(8, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(9, 'andrei', 'andrei@yahoo.com', 'parola123', 'student'),
(10, 'student', 'email', 'parola', 'administrator'),
(11, 'student', 'email', 'parola', 'administrator'),
(12, 'Andrei', 'test@yahoo.com', 'test', 'Student'),
(13, 'Test', 'test1@yahoo.com', 'test', 'Student'),
(14, 'flash2', 'flash@yahoo.com', 'test', 'Membru Comisie'),
(15, 'FLASH STUDENT 1', 'test2@yahoo.com', 'test', 'Student'),
(16, 'test', 'testtest@yahoo.com', 'test', 'Student'),
(17, 'test', 'ok@yahoo.com', 'test', 'Student'),
(18, 'ok1@', 'ok1@yahoo.com', 'test', 'Student'),
(19, 'cont nou', 'cont@yahoo.com', 'test', 'Student'),
(20, 'contnou', 'contnou@yahoo.com', 'test', 'Student'),
(21, 'contnou2', 'contnou2@yahoo.com', 'test', 'Student'),
(22, 'test username', 'okk@yahoo.com', 'test', 'Membru Comisie'),
(23, 'ok@test.com', 'ok@test.com', 'test', 'Student'),
(24, 'test username', 'username@yahoo.com', 'test', 'Membru Comisie'),
(25, 'username@yahoo.com', 'username1@yahoo.com', 'test', 'Student'),
(26, 'STUDENT NOU', 'studentnou@yahoo.com', 'test', 'Student'),
(27, 'Test Andrei', 'alex@yahoo.com', 'test', 'Student'),
(28, 'adsdasd', 'alex1@yahoo.com', 'test', 'Student'),
(29, 'test', 'test22@yahoo.com', 'test', 'Membru Comisie'),
(30, 'Nume si Prenume', 'membrucomisie@yahoo.com', 'test', 'Membru Comisie'),
(31, 'FLASH', 'flash2@yahoo.com', 'test', 'Student'),
(32, 'test', 'testok@yahoo.com', 'test', 'Membru Comisie'),
(33, 'test', 'teststudent@yahoo.com', 'test', 'Student'),
(34, 'test', 'flash29@yahoo.com', 'test', 'Student'),
(35, 'test', 'xtest@yahoo.com', 'test', 'Student'),
(36, 'test', 'testy@yahoo.com', 'test', 'Membru Comisie'),
(37, 'test', 'testt@yahoo.com', 'test', 'Student'),
(38, 'test', 'student1@yahoo.com', 'test', 'Student'),
(39, 'Andrei Vali Test', 'test@gmail.com', 'test', 'Membru Comisie'),
(40, 'test 3', 'test1@gmail.com', 'test', 'Student'),
(41, 'test', 'test2@gmail.com', 'test', 'Membru Comisie'),
(42, 'test', 'test4@gmail.com', 'test', 'Student'),
(43, 'test', 'test5@gmail.com', 'test', 'Student'),
(44, 'nume si prenume', 'test6@gmail.com', 'test', 'Student'),
(45, 'test', 'test7@gmail.com', 'test', 'Membru Comisie'),
(46, 'test', 'test8@gmail.com', 'test', 'Student'),
(47, 'test', 'test9@gmail.com', 'test', 'Student'),
(48, 'test', 'ok@gmail.cm', 'test', 'Student'),
(49, 'Jackson', 'j@yahoo.com', 'test', 'Student'),
(50, 'test', 'y@gmail.com', 'test', 'Student'),
(51, 'test', 'o@gmail.com', 'test', 'Student'),
(52, 'test', 'u@yahoo.com', 'test', 'Student'),
(53, 'Alex G', 'g@yahoo.com', 'test', 'Student'),
(54, 'test test', 'test98@yahoo.com', 'test', 'Student'),
(55, 'test88@yahoo.com', 'test88@yahoo.com', 'test', 'Student'),
(56, 'test99@yahoo.com', 'test99@yahoo.com', 'test', 'Membru Comisie'),
(57, 'test99@yahoo.com', 'test993@yahoo.com', 'test', 'Student'),
(58, 'test', 'okok@yahoo.co', 'test', 'Student'),
(59, 'test', 'okokok@gmail.com', 'test', 'Membru Comisie'),
(60, 'test', 'okokokok@gmail.com', 'test', 'Student'),
(61, 'TEST', 'test938@yahoo.com', 'test', 'Student'),
(62, 'test', 'test213@YAHOO.COM', 'test', 'Student'),
(63, 'OKASODKASD', '98@yahoo.com', 'test', 'Student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
