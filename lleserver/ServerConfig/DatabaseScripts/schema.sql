drop table license;
drop table desktopaccess;
drop table icm;
drop table account;
drop table reseller;
drop table authentication;
drop table desktop;
create table license (
   id VARCHAR(32) not null,
   code varchar(100),
   duration INTEGER,
   numUses INTEGER,
   maxUses INTEGER,
   revoked BIT,
   regCode BIT,
   firstUseDate DATETIME,
   resellerId VARCHAR(32) not null,
   accountId VARCHAR(32),
   primary key (id)
);
create table desktopaccess (
   accountId VARCHAR(32) not null,
   desktopId VARCHAR(32) not null,
   primary key (desktopId, accountId)
);
create table icm (
   id VARCHAR(32) not null,
   name varchar(32) not null,
   protocol varchar(5) not null,
   hostname varchar(80) not null,
   port INTEGER,
   statusURL varchar(80) not null,
   appListURL varchar(80) not null,
   logoutURL varchar(80) not null,
   appPattern varchar(80) not null,
   username varchar(16) not null,
   password varchar(32) not null,
   primary key (id)
);
create table account (
   id VARCHAR(32) not null,
   username varchar(20) unique,
   firstname varchar(32),
   lastname varchar(32),
   email varchar(64) not null,
   locale varchar(20) not null,
   creationDate DATETIME not null,
   billingId varchar(250),
   securityQuestion INTEGER not null,
   securityAnswer varchar(40) not null,
   regCode varchar(100),
   resellerId VARCHAR(32) not null,
   primary key (id)
);
create table reseller (
   id VARCHAR(32) not null,
   code varchar(250) unique,
   name varchar(20),
   hostname varchar(150),
   expiryUrl varchar(250),
   defaultLocale varchar(32),
   nbSecurityQuestions INTEGER,
   licenseCodeFormat varchar(32) not null,
   demoDuration INTEGER,
   passwordEncoder varchar(250) not null,
   primary key (id)
);
create table authentication (
   id VARCHAR(32) not null,
   username varchar(20) unique,
   password varchar(20) not null,
   authtype varchar(250) not null,
   rolename varchar(10) not null,
   accountId VARCHAR(32),
   primary key (id)
);
create table desktop (
   id VARCHAR(32) not null,
   did varchar(128) not null unique,
   name varchar(20),
   owner varchar(20),
   licenseSerialNumber varchar(250),
   expiryDate DATETIME,
   creationDate DATETIME,
   visible BIT,
   licenseHistory text,
   registrationCode varchar(100),
   softwareVersion varchar(10),
   accountId VARCHAR(32),
   licenseId VARCHAR(32),
   primary key (id)
);
alter table license add index (resellerId), add constraint FK9F084418F1DA02D foreign key (resellerId) references reseller (id);
alter table license add index (accountId), add constraint FK9F084419319BC28 foreign key (accountId) references account (id);
alter table desktopaccess add index (desktopId), add constraint FK3B39C080672C0257 foreign key (desktopId) references desktop (id);
alter table desktopaccess add index (accountId), add constraint FK3B39C0809319BC28 foreign key (accountId) references account (id);
alter table account add index (resellerId), add constraint FKB9D38A2D8F1DA02D foreign key (resellerId) references reseller (id);
alter table authentication add index (accountId), add constraint FK19A7E2789319BC28 foreign key (accountId) references account (id);
alter table desktop add index (licenseId), add constraint FK5CCF901C4FE0813C foreign key (licenseId) references license (id);
alter table desktop add index (accountId), add constraint FK5CCF901C9319BC28 foreign key (accountId) references account (id);
