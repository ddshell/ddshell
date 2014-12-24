#!/bin/sh

SCRDIR=`dirname $0`

. "$SCRDIR"/../../../etc/setenv.sh

cd "$SCRDIR"/..

gradle clean cleanEclipse wsinit eclipse

PRGDIR=`pwd`
export PRGDIR
"$ECLIPSE_HOME"/eclipse -nosplash -data "$PRGDIR" -vm "$JAVA_HOME"/bin/java -application org.eclipse.ant.core.antRunner -buildfile "$SCRDIR"/eclipse.xml
