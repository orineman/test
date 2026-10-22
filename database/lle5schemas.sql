-- MySQL dump 9.10
--
-- Host: localhost    Database: lle5beta
-- ------------------------------------------------------
-- Server version	4.0.18-standard

--
-- Table structure for table `account`
--

CREATE TABLE account (
  id varchar(32) NOT NULL default '',
  username varchar(20) default NULL,
  firstname varchar(32) default NULL,
  lastname varchar(32) default NULL,
  email varchar(64) NOT NULL default '',
  locale varchar(20) NOT NULL default '',
  creationDate datetime NOT NULL default '0000-00-00 00:00:00',
  billingId varchar(250) default NULL,
  securityQuestion int(11) NOT NULL default '0',
  securityAnswer varchar(40) NOT NULL default '',
  regCode varchar(100) default NULL,
  resellerId varchar(32) NOT NULL default '',
  CountryCode char(2) default 'US',
  defaultDesktop varchar(32) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY username (username),
  KEY resellerId (resellerId),
  KEY index_creationDate (creationDate)
) TYPE=MyISAM;

--
-- Table structure for table `authentication`
--

CREATE TABLE authentication (
  id varchar(32) NOT NULL default '',
  username varchar(20) default NULL,
  password varchar(20) NOT NULL default '',
  authtype varchar(250) NOT NULL default '',
  rolename varchar(10) NOT NULL default '',
  accountId varchar(32) default NULL,
  PRIMARY KEY  (id),
  KEY username_idx (username),
  KEY accountid_idx (accountId)
) TYPE=MyISAM;

--
-- Table structure for table `desktop`
--

CREATE TABLE desktop (
  id varchar(32) NOT NULL default '',
  did varchar(10) default NULL,
  name varchar(20) default NULL,
  owner varchar(20) default NULL,
  licenseSerialNumber varchar(250) default NULL,
  expiryDate datetime default NULL,
  creationDate datetime default NULL,
  visible tinyint(1) default NULL,
  licenseHistory text,
  registrationCode varchar(100) default NULL,
  softwareVersion varchar(15) default NULL,
  accountId varchar(32) default NULL,
  licenseId varchar(32) default NULL,
  hostPublicKey text,
  serverPrivateKey text,
  ssid text,
  PRIMARY KEY  (id),
  UNIQUE KEY did (did),
  KEY licenseId (licenseId),
  KEY accountId (accountId),
  KEY expiryDate (expiryDate)
) TYPE=MyISAM;

--
-- Table structure for table `desktopaccess`
--

CREATE TABLE desktopaccess (
  accountId varchar(32) NOT NULL default '',
  desktopId varchar(32) NOT NULL default '',
  PRIMARY KEY  (desktopId,accountId),
  KEY desktopId (desktopId),
  KEY accountId (accountId)
) TYPE=MyISAM;

--
-- Table structure for table `desktopnames`
--

CREATE TABLE desktopnames (
  id varchar(32) NOT NULL default '',
  isHidden tinyint(1) NOT NULL default '0',
  desktop_names varchar(32) NOT NULL default '',
  desktop_value varchar(255) NOT NULL default '',
  PRIMARY KEY  (id,desktop_names),
  KEY FK3C6DF8AC59D2931A (id),
  KEY FK3C6DF8AC3538D153 (desktop_names)
) TYPE=MyISAM;

--
-- Table structure for table `icm`
--

CREATE TABLE icm (
  id varchar(32) NOT NULL default '',
  name varchar(32) NOT NULL default '',
  protocol varchar(5) NOT NULL default '',
  hostname varchar(80) NOT NULL default '',
  port int(11) default NULL,
  statusURL varchar(80) NOT NULL default '',
  appListURL varchar(80) NOT NULL default '',
  logoutURL varchar(80) NOT NULL default '',
  appPattern varchar(80) NOT NULL default '',
  username varchar(16) NOT NULL default '',
  password varchar(32) NOT NULL default '',
  PRIMARY KEY  (id)
) TYPE=MyISAM;

--
-- Table structure for table `jauth`
--

CREATE TABLE jauth (
  id varchar(32) NOT NULL default '',
  username varchar(20) default NULL,
  password varchar(20) NOT NULL default '',
  authtype varchar(250) NOT NULL default '',
  rolename varchar(10) NOT NULL default '',
  accountId varchar(32) default NULL
) TYPE=MyISAM;

--
-- Table structure for table `license`
--

CREATE TABLE license (
  id varchar(32) NOT NULL default '',
  code varchar(100) default NULL,
  duration int(11) default NULL,
  numUses int(11) default NULL,
  maxUses int(11) default NULL,
  revoked tinyint(1) default NULL,
  regCode tinyint(1) default NULL,
  firstUseDate datetime default NULL,
  resellerId varchar(32) NOT NULL default '',
  accountId varchar(32) default NULL,
  RC char(1) default 'N',
  rcaccountid varchar(32) default NULL,
  licensetype int(11) default '0',
  PRIMARY KEY  (id),
  KEY resellerId (resellerId),
  KEY accountId (accountId)
) TYPE=MyISAM;

--
-- Table structure for table `lle5_account`
--

CREATE TABLE lle5_account (
  id varchar(32) NOT NULL default '',
  username varchar(20) default NULL,
  firstname varchar(32) default NULL,
  lastname varchar(32) default NULL,
  email varchar(64) NOT NULL default '',
  locale varchar(20) NOT NULL default '',
  creationDate datetime NOT NULL default '0000-00-00 00:00:00',
  billingId varchar(250) default NULL,
  securityQuestion int(11) NOT NULL default '0',
  securityAnswer varchar(40) NOT NULL default '',
  regCode varchar(100) default NULL,
  resellerId varchar(32) NOT NULL default '',
  CountryCode char(2) default 'US',
  defaultDesktop varchar(32) default NULL
) TYPE=MyISAM;

--
-- Table structure for table `lle5_active_license`
--

CREATE TABLE lle5_active_license (
  id varchar(32) NOT NULL default '',
  code varchar(100) default NULL,
  duration int(11) default NULL,
  numUses int(11) default NULL,
  maxUses int(11) default NULL,
  revoked tinyint(1) default NULL,
  regCode tinyint(1) default NULL,
  firstUseDate datetime default NULL,
  resellerId varchar(32) NOT NULL default '',
  accountId varchar(32) default NULL,
  RC char(1) default 'N',
  rcaccountid varchar(32) default NULL,
  licensetype int(11) default '0',
  PRIMARY KEY  (id),
  KEY resellerId (resellerId),
  KEY accountId (accountId),
  KEY license_code (code)
) TYPE=MyISAM;

--
-- Table structure for table `lle5_authentication`
--

CREATE TABLE lle5_authentication (
  id varchar(32) NOT NULL default '',
  username varchar(20) default NULL,
  password varchar(20) NOT NULL default '',
  authtype varchar(250) NOT NULL default '',
  rolename varchar(10) NOT NULL default '',
  accountId varchar(32) default NULL
) TYPE=MyISAM;

--
-- Table structure for table `lle5_license`
--

CREATE TABLE lle5_license (
  id varchar(32) NOT NULL default '',
  code varchar(100) default NULL,
  duration int(11) default NULL,
  numUses int(11) default NULL,
  maxUses int(11) default NULL,
  revoked tinyint(1) default NULL,
  regCode tinyint(1) default NULL,
  firstUseDate datetime default NULL,
  resellerId varchar(32) NOT NULL default '',
  accountId varchar(32) default NULL,
  RC char(1) default 'N',
  rcaccountid varchar(32) default NULL,
  licensetype int(11) default '0'
) TYPE=MyISAM;

--
-- Table structure for table `rcusage`
--

CREATE TABLE rcusage (
  id varchar(32) NOT NULL default '',
  did varchar(128) NOT NULL default '',
  licenseSerialNumber varchar(250) default NULL,
  expiryDate datetime default NULL,
  windowExpiryDate datetime default NULL,
  bytes bigint(20) NOT NULL default '0',
  serverSeconds int(11) NOT NULL default '0',
  clientSeconds int(11) NOT NULL default '0',
  windowlimit int(11) NOT NULL default '0',
  PRIMARY KEY  (id),
  UNIQUE KEY did (did)
) TYPE=MyISAM;

--
-- Table structure for table `reseller`
--

CREATE TABLE reseller (
  id varchar(32) NOT NULL default '',
  code varchar(250) default NULL,
  name varchar(20) default NULL,
  hostname varchar(150) default NULL,
  expiryUrl varchar(250) default NULL,
  defaultLocale varchar(32) default NULL,
  nbSecurityQuestions int(11) default NULL,
  licenseCodeFormat varchar(32) NOT NULL default '',
  demoDuration int(11) default NULL,
  passwordEncoder varchar(250) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY code (code)
) TYPE=MyISAM;

