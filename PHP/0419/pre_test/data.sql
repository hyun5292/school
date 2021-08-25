-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 16-06-17 01:52 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `travel`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `place`
--

CREATE TABLE IF NOT EXISTS `place` (
  `order` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `pic` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`order`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- 테이블의 덤프 데이터 `place`
--

INSERT INTO `place` (`order`, `location`, `pic`, `price`) VALUES
(1, '방콕', 'ban.jpg', 129000),
(2, '파타야', 'pataya.jpg', 249000),
(3, '푸켓', 'puket.jpg', 339000),
(4, '치앙마이', 'chiyang.jpg', 474000);
