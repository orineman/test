#/bin/bash

HERE=`pwd`
ARCHIVE_POSTFIX=`date +%m%d%y-%s`
ARCHIVE_DIR=/root/jodie/update.$ARCHIVE_POSTFIX
ARCHIVE_PORTAL=/lle/system/portal.$ARCHIVE_POSTFIX
DONE=1

# Remove the copy overwrite prompt
unalias cp

# Copy the update to an archive directory
mkdir $ARCHIVE_DIR
cp /root/update/* $ARCHIVE_DIR
	
# Make sure the zip file in where we need it to be
if test ! -f /root/update/client*.zip || test ! -f /root/update/Server*.zip
then
	echo "**************** UPDATE FILE NOT IN /root/update -- ABORTING *********************" 1>&2
else
	# Stop the server
	/lle/stoplle.sh
	
	# Make a backup copy of the server
	cp -R /lle/system/portal $ARCHIVE_PORTAL

	# Make a backup copy of the laplink tomcat jar files
	cp /lle/dist/tomcat/lib/c4plaplink.jar  /lle/dist/tomcat/lib/c4plaplink.jar.$ARCHIVE_POSTFIX
	cp /lle/dist/tomcat/lib/c4psecurity.jar  /lle/dist/tomcat/lib/c4psecurity.jar.$ARCHIVE_POSTFIX
	
	# change to where the update files should be
	cd /root/update
	
	# unpack the server files
	mkdir server-temp
	cd server-temp
	unzip ../Server*.zip
	cd ..
	
	# unpack the client files
	mkdir client-temp
	cd client-temp
	unzip ../client*.zip
	cd ..
	
	# Copy all the client files into the server area
	cp -R /root/update/client-temp/* /lle/system/portal

	# Overwrite the server's version file from server zip
	cp -R /root/update/server-temp/WEB-INF/jsp/* /lle/system/portal/WEB-INF/jsp
	
	# Restore the WEB-INF/classes config files that are different
	cp /root/jodie/WEB-INF/classes/* /lle/system/portal/WEB-INF/classes
	
	# Restore the jar file collection needed for this configuration
	# Note: this does not update c4p.jar, pda.jar or llcenter.jar
	rm -rf /lle/system/portal/WEB-INF/lib/*
	cp -R /root/jodie/WEB-INF/lib/* /lle/system/portal/WEB-INF/lib
	
	# Update the c4p.jar file
	cd /lle/system/portal/WEB-INF/lib
	rm -rf jtemp
	mkdir jtemp
	cd jtemp
	cp /root/update/server-temp/c4p.jar .
	jar xvf c4p.jar
	cp /root/jodie/hibernate-xml/* /lle/system/portal/WEB-INF/lib/jtemp/com/infowave/c4p/datasource/dao/hibernate
	mv c4p.jar c4p.jar.$ARCHIVE_POSTFIX
	jar cvf c4p.jar com
	cp c4p.jar /lle/system/portal/WEB-INF/lib

	# update the other laplink jars in WEB-INF/lib
	cp /root/update/server-temp/pda.jar /lle/system/portal/WEB-INF/lib
	cp /root/update/server-temp/llcenter.jar /lle/system/portal/WEB-INF/lib
	
	# copy the new laplink server jars into the tomcat directory
	cp /root/update/server-temp/c4plaplink.jar  /lle/dist/tomcat/lib
	cp /root/update/server-temp/c4psecurity.jar  /lle/dist/tomcat/lib

	
	# Restart the server
	cd /root
	/lle/startlle.sh
	
	DONE=0
fi

# Set the overwrite prompt back on the copy command
alias cp='cp -i'

# Copy any output files 
cp $HERE/*.err $ARCHIVE_DIR
cp $HERE/*.out $ARCHIVE_DIR

# Cleanup the staging area
rm -rf /root/update/*
	
exit $DONE
