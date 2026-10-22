mkdir ..\jars

mkdir llcenter_classes
del /F /S /Q llcenter_classes
del /Q filelist

dir /A /B /S *.java > filelist

"C:\Program Files\Java\jdk1.6.0_03\bin\javac" @options @filelist
copy module_llcenter.xml llcenter_classes

cd llcenter_classes
\sun\appserver\jdk\bin\jar cf llcenter.jar llcenter *.xml
del /Q ..\..\jars\llcenter.jar 
copy llcenter.jar ..\..\jars
cd ..