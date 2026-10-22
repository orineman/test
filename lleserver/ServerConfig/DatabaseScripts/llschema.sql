-- MySQL dump 9.09
--
-- Host: localhost    Database: proto
---------------------------------------------------------
-- Server version	4.0.15-standard

--
-- Current Database: icmdata
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ icmdata;

USE icmdata;

drop table LL_GOLDUSAGE;
drop table LL_RCUSAGE;

--
-- Table structure for table `LL_GOLDUSAGE`
--

CREATE TABLE LL_GOLDUSAGE (
  DEVICEID varchar(8) NOT NULL default '',
  CLIENTSECONDS int(10) default '0',
  SERVERSECONDS int(10) default '0',
  BYTES bigint(20) default '0',
  CREATEDATE datetime NOT NULL default '0000-00-00 00:00:00',
  UPDATEDATE datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (DEVICEID)
) TYPE=MyISAM;

--
-- Table structure for table `LL_RCUSAGE`
--

CREATE TABLE LL_RCUSAGE (
  DEVICEID varchar(8) NOT NULL default '',
  CLIENTSECONDS int(10) default '0',
  SERVERSECONDS int(10) default '0',
  BYTES bigint(20) default '0',
  CREATEDATE datetime NOT NULL default '0000-00-00 00:00:00',
  UPDATEDATE datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (DEVICEID)
) TYPE=MyISAM;
