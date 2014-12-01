#!/bin/sh

PRGDIR=`dirname $0`

. "$PRGDIR"/../../../../etc/setenv.sh

"$ORA_HOME/bin/sqlplus" "/as sysdba" @"$PRGDIR"/data/oracle.sql $ORA_DATADIR
