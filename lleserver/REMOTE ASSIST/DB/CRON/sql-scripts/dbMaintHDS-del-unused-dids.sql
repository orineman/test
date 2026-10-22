delete from HDSession where enddatetime = '1970-01-01 00:00:00' and startdatetime = '1970-01-01 00:00:00' and createdatetime < DATE_ADD(NOW(), INTERVAL -14 DAY);
