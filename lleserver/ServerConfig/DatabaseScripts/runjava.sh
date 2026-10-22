#!/bin/sh
CLASSNAME="$1"

WEBAPP_ROOT=/c4/system/portal
# Add on extra jar files to CLASSPATH
for i in "$WEBAPP_ROOT"/WEB-INF/lib/*.jar; do
  CLASSPATH="$CLASSPATH":"$i"
done

CLASSPATH="$CLASSPATH":"$WEBAPP_ROOT"/WEB-INF/classes

_RUNJAVA="$JAVA_HOME"/bin/java

echo "class name: $CLASSNAME"
echo "class path: $CLASSPATH"

DB_PARAMETERS=-Dhibernate.connection.driver_class=com.mysql.jdbc.Driver
DB_PARAMETERS="$DB_PARAMETERS -Dhibernate.connection.url=jdbc:mysql://localhost/proto?autoReconnect=true"
DB_PARAMETERS="$DB_PARAMETERS -Dhibernate.connection.username=vincent"
DB_PARAMETERS="$DB_PARAMETERS -Dhibernate.connection.password=vincent"
echo "$DB_PARAMETERS"

# exec "$_RUNJAVA" -Dhibernate.connection.url=jdbc:mysql://localhost/proto?autoReconnect=true -Dhibernate.connection.username=vincent -Dhibernate.connection.password=vincent -classpath "$CLASSPATH"  "$@"
exec "$_RUNJAVA" $DB_PARAMETERS -classpath "$CLASSPATH"  "$@"


