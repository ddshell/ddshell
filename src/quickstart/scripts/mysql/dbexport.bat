@echo off

call "%~dp0../setenv"

"%MYSQL_HOME%\bin\mysqldump" --hex-blob %MYSQL_LOGON% ddshell_quickstart > %~dp0data\ddshell_quickstart.sql

pause
