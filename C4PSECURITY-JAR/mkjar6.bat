mkdir ..\jars

mkdir c4psecurity_classes
del /F /S /Q c4psecurity_classes
del /Q filelist

dir /A /B /S *.java > filelist

"C:\Program Files\Java\jdk1.6.0_03\bin\javac" @options @filelist
copy module_c4psecurity.xml c4psecurity_classes

cd c4psecurity_classes
\sun\appserver\jdk\bin\jar cf c4psecurity.jar com *.xml
del /Q ..\..\jars\c4psecurity.jar 
copy c4psecurity.jar ..\..\jars
cd ..