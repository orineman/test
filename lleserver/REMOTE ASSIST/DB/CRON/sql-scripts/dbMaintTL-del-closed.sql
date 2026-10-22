delete from Techlogin where Enddatetime < DATE_ADD( NOW(), INTERVAL -1 MONTH ) and Enddatetime != '1970-01-01 00:00:00';

