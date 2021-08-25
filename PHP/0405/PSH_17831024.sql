-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 18-04-11 23:01 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `kyungmin`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `shim`
--

CREATE TABLE IF NOT EXISTS `shim` (
  `id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `e_mail` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 테이블의 덤프 데이터 `shim`
--

INSERT INTO `shim` (`id`, `gender`, `name`, `e-mail`) VALUES
('tytyjacob', '여자', '박수현', 'tytyjacob@naver.com'),
('cgo2046', '여자', '최소연', 'cgo2046@naver.com'),
('yoon2618', '남자', '조성윤', 'yoon2618@naver.com '),
('qkqlqln', '여자', '박수빈', 'qkqlqln@naver.com'),
('rock6430', '여자', '최은지', 'rock6430@naver.com');
