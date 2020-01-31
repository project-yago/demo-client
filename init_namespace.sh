#!/bin/bash

NAMESPACE=$1

NAMESPACE_ID=$(curl --location --request PUT 'http://localhost:58080/namespaces' --header 'Content-Type: application/json' --data-raw "{\"name\": \"$NAMESPACE\"}" | jq -r .id)

echo "NAMESPACE_ID : ${NAMESPACE_ID}"

CONFIG_URL="http://localhost:58080/agent/config/${NAMESPACE_ID}"

echo "CONFIG_URL : ${CONFIG_URL}"

curl --location --request PUT ${CONFIG_URL} \
--header 'Content-Type: application/json' \
--data-raw '{
  "protocol": "http",
  "proxyHost": "localhost",
  "proxyPort": 55000
}'

