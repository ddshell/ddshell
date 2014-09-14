@echo off

call "%~dp0../setenv"

"%ORA_HOME%/bin/imp" ddshell_example/ddshell_example fromuser=ddshell_example touser=ddshell_example file=%~dp0data/ddshell_example.dmp

pause


