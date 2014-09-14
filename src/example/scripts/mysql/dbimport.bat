@echo off

call "%~dp0../setenv"

"%MYSQL_HOME%\bin\mysql" %MYSQL_LOGON% ddshell_example < %~dp0data\ddshell_example.sql

pause
