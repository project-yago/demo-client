# demo-client

curl --location --request PUT 'http://localhost:58080/namespaces' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "<NAMESPACE HERE>"
}'

curl --location --request PUT 'http://localhost:58080/agent/config/<NAMESPACE UUID HERE>' \
--header 'Content-Type: application/json' \
--data-raw '{
  "protocol": "http",
  "proxyHost": "localhost",
  "proxyPort": 55000
}'
