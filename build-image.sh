#!/bin/bash

mvn clean package
docker build -t open-affect/flow .
