#!/bin/sh

# llremoteassist.Report

CLASSNAME="$1"

TOMCAT_ROOT=/remasst/dist/tomcat
WEBAPP_ROOT="$TOMCAT_ROOT"/webapps/RAssist

# Add on extra jar files to CLASSPATH
for i in "$WEBAPP_ROOT"/WEB-INF/lib/*.jar; do
  CLASSPATH="$CLASSPATH":"$i"
done

CLASSPATH="$CLASSPATH":"$TOMCAT_ROOT"/common/lib/mysql-connector-java-3.1.12-bin.jar
CLASSPATH="$CLASSPATH":"$WEBAPP_ROOT"/WEB-INF/classes

_RUNJAVA="$JAVA_HOME"/bin/java

# echo "class name: $CLASSNAME"
# echo "class path: $CLASSPATH"

DB_PARAMETERS=-Dhibernate.connection.driver_class=com.mysql.jdbc.Driver
DB_PARAMETERS="$DB_PARAMETERS -Dhibernate.connection.url=jdbc:mysql://ra-sql.getremoteassist.com:3306/rassist?autoReconnect=true"
DB_PARAMETERS="$DB_PARAMETERS -Dhibernate.connection.username=wally"
DB_PARAMETERS="$DB_PARAMETERS -Dhibernate.connection.password=to00ols"
# echo "$DB_PARAMETERS"

echo "Running report..."
# parameters: -d = #days to go back  -l = locale (e.g., en or de)
exec "$_RUNJAVA" $DB_PARAMETERS -classpath "$CLASSPATH"  "llremoteassist.Report"  "-d7" "-len"


