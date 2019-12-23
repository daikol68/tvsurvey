#!/bin/bash
. ./setenv.sh
find tomcat/webapps/ -maxdepth 1 -mindepth 1  -type d -exec rm -Rf {} \;
find tomcat/work/ -maxdepth 1 -mindepth 1  -type d -exec rm -Rf {} \;
$CATALINA_HOME/bin/startup.sh
