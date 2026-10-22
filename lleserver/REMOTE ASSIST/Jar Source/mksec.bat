
mkdir c4psecurity_classes
del /F /S /Q c4psecurity_classes


\sun\appserver\jdk\bin\javac -d c4psecurity_classes -classpath c4psecurity;..\ref-jar\catalina.jar;..\ref-jar\servlet.jar;..\ref-jar\commons-logging.jar;..\ref-jar\commons-dbcp-1.2.1.jar  c4p\com\infowave\c4p\laplink\security\PasswordEncoderImpl.java
\sun\appserver\jdk\bin\javac -d c4psecurity_classes -classpath c4psecurity;..\ref-jar\catalina.jar;..\ref-jar\servlet.jar;..\ref-jar\commons-logging.jar;..\ref-jar\commons-dbcp-1.2.1.jar  c4p\com\infowave\c4p\util\StringManager.java
\sun\appserver\jdk\bin\javac -d c4psecurity_classes -classpath c4psecurity;c4psecurity_classes;..\ref-jar\catalina.jar;..\ref-jar\servlet.jar;..\ref-jar\commons-logging.jar;..\ref-jar\commons-dbcp-1.2.1.jar  c4psecurity\com\infowave\c4p\laplink\web\security\*.java

cd c4psecurity_classes
del /Q c4psecurity.jar
\sun\appserver\jdk\bin\jar cf c4psecurity.jar com
cd ..

