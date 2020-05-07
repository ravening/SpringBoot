# Spring Cloud Stream Apache Kafka

## Description
A demo spring boot application to send and receive message from\
apache kafka using spring cloud stream apache kakfa libraries

## Building the application

1. Compile the project
```bash
mvn clean package -DskipTests
```

This will build the jar file and also generates the docker image.\
Change the username in ```<repository>``` field in pom.xml under `plugins`\
tab so that the docker image created will have your username.\

Or you can manually build the docker image using

```bash
docker build -t <image name> .
```

2. Push the docker image to dockerhub
```bash
docker push <image name>:<tag>
```

3. Note down the tag of the docker image and enter the same in `spring-cloud-stream-kafka.yml`\
so that kubernetes can download the image

## Deploying application to kubernetes

1. Run the command
```bash
kubectl apply -f spring-cloud-stream-kafka.yaml
```

This will create both deployment and service

2. Create ingress rules to access the service
```bash
kubectl apply -f ingress.yaml
```

3. Now you can access the endpoints

To send a message to kafka, open the below link in your browser

```html
http://<IP address of kubernetes cluster master>/greetings/sendmessage?message=helloworld
```

Or

```html
http://<IP address of kubernetes cluster master>/greetings/send/helloworld
```

4. Retrieve all the message sent using

```html
http://<IP address of kubernetes cluster master>/greetings/readmessage
```
