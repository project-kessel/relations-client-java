#!/bin/sh
DOCKER=$(command -v podman || command -v docker)

$DOCKER run -v "$(pwd):/workspace" --workdir /workspace bufbuild/buf generate buf.build/project-kessel/relations-api