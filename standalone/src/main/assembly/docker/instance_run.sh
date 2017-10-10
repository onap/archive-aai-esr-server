#!/bin/bash
cd /home/esr
./bin/run.sh

while [ ! -f works/logs/extsys.log ]; do
    sleep 1
done
tail -F works/logs/extsys.log
