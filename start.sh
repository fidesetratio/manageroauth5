#!/bin/bash

nohup java -jar /opt/oauth2/manageroauth-0.0.1-SNAPSHOT.jar > /opt/oauth2/log.txt 2>&1 &
echo $! > /opt/oauth2/pid.file
