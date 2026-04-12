#!/bin/bash

# check-db-status.sh
echo "Starting Database Connection Check..."

# Replace these with your actual DB credentials or environment variables
DB_HOST="localhost"
DB_PORT="5432"

# Attempt to ping the port to see if the service is up
nc -z -v -w5 $DB_HOST $DB_PORT

if [ $? -eq 0 ]; then
    echo "✅ Database is REACHABLE on $DB_HOST:$DB_PORT"
    exit 0
else
    echo "❌ Database CONNECTION FAILED on $DB_HOST:$DB_PORT"
    echo "Ensure the database service is running on the static agent."
    exit 1
fi
