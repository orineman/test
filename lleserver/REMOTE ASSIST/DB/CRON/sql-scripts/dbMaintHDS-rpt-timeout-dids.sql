select * from HDSession where enddatetime < startdatetime and startdatetime < DATE_ADD(NOW(), INTERVAL -14 DAY) order by companyid, techcid, createdatetime;
