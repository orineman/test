use rassist;

LOCK TABLES `Company` WRITE;
Insert into Company (Id, CompanyId, Name, LicenseType, ActiveFlags, MiscFlags, MaxConns, Locale) values ('2', 'laplink', 'LapLink', 4, 1, 0, '1', 'en');
INSERT INTO `Company` VALUES ('3','wallyworld','Wallys World',4,5,1,0,'2006-04-04 22:12:27','en',NULL,NULL,NULL),('4','biter','Biter Ranch',4,10,1,0,'2006-04-04 22:12:27','en',NULL,NULL,NULL);
UNLOCK TABLES;

LOCK TABLES `Technician` WRITE;
INSERT INTO `Technician` VALUES ('2','laplink','jack','q2q2',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('3','laplink','jodi','1234',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('4','wallyworld','wally','wwww',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('5','wallyworld','alice','aaaa',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('6','wallyworld','asok','0000',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('7','wallyworld','phb','0666',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('8','wallyworld','dilbert','qwe2',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('9','wallyworld','dogbert','woof',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('10','biter','flea0','fff0',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('11','biter','flea1','fff1',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('12','biter','flea2','fff2',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('13','biter','flea3','fff3',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('14','biter','flea4','fff4',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('15','biter','flea5','fff5',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('16','biter','flea6','fff6',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('17','biter','flea7','fff7',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('18','biter','flea8','fff8',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('19','biter','flea9','fff9',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('20','biter','flea10','ff10',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('21','biter','flea11','ff11',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
INSERT INTO `Technician` VALUES ('22','biter','flea12','ff12',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('23','biter','flea13','ff13',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27'),('24','biter','flea14','ff14',1,65,'_hs_213bf44a16b8',NULL,NULL,NULL,NULL,NULL,'2006-04-04 22:12:27');
UNLOCK TABLES;
