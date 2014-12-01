@echo off

call "%~dp0..\..\..\..\etc\setenv"

"%ORA_HOME%\bin\imp" ddshell/ddshell fromuser=ddshell touser=ddshell file=%~dp0data\ddshell_quickstart.dmp

pause


