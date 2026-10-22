select * from HDSession where enddatetime < startdatetime and startdatetime < DATE_ADD( NOW(), INTERVAL -1 WEEK) order by companyid, techcid, createdatetime;  
