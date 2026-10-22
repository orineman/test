-- MySQL dump 9.09

--

-- Host: localhost    Database: proto

---------------------------------------------------------

-- Server version	4.0.15-standard

create database lle;

-- user for icm to add reporting info to icmdata database

grant select,insert,update,delete on icmdata.* to icmuser@'%' identified by 'c4icmaccess';



-- user for portal site - this is defined in the tomcat server.xml file and must be set to match

grant select,insert,update,delete on lle.* to vincent@'%' identified by 'vincent';
grant select,insert,update,delete on lle.* to vincent@'db.ll2go.com' identified by 'vincent';

-- replace 'development110.infowave.com' with the machine's fully qualified domain name or ip if the name is not available