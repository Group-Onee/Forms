#!/bin/bash

# deploy.sh
ENVIRONMENT=$1
ARTIFACT_PATH="target/Forms-1.0-SNAPSHOT.jar"
DEPLOY_DIR="/var/www/forms-app/$ENVIRONMENT"

echo "🚀 Starting Deployment to: $ENVIRONMENT"

# Check if the artifact exists after the Maven build
if [ ! -f "$ARTIFACT_PATH" ]; then
    echo "❌ Error: Artifact not found at $ARTIFACT_PATH"
    exit 1
fi

# Create deployment directory if it doesn't exist
mkdir -p $DEPLOY_DIR

# Copy the new artifact to the deployment folder
cp $ARTIFACT_PATH $DEPLOY_DIR/app-current.jar

# Simple restart logic (assuming a systemd service is configured)
# sudo systemctl restart forms-staging.service

if [ $? -eq 0 ]; then
    echo "✅ Deployment to $ENVIRONMENT successful!"
else
    echo "❌ Deployment FAILED."
    exit 1
fi
