#! /bin/sh

JAVA_HOME=/usr/local/jdk1.8
JAVA=$JAVA_HOME/bin/java

$JAVA -server -Xmx2g -Xms2g -Xmn512m -Xss256k -XX:MaxTenuringThreshold=15 -XX:+DisableExplicitGC -XX:+UseG1GC -XX:LargePageSizeInBytes=128m -XX:+AggressiveOpts -XX:+UseBiasedLocking -jar springboot-docker-maven.jar >> cowboy.out 2>&1 &