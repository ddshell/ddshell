drop database ddshell_quickstart;
create database ddshell_quickstart default character set utf8;
grant all privileges on ddshell_quickstart.* to 'ddshell'@'localhost' identified by 'ddshell' with grant option;
