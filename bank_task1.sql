-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2020 at 01:01 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_task1`
--

-- --------------------------------------------------------

--
-- Table structure for table `deposits`
--

CREATE TABLE `deposits` (
  `transactionId` int(11) NOT NULL,
  `accountNumber` int(50) NOT NULL,
  `amount` int(50) NOT NULL,
  `newBalance` int(50) NOT NULL,
  `date/time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `deposits`
--

INSERT INTO `deposits` (`transactionId`, `accountNumber`, `amount`, `newBalance`, `date/time`) VALUES
(1, 123456789, 450, 16650, '2020-08-13 12:10:09'),
(2, 123456789, 1200, 17850, '2020-08-13 12:14:10'),
(3, 123456789, 2500, 20350, '2020-08-13 13:26:37'),
(4, 1234, 15000, 18000, '2020-08-13 22:44:14');

-- --------------------------------------------------------

--
-- Table structure for table `usertable`
--

CREATE TABLE `usertable` (
  `accountNumber` int(16) NOT NULL,
  `password` varchar(8) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `otherName` varchar(100) NOT NULL,
  `nationalId` int(50) NOT NULL,
  `mobile` int(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `balance` int(50) NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usertable`
--

INSERT INTO `usertable` (`accountNumber`, `password`, `surname`, `otherName`, `nationalId`, `mobile`, `email`, `balance`, `dateCreated`) VALUES
(1234, '1234', 'test', 'user', 1234, 1234, 'test@yahoo.com', 3011, '2020-08-14 12:17:13'),
(7867, '9090', 'Tappit', 'Doe', 1245, 7896756, 'doe@hotmail.com', 124033, '2020-08-14 12:17:13'),
(7890, '5656', 'Nyawira', 'Lydia', 67123, 7895634, 'nyawira.lydia@gmail.com', 20577, '2020-08-14 12:17:13'),
(123456789, '2001', 'ian', 'athanem', 123456, 712345678, 'sanem@gmail.com', 20000, '2020-08-14 12:17:13');

-- --------------------------------------------------------

--
-- Table structure for table `withdrawals`
--

CREATE TABLE `withdrawals` (
  `transactionId` int(11) NOT NULL,
  `accountNumber` int(50) NOT NULL,
  `date/time` timestamp NOT NULL DEFAULT current_timestamp(),
  `recipient` int(50) NOT NULL,
  `newBalance` int(50) NOT NULL,
  `amount` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `withdrawals`
--

INSERT INTO `withdrawals` (`transactionId`, `accountNumber`, `date/time`, `recipient`, `newBalance`, `amount`) VALUES
(1, 123456789, '2020-08-13 08:49:35', 123456789, 62, 38),
(2, 1234, '2020-08-13 08:56:28', 1234, 26900, 400),
(3, 7867, '2020-08-13 09:00:16', 7867, 124033, 76067),
(4, 123456789, '2020-08-13 13:26:51', 123456789, 20000, 350);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deposits`
--
ALTER TABLE `deposits`
  ADD PRIMARY KEY (`transactionId`),
  ADD KEY `accountNumber` (`accountNumber`);

--
-- Indexes for table `usertable`
--
ALTER TABLE `usertable`
  ADD PRIMARY KEY (`accountNumber`);

--
-- Indexes for table `withdrawals`
--
ALTER TABLE `withdrawals`
  ADD PRIMARY KEY (`transactionId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deposits`
--
ALTER TABLE `deposits`
  MODIFY `transactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `withdrawals`
--
ALTER TABLE `withdrawals`
  MODIFY `transactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `deposits`
--
ALTER TABLE `deposits`
  ADD CONSTRAINT `deposits_ibfk_1` FOREIGN KEY (`accountNumber`) REFERENCES `usertable` (`accountNumber`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
