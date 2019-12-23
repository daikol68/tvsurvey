JAVA_HOME=/usr/java/latest; export JAVA_HOME
CATALINA_HOME=/home/tomcat/apache-tomcat-8; export CATALINA_HOME
CATALINA_BASE=@HOME@/tomcat; export CATALINA_BASE
CATALINA_OUT=@HOME@/logs/@NAME@.log; export CATALINA_OUT
JAVA_OPTS="$JAVA_OPTS -Xms256M -Xmx1024M -Dapplication.name=@NAME@ -Dapplication.home=@HOME@ -Dlogback.configurationFile=@HOME@/conf/logback.xml -Dtvsurvey-backend.endpoint=http://192.168.249.161:8080/tvsurvey-backend/rest -Dcom.sun.management.jmxremote"; export JAVA_OPTS
