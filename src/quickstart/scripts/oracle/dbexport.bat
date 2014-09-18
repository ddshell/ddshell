@echo off

call "%~dp0../setenv"

"%ORA_HOME%/bin/exp" ddshell_quickstart/ddshell_quickstart owner=ddshell_quickstart file=%~dp0data/ddshell_quickstart.dmp

pause


