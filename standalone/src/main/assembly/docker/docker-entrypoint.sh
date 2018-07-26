#!/bin/bash

if [ -z "$SERVICE_IP" ]; then
    export SERVICE_IP=`hostname -i`
fi
echo "SERVICE_IP=$SERVICE_IP"

if [ -z "$MSB_ADDR" ]; then
    echo "Missing required variable MSB_ADDR: Microservices Service Bus address <ip>:<port>"
    exit 1
fi
echo "MSB_ADDR=$MSB_ADDR"

# Wait for MSB initialization
echo "Wait for MSB initialization"
for i in {1..5}; do
    curl -sS -m 1 $MSB_ADDR > /dev/null && break
    sleep $i
done

# Configure service based on docker environment variables
/home/esr/docker/instance_config.sh

# Set env
sed -i 's|#networkaddress.cache.ttl=-1|networkaddress.cache.ttl=10|' /usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/jre/lib/security/java.security 
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)
export PATH=$PATH:/usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/jre/bin:/usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/bin
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export JRE_HOME=${JAVA_HOME}/jre

# Start the microservice
/home/esr/docker/instance_run.sh
