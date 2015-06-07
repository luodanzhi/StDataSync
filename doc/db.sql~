CREATE DATABASE `stock` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `st_his` (
  `st_his_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT 'stock history data',
  `code` varchar(8) NOT NULL,
  `date` date NOT NULL,
  `open` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `volume` double DEFAULT NULL,
  `adj` double DEFAULT NULL COMMENT 'last transission value',
  PRIMARY KEY (`st_his_seq`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `st` (
  `code` varchar(8) NOT NULL,
  `name` varchar(8) NOT NULL,
  `last_date` date NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
