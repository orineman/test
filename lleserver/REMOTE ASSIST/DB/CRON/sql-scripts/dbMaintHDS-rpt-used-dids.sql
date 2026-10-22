select * from HDSession where enddatetime < DATE_ADD( NOW(), INTERVAL -1 MONTH ) and enddatetime != '1970-01-01 00:00:00' order by companyid, techcid, createdatetime;
