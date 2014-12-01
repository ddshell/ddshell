@echo off

call "%~dp0..\..\..\..\etc\setenv"

"%ORA_HOME%\bin\exp" ddshell/ddshell owner=ddshell file=%~dp0data\ddshell_quickstart.dmp

pause


