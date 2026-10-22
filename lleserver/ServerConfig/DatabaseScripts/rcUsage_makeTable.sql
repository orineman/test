--drop table rcusage;

create table rcusage
(
id varchar(32) primary key,
did varchar(128) not null unique,
licenseSerialNumber varchar(250),
expiryDate datetime,
windowExpiryDate datetime,
bytes bigint not null default 0,
serverSeconds integer not null default 0,
clientSeconds integer not null default 0,
windowlimit integer not null default 0
)
