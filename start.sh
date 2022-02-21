#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
#export BOT_NAME=$1
#export BOT_TOKEN=$2
#export OFFICE_DB_PASSWORD=$3
#export OFFICE_DB_USERNAME=$4

# Start new deployment
docker-compose up --build -d
sleep 5h