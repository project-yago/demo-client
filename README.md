# demo-client

Start Demo

> ./start_demo.sh

Flush redis Database

> ./clean_redisDB.sh


Init namespace (create namespace and namespace configuration)

> ./init_namespace.sh <NAMESPACE>

Ex: ./init_namespace.sh test-app-client

Demo Container Demo

| Service | Container Name | Port Host |
| ------ | ------ | ------ |
| Redis | demo-yago-mock-api-redis-container | 56379 |
| Mock API | demo-yago-mock-api-container | 58080 |
| Mock UI | demo-yago-mock-ui-container | 50080 |
| Proxy | demo-yago-proxy-container | 55000 |
| White App Server (External API) | demo-yago-external-api-container | 58580 |