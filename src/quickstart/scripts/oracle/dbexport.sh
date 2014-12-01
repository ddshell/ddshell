#!/bin/sh

PRGDIR=`dirname $0`

. "$PRGDIR"/../../../../etc/setenv.sh

"$ORA_HOME/bin/exp" ddshell/ddshell owner=ddshell file="$PRGDIR"/data/ddshell_quickstart.dmp
