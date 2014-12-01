#!/bin/sh

PRGDIR=`dirname $0`

. "$PRGDIR"/../../../../etc/setenv.sh

"%MYSQL_HOME%/bin/mysqldump" --hex-blob $MYSQL_LOGON ddshell_quickstart > "$PRGDIR"/data/ddshell_quickstart.sql
