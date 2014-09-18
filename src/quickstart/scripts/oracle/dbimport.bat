@echo off

call "%~dp0../setenv"

"%ORA_HOME%/bin/imp" ddshell_quickstart/ddshell_quickstart fromuser=ddshell_quickstart touser=ddshell_quickstart file=%~dp0data/ddshell_quickstart.dmp

pause


