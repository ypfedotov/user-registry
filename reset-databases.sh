#!/bin/bash -e

docker-compose down

docker ps --all --format {{.Names}} | grep user-registry | xargs docker rm || true

docker-compose up -d
