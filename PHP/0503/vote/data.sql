-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 15-05-17 17:29 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `vote`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `poll`
--

CREATE TABLE IF NOT EXISTS `poll` (
  `reading` int(11) NOT NULL,
  `swimming` int(11) NOT NULL,
  `golf` int(11) NOT NULL,
  `fishing` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 테이블의 덤프 데이터 `poll`
--

INSERT INTO `poll` (`reading`, `swimming`, `golf`, `fishing`) VALUES
(0, 0, 0, 0);
