@echo off

call "%~dp0../setenv"

"%ORA_HOME%/bin/exp" ddshell_example/ddshell_example owner=ddshell_example file=%~dp0data/ddshell_example.dmp

pause


