#!/bin/bash

docker container start demo-yago-external-api-container

until $(curl --output /dev/null --silent --head --fail http://localhost:28580/management/health); do
    printf '.'
    sleep 2
done

printf "\n"
printf "***************\n"
printf "External API UP\n"
printf "***************\n"