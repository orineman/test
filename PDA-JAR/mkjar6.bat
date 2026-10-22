mkdir ..\jars

mkdir pda_classes
del /F /S /Q pda_classes
del /Q filelist

dir /A /B /S *.java > filelist

"C:\Program Files\Java\jdk1.6.0_03\bin\javac" @options @filelist
copy module_pda.xml pda_classes\com\infowave\pda

cd pda_classes
\sun\appserver\jdk\bin\jar cf pda.jar com pda
del /Q ..\..\jars\pda.jar 
copy pda.jar ..\..\jars
cd ..