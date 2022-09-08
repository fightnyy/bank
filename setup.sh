#!/bin/bash

GREEN='\033[0;32m'
NC='\033[0m'

PROJECT_ROOT_PATH=$(git rev-parse --show-toplevel)
BUILD_RESULT_PATH="$PROJECT_ROOT_PATH/build/libs"

docker_compose_start() {
  echo -e "${GREEN}Running containers in daemon mode${NC}"
  docker-compose up -d
}

build_application() {
  cd $PROJECT_ROOT_PATH

  $PROJECT_ROOT_PATH/gradlew clean build
}

run_application() {
  cd $BUILD_RESULT_PATH
  java -jar hatemint.jar
}

main() {
  echo -e "${PROJECT_ROOT_PATH}"
  docker_compose_start
  build_application
  run_application
}

main "$@"
