@echo off

call "%~dp0..\..\..\etc\setenv"
cd /d "%~dp0.."

call gradle clean cleanEclipse wsinit eclipse

set PRGDIR=%cd%
"%ECLIPSE_HOME%/eclipse.exe" -nosplash -data "%cd%" -vm "%JAVA_HOME%/bin/javaw.exe" -application org.eclipse.ant.core.antRunner -buildfile "%~dp0eclipse.xml"

pause