#!/bin/bash

 rm -rf backend/src/main/resources/public/*
cd frontend
rm -rf dist
npm run build

cd ../backend
rm -rf target
mvn clean package -DskipTests

