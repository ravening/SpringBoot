#!/bin/bash
cd frontend
rm -rf dist
npm run build

cd ../backend
rm -rf target
mvn clean package -DskipTests

