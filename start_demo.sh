#!/bin/bash

export MOCK_API_BASE_URI=http://localhost:58080

# Pull latest images
docker-compose pull

# Start all containers
docker-compose up --force-recreate