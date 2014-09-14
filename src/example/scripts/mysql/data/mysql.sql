drop database ddshell_example;
create database ddshell_example default character set utf8;
grant all privileges on ddshell_example.* to 'ddshell_example'@'localhost' identified by 'ddshell_example' with grant option;
