#!/usr/bin/env bash

docker rm -fv $(docker ps -q -fname=mydb) &> /dev/null

docker run --name mydb -p 3306:3306 -d \
    -e MYSQL_USER=root \
    -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
    -e MYSQL_DATABASE=devopsbingo \
    -e lower_case_table_names=1 \
    -e skip-ssl \
    -e character_set_server=utf8 \
    -e explicit_defaults_for_timestamp \
    mysql:5.7.18 > /dev/null

echo "Database is starting..."
sleep 10
echo "Database is up and running."

read -p "Close Database? [y] " close

while [ "$close" != "y" ]; do
    read -p "Close Database? [y] " close
done

docker rm -fv mydb &> /dev/null
echo "Database closed."
