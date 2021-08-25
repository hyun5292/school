-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 18-05-20 22:58 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `membership`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `p_name` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `pwd1` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `birth1` int(11) DEFAULT NULL,
  `birth2` int(11) DEFAULT NULL,
  `birth3` int(11) DEFAULT NULL,
  `birth4` int(11) DEFAULT NULL,
  `phone1` text COLLATE utf8_unicode_ci,
  `phone2` text COLLATE utf8_unicode_ci,
  `phone3` text COLLATE utf8_unicode_ci,
  `email` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `joinDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- 테이블의 덤프 데이터 `member`
--

