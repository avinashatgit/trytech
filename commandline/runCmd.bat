setlocal
cd /d %~dp0
cd ..
java -cp libs\*;bin org.testng.TestNG testng.xml > log.txt
