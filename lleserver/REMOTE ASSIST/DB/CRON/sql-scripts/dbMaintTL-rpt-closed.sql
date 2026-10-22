select * from Techlogin where Enddatetime < DATE_ADD( NOW(), INTERVAL -1 MONTH ) AND enddatetime != '1970-01-01 00:00:00' order by companyid, techcid, logindatetime;

