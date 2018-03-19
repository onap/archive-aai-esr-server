#!/bin/bash

MSB_IP=`echo $MSB_ADDR | cut -d: -f 1`
MSB_PORT=`echo $MSB_ADDR | cut -d: -f 2`

sed -i "s|msbDiscoveryIp.*|msbDiscoveryIp: $MSB_IP|" /home/esr/conf/extsys.yml
sed -i "s|msbServerAddr.*|msbServerAddr: http://$MSB_IP:443|" /home/esr/conf/extsys.yml

cat /home/esr/conf/extsys.yml
