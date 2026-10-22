update Techlogin set flags = '2', enddatetime = NOW() where logindatetime < DATE_ADD( NOW(), INTERVAL -7 DAY ) and Enddatetime = '1970-01-01 00:00:00';

