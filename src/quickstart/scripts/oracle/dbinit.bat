@echo off

call "%~dp0..\..\..\..\etc\setenv"

"%ORA_HOME%\bin\sqlplus" "/as sysdba" @"%~dp0data\oracle.sql" %ORA_DATADIR% 0<NUL

pause
