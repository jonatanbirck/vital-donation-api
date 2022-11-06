#!/bin/bash

volume=$(docker volume ls --filter name=pg_data_vital_donation | grep pg_data_vital_donation)

if [ -z "$volume" ]
then
    echo "Postgres volume does not exists. Creating it..."
    docker volume create pg_data_vital_donation
fi

echo "Updating project"
git pull origin main
echo "Rebuilding containers"
docker compose build && docker compose up -d