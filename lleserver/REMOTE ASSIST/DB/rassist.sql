drop database if exists rassist;
Create database rassist;
Grant select ,insert, update, delete, execute on rassist.* to wally@'localhost' identified by 'to00ols';

Use rassist;

SET GLOBAL log_bin_trust_function_creators = 1; 

create function bitwise_or(s char(64), i bigint) returns bigint return (s | i);
create function bitwise_and(s char(64), i bigint) returns bigint return (s & i);

Create table Company
(
	Id			varchar(32) not null unique,
	CompanyId		varchar(64) not null unique,
	Name			varchar(256),
	LicenseType		bigint not null default 0,
	MaxConns		int unsigned not null default 0,
	ActiveFlags		bigint unsigned not null default 0,
	MiscFlags		bigint unsigned not null default 0,
	CreateDateTime		timestamp not null default now(),
	Locale			varchar(32),
	MiscInfo1		varchar(256),
	MiscInfo2		varchar(256),
	XRef			varchar(256),
	Primary key 		(Id)
);

Alter table Company add index (name);
Alter table Company add index (XRef);

Create table Technician
(
	Id			varchar(32) not null unique,
	CompanyId		varchar(64) not null,
	TechId			varchar(32) not null,
        TechCid		        varchar(16) not null unique,
	MaxConns		int unsigned not null default 0,
	AccessLevel		bigint unsigned not null default 0,
	Password		varchar(64) not null,
	Email			varchar(128),
	Name			varchar(64),
	Phone			varchar(32),
	Misc			varchar(256),
	XRef			varchar(256),
	CreateDateTime		timestamp default NOW(),
	Primary key 		(Id),
	Foreign Key		(CompanyId) references Company (CompanyId)
);

Alter table Technician add index (companyid, techcid);
Alter table Technician add index (companyid, techid);
Alter table Technician add index (companyid, name);
Alter table Technician add index (companyid, email);

Create table HDSession
(
	Id			varchar(32) not null unique,
	CompanyId		varchar(64) not null,
	TechCid			varchar(16) not null,
	Did			varchar(12) not null unique,
	Name			varchar(32),
	CreateDateTime		timestamp not null default NOW(),
	StartDateTime		timestamp default '1970-1-1 00:00:00',
	EndDateTime		timestamp default '1970-1-1 00:00:00',
	NumUses			int not null default 0,
	Primary key 		(Id),
	Foreign Key		(CompanyId) references Company (CompanyId),
	Foreign Key		(TechCid) references Technician (TechCid)
);

Alter table HDSession add index (CompanyId, TechCid, Did);
Alter table HDSession add index (CompanyId);
Alter table HDSession add index (Did);
Alter table HDSession add index (CompanyId, TechCid, StartDateTime, EndDateTime);


Create table Techlogin
(
	Id			varchar(32) not null unique,
	CompanyId		varchar(64) not null,
	TechCid			varchar(16) not null,
	LoginDateTime		timestamp not null default NOW(),
	EndDateTime		timestamp default '1970-1-1 00:00:00',
	Flags			bigint unsigned not null default 0,
	LoginId			double(14,14) unique,
	Primary key 		(Id),
	Foreign Key		(CompanyId) references Company (CompanyId),
	Foreign Key		(TechCid) references Technician (TechCid)
);

Alter table Techlogin add index (CompanyId, TechCid, EndDateTime);
Alter table Techlogin add index (TechCid, EndDateTime);
Alter table Techlogin add index (LoginId, EndDateTime);
Alter table Techlogin add index (CompanyId, EndDateTime);



Insert into Company (Id, CompanyId, Name, LicenseType, ActiveFlags, MiscFlags, MaxConns, Locale) values ('1', 'SiteAdmin', 'SiteAdmin', 128, 1, 0, '1', 'en');

Insert into Technician (Id, CompanyId, TechId, MaxConns, AccessLevel, Password) values ('1', 'SiteAdmin', 'SiteAdmin', 1, 65, '_hs_213bf44a16b8');
Insert into Technician (Id, CompanyId, TechId, Techcid, MaxConns, AccessLevel, Password) values ('100', 'SiteAdmin', 'ra!admin', 'w68e', 1, 3, '_hs_479afd725df7');
