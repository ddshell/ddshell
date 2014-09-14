#!/bin/sh

PRGDIR=`dirname $0`

. "$PRGDIR"/../setenv.sh

"$MYSQL_HOME/bin/mysql" $MYSQL_LOGON -f mysql < "$PRGDIR"/data/mysql.sql
"$MYSQL_HOME/bin/mysql" $MYSQL_LOGON ddshell_example < "$PRGDIR"/data/ddshell_example.sql
