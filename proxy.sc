#!/bin/bash

# This script is created to auto transfer proxy logs to aws
# Created by M Chery on March 25, 2019.

# Variables

 dir='/var/log/httpd'

 hostname='hostname'

# Verify that the user is root

if ! [ $(id -u) = 0 ]; then

   echo "User must be root to use this command. Please log in as root"

   exit 1

# Compressing the log files

else 

cd $dir

fi

for file in *log; do

tar -czvf `hostname`.log.gz /var/log/httpd/*

sleep 1


#  SFTP the logs to AWS

for file in *.gz; do

aws s3 mv /var/log/httpd/$hostname.log.gz s3://server-logs/[env]/[hostname]/


done

done
