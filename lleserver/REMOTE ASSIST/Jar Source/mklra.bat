mkdir llremoteassist_classes
del /F /S /Q llremoteassist_classes

\sun\appserver\jdk\bin\javac -d llremoteassist_classes -classpath llremoteassist;c4p_classes\c4p.jar;..\ref-jar\commons-logging.jar;..\ref-jar\servlet.jar  llremoteassist\*.java

cd llremoteassist_classes
del /Q llremoteassist.jar
\sun\appserver\jdk\bin\jar cf llremoteassist.jar llremoteassist
cd ..
