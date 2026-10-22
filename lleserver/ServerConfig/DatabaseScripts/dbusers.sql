-- user for icm to add reporting info to icmdata database
grant select,insert,update,delete on icmdata.* to icmuser@'%' identified by 'c4icmaccess';

-- user for portal site - this is defined in the tomcat server.xml file and must be set to match
grant select,insert,update,delete on proto.* to vincent@'%' identified by 'vincent';

-- user for SCM to access auth database
grant select on proto.authentication to scmuser@'%' identified by 'c4scmaccess';
grant select on proto.desktop to scmuser@'%' identified by 'c4scmaccess';
