update HDSession set enddatetime = NOW() where enddatetime < startdatetime and startdatetime < DATE_ADD(NOW(), INTERVAL -14 DAY);
