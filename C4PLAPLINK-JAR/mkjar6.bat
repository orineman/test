mkdir ..\jars

mkdir c4plaplink_classes
del /F /S /Q c4plaplink_classes
del /Q filelist

dir /A /B /S *.java > filelist

"C:\Program Files\Java\jdk1.6.0_03\bin\javac" @options @filelist
copy module_c4plaplink.xml c4plaplink_classes

cd c4plaplink_classes
\sun\appserver\jdk\bin\jar cf c4plaplink.jar com *.xml
del /Q ..\..\jars\c4plaplink.jar 
copy c4plaplink.jar ..\..\jars
cd ..