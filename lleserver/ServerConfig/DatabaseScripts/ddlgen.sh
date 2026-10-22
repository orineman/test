#!/bin/sh



export _root=/home/vincent/dev/portal



export _hibernate_home=/home/vincent/tools/hibernate-2.0.3

export _cp=${_hibernate_home}/hibernate2.jar

export _cp=${_cp}:${_hibernate_home}/lib/dom4j.jar

export _cp=${_cp}:${_hibernate_home}/lib/commons-lang.jar

export _cp=${_cp}:${_hibernate_home}/lib/commons-logging.jar

export _cp=${_cp}:${_hibernate_home}/lib/commons-beanutils.jar

export _cp=${_cp}:${_hibernate_home}/lib/commons-collections.jar

export _cp=${_cp}:${_hibernate_home}/lib/odmg.jar



export _cp=${_cp}:/home/vincent/dev/portal/WEB-INF/classes





echo ${_cp}



export _files=${_root}/WEB-INF/classes/Account.hbm.xml

export _files="${_files} ${_root}/WEB-INF/classes/Reseller.hbm.xml"

export _files="${_files} ${_root}/WEB-INF/classes/Desktop.hbm.xml"











java -cp ${_cp} net.sf.hibernate.tool.hbm2ddl.SchemaExport \

          --text --format --delimiter=";" --output=schema.ddl ${_files}





#java -cp ${_cp} net.sf.hibernate.tool.hbm2ddl.SchemaExport \

#          --text --output=schema.ddl \

#          /home/vincent/dev/portal/WEB-INF/classes/Cat.hbm.xml