#!/bin/sh

PRGDIR=`dirname $0`

. "$PRGDIR"/../../../../etc/setenv.sh

"$ORA_HOME/bin/imp" ddshell/ddshell fromuser=ddshell touser=ddshell file="$PRGDIR"/data/ddshell_quickstart.dmp
