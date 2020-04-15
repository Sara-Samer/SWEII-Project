-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Apr 15, 2020 at 09:12 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `online_store_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `login_token`
--

DROP TABLE IF EXISTS `login_token`;
CREATE TABLE IF NOT EXISTS `login_token` (
  `token` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `encryption_key` int(11) NOT NULL DEFAULT '512',
  `type` varchar(11) COLLATE utf8_bin NOT NULL,
  UNIQUE KEY `token` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `login_token`
--

INSERT INTO `login_token` (`token`, `start_date`, `end_date`, `encryption_key`, `type`) VALUES
('989796103126989796103102101', '2020-04-15 20:49:16', '2020-04-15 21:04:16', 83, 'BUYER');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `dtype` varchar(31) COLLATE utf8_bin NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`dtype`, `id`, `username`, `email`, `password`, `type`) VALUES
('Owner', 1, '11', 'bob@may.com', '1234', 'OWNER'),
('Owner', 2, '12', 'oop@may.com', '1234', 'OWNER'),
('Owner', 3, '123', 'op@may.com', '1234', 'OWNER'),
('Buyer', 4, '', 'ss.titanic199@gmail.com', '123Ss123_', 'BUYER'),
('Owner', 5, '1234', 'ss.titanic@gmail.com', '123456', 'OWNER');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
