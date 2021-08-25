-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 15-12-09 01:26 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `shopping`
-- 

-- --------------------------------------------------------

--
-- 테이블 구조 `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `number` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `cookie` char(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unit` int(11) NOT NULL,
  `name` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `putdate` datetime DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 테이블의 덤프 데이터 `cart`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `p_id` int(11) NOT NULL,
  `name` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  `product` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `detail` text COLLATE utf8_unicode_ci,
  `pic` char(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 테이블의 덤프 데이터 `item`
--

INSERT INTO `item` (`p_id`, `name`, `price`, `product`, `detail`, `pic`) VALUES
(10001, '겨울신발', 20000, '화승', '매우 따뜻함', '../img/outdoor/10001.jpg'),
(10002, '온풍기', 53000, '신일', '빨리 더워짐', '../img/outdoor/10002.jpg'),
(10003, '쿠션', 15600, '몰라', '배에 근육이생겨 더워짐', '../img/outdoor/10003.jpg'),
(10004, '다운자켓', 60000, '나이키', '엄청 몸을 덥게함', '../img/outdoor/10004.jpg'),
(10005, '부스터', 15000, '신강', '음식을 먹을 수 있음', '../img/outdoor/10005.jpg'),
(10006, '운동신발', 23000, '제일', '겨울용 온둥화', '../img/outdoor/10006.jpg'),
(10007, '속옷', 12000, '가을', '그냥 속옷임', '../img/outdoor/10007.jpg'),
(10008, '장갑', 8000, '회사', '손이 따뜻해짐', '../img/outdoor/10008.jpg'),
(10009, '미끄럼방지', 9000, '에베레스트', '자빠져 죽을 염려없음', '../img/outdoor/10009.jpg'),
(10010, '실내화', 5000, '화승', '실내에서 엄청 따스함', '../img/outdoor/10010.jpg'),
(10011, '남방', 15000, '제일', '패션어블한 윗도리', '../img/outdoor/10011.jpg'),
(10012, '목도리', 7600, '제일', '목이 따스함', '../img/outdoor/10012.jpg'),
(10013, '겨울쟈켓', 78000, '몰라', '귀찮다', '../img/outdoor/10013.jpg'),
(10014, '털쟈켓', 120000, '경민', '얼어 죽지는 않음', '../img/outdoor/10014.jpg'),
(20001, '큰냄비', 15000, '기획', '사골국물 해 먹기 딱 좋음', '../img/nambi/20001.jpg'),
(20002, '압력밥솥', 160000, '쿠첸', '엄청 잘됨', '../img/nambi/20002.jpg'),
(20003, '뚝배기', 6000, '망함', '된장찌게 부글부글', '../img/nambi/20003.jpg'),
(20004, '냄비', 45000, '기승', '라면해 먹기 딱 좋음', '../img/nambi/20004.jpg'),
(20005, '접시', 5000, '때깔', '보기에 좋음', '../img/nambi/20005.jpg');
