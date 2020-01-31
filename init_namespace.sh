#!/bin/bash

NAMESPACE=$1

NAMESPACE_ID=$(curl --location --request PUT 'http://localhost:58080/namespaces' --header 'Content-Type: application/json' --data-raw '{"name": "${NAMESPACE}"}')

