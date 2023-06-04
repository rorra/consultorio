#!/bin/sh
dir=$(dirname "$0")
java -cp "$dir/lib/h2-2.1.214.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Console "$@"
