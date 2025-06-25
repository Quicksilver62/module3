#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

# --- Configuration ---
ACCOUNT_SERVICE_DIR="account-service"
ACCOUNT_IMAGE_NAME="account-service"
ACCOUNT_IMAGE_TAG="0.0.1-SNAPSHOT"

BLOCKER_SERVICE_DIR="blocker-service"
BLOCKER_IMAGE_NAME="blocker-service"
BLOCKER_IMAGE_TAG="0.0.1-SNAPSHOT"

CASH_SERVICE_DIR="cash-service"
CASH_IMAGE_NAME="cash-service"
CASH_IMAGE_TAG="0.0.1-SNAPSHOT"

CONFIG_SERVER_DIR="config-server"
CONFIG_IMAGE_NAME="config-server"
CONFIG_IMAGE_TAG="0.0.1-SNAPSHOT"

EUREKA_SERVER_DIR="eureka-server"
EUREKA_IMAGE_NAME="eureka-server"
EUREKA_IMAGE_TAG="0.0.1-SNAPSHOT"

EXCHANGE_GENERATOR_DIR="exchange-generator"
EXCHANGE_GENERATOR_IMAGE_NAME="exchange-generator"
EXCHANGE_GENERATOR_IMAGE_TAG="0.0.1-SNAPSHOT"

EXCHANGE_SERVICE_DIR="exchange-service"
EXCHANGE_IMAGE_NAME="exchange-service"
EXCHANGE_IMAGE_TAG="0.0.1-SNAPSHOT"

FRONTEND_DIR="frontend"
FRONTEND_IMAGE_NAME="frontend"
FRONTEND_IMAGE_TAG="0.0.1-SNAPSHOT"

GATEWAY_DIR="gateway"
GATEWAY_IMAGE_NAME="gateway"
GATEWAY_IMAGE_TAG="0.0.1-SNAPSHOT"

NOTIFICATION_SERVICE_DIR="notification-service"
NOTIFICATION_IMAGE_NAME="notification-service"
NOTIFICATION_IMAGE_TAG="0.0.1-SNAPSHOT"

TRANSFER_SERVICE_DIR="transfer-service"
TRANSFER_IMAGE_NAME="transfer-service"
TRANSFER_IMAGE_TAG="0.0.1-SNAPSHOT"
# ---------------------

echo "Building Docker image for Eureka Server: ${EUREKA_IMAGE_NAME}:${EUREKA_IMAGE_TAG}"
# Navigate to the customer service directory
pushd "$EUREKA_SERVER_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${EUREKA_IMAGE_NAME}:${EUREKA_IMAGE_TAG}" .

echo "Eureka Server image built: ${EUREKA_IMAGE_NAME}:${EUREKA_IMAGE_TAG}"

# Navigate back
popd > /dev/null

echo ""
echo "Building Docker image for Config Server: ${CONFIG_IMAGE_NAME}:${CONFIG_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$CONFIG_SERVER_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${CONFIG_IMAGE_NAME}:${CONFIG_IMAGE_TAG}" .

echo "Config Server image built: ${CONFIG_IMAGE_NAME}:${CONFIG_IMAGE_TAG}"

echo ""
echo "Building Docker image for Gateway: ${GATEWAY_IMAGE_NAME}:${GATEWAY_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$GATEWAY_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${GATEWAY_IMAGE_NAME}:${GATEWAY_IMAGE_TAG}" .

echo "Gateway image built: ${GATEWAY_IMAGE_NAME}:${GATEWAY_IMAGE_TAG}"

echo ""
echo "Building Docker image for Account Service: ${ACCOUNT_IMAGE_NAME}:${ACCOUNT_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$ACCOUNT_SERVICE_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${ACCOUNT_IMAGE_NAME}:${ACCOUNT_IMAGE_TAG}" .

echo "Account Service image built: ${ACCOUNT_IMAGE_NAME}:${ACCOUNT_IMAGE_TAG}"

echo ""
echo "Building Docker image for Blocker Service: ${BLOCKER_IMAGE_NAME}:${BLOCKER_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$BLOCKER_SERVICE_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${BLOCKER_IMAGE_NAME}:${BLOCKER_IMAGE_TAG}" .

echo "Blocker Service image built: ${BLOCKER_IMAGE_NAME}:${BLOCKER_IMAGE_TAG}"

echo ""
echo "Building Docker image for Cash Service: ${CASH_IMAGE_NAME}:${CASH_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$CASH_SERVICE_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${CASH_IMAGE_NAME}:${CASH_IMAGE_TAG}" .

echo "Cash Service image built: ${CASH_IMAGE_NAME}:${CASH_IMAGE_TAG}"

echo ""
echo "Building Docker image for Exchange generator: ${EXCHANGE_GENERATOR_IMAGE_NAME}:${EXCHANGE_GENERATOR_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$EXCHANGE_GENERATOR_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${EXCHANGE_GENERATOR_IMAGE_NAME}:${EXCHANGE_GENERATOR_IMAGE_TAG}" .

echo "Exchange generator image built: ${EXCHANGE_GENERATOR_IMAGE_NAME}:${EXCHANGE_GENERATOR_IMAGE_TAG}"

echo ""
echo "Building Docker image for Exchange Service: ${EXCHANGE_IMAGE_NAME}:${EXCHANGE_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$EXCHANGE_SERVICE_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${EXCHANGE_IMAGE_NAME}:${EXCHANGE_IMAGE_TAG}" .

echo "Exchange Service image built: ${EXCHANGE_IMAGE_NAME}:${EXCHANGE_IMAGE_TAG}"

echo ""
echo "Building Docker image for Notification Service: ${NOTIFICATION_IMAGE_NAME}:${NOTIFICATION_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$NOTIFICATION_SERVICE_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${NOTIFICATION_IMAGE_NAME}:${NOTIFICATION_IMAGE_TAG}" .

echo "Notification Service image built: ${NOTIFICATION_IMAGE_NAME}:${NOTIFICATION_IMAGE_TAG}"

echo ""
echo "Building Docker image for Transfer Service: ${TRANSFER_IMAGE_NAME}:${TRANSFER_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$TRANSFER_SERVICE_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${TRANSFER_IMAGE_NAME}:${TRANSFER_IMAGE_TAG}" .

echo "Transfer Service image built: ${TRANSFER_IMAGE_NAME}:${TRANSFER_IMAGE_TAG}"

echo ""
echo "Building Docker image for Frontend: ${FRONTEND_IMAGE_NAME}:${FRONTEND_IMAGE_TAG}"
# Navigate to the order service directory
pushd "$FRONTEND_DIR" > /dev/null

# Build the Docker image using the base name
docker build -t "${FRONTEND_IMAGE_NAME}:${FRONTEND_IMAGE_TAG}" .

echo "Frontend image built: ${FRONTEND_IMAGE_NAME}:${FRONTEND_IMAGE_TAG}"

# Navigate back
popd > /dev/null

echo ""
echo "Docker images built successfully!" 