-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 18-04-08 19:47 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `first`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) NOT NULL,
  `dept` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `salary` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=15 ;

--
-- 테이블의 덤프 데이터 `payment`
--

INSERT INTO `payment` (`id`, `name`, `age`, `dept`, `salary`) VALUES
(1, '심일식', 22, '전산부', 2300),
(2, '이진근', 44, '회계부', 4500),
(3, '조성택', 12, '전산부', 3000),
(4, '송민중', 22, '경리부', 9500);
