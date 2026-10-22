
#cd /remasst/dist/mysql
#bin/mysqladmin -u root shutdown
#
#sleep 5

#su - tomcat -c /remasst/dist/tomcat/bin/shutdown.sh
/remasst/dist/tomcat/bin/shutdown.sh

sleep 5

/remasst/dist/httpd/bin/apachectl stop

sleep 5

#su - tomcat -c /remasst/dist/tomcat/bin/shutdown.sh
/remasst/dist/tomcat/bin/shutdown.sh

sleep 5

/remasst/dist/httpd/bin/apachectl stop

