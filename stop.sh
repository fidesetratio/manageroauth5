#!/bin/bash
if [ -f /opt/oauth2/pid.file ]; then

 if ps -p $(cat /opt/oauth2/pid.file) > /dev/null;then
        kill $(cat /opt/oauth2/pid.file) > /dev/null
        echo 'kill them...'
 fi

fi
