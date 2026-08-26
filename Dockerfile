FROM ubuntu:latest
LABEL authors="Guilherme"

ENTRYPOINT ["top", "-b"]