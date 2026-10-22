analyze table Company;
optimize table Company;
analyze table HDSession;
optimize table HDSession;
analyze table Techlogin;
optimize table Techlogin;
analyze table Technician;
optimize table Technician;
show status like '%qcache%';
flush query cache;
show status like '%qcache%';

