select * from Techlogin where logindatetime < DATE_ADD( NOW(), INTERVAL -7 DAY ) AND enddatetime = '1970-01-01 00:00:00' order by companyid, techcid, logindatetime;

