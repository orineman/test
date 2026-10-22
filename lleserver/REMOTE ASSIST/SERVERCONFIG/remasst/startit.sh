echo Starting Apache

/remasst/dist/httpd/bin/apachectl start

#sleep 5
#
#echo Starting MySQL
#
#cd /remasst/dist/mysql
#bin/mysqld_safe --user=mysql &

sleep 5

echo Starting Tomcat

#rm -f /remasst/dist/tomcat/webapps/RAssist/WEB-INF/proto.*

#su - tomcat -c /lle/dist/tomcat/bin/startup.sh
/remasst/dist/tomcat/bin/startup.sh

