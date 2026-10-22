mkdir ..\jars

mkdir c4p_classes
del /F /S /Q c4p_classes
del /Q filelist

dir /A /B /S *.java > filelist

"C:\Program Files\Java\jdk1.6.0_03\bin\javac" @options @filelist

copy com\infowave\c4p\datasource\dao\hibernate\*.xml c4p_classes\com\infowave\c4p\datasource\dao\hibernate
copy module_c4p.xml c4p_classes\com\infowave\c4p

cd c4p_classes
\sun\appserver\jdk\bin\jar cf c4p.jar com 
del /Q ..\..\jars\c4p.jar
copy c4p.jar ..\..\jars
cd ..