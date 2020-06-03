# Spring boot reactive kafka application

## Sample project to demonstrate the use of reactive kafka in a spring boot application
This project contains two parts
1. A reactive producer which sends payments details to particular kafka topic
2. A reactive consumer which fetches the messages coming on the kafka topics

## Run the project

Make sure that kafka instance is running in localhost or in some machine.

Navigate to top of the project and run below commands

```
mvn clean package -DskipTests
```

This will build the jar files, builds docker image and created kubernetes resource files as well.project

Start the producer. This will run on port 8080

```
java -jar paymentprocessor-gateway/target/paymentprocessor-gateway.jar
```

Start the consumer. This will run on port 8081

```
java -jar paymentprocessor-paymentvalidator/target/paymentprocessor-paymentvalidator.jar
```

Navigate to `http://localhost:8080/api/payments/home` and click on submit button to send a messages
This will make the producer to send message to kafka which in turn will be received by consumer.

## Running from docker images

```
docker run -d -p 8081:8081 --name payment-validator --rm rakgenius/paymentprocessor-paymentvalidator:0.0.1
```

```
docker run -d -p 8080:8080 --name payment-gateway --rm rakgenius/paymentprocessor-gateway:0.0.1
```

Navigate to `http://localhost:8080/api/payments/home`


## Deploying on kubernetes

1. `kubectl apply -f kubernetes/paymentprocessor-gateway-deployment.yml`
2. `kubectl apply -f kubernetes/paymentprocessor-gateway-service.yml`
3. `kubectl apply -f kubernetes/paymentprocessor-paymentvalidator-deployment.yml`
4. `kubectl apply -f kubernetes/paymentprocessor-paymentvalidator-service.yml`
5. `kubectl apply -f kubernetes/ingress.yml`

Navigate to `http://<Kubernetes cluster IP>/api/payments/home`

## Detailed steps on building packages, docker images and resources

1. Spotify maven plugin will build the docker images
2. Eclipse jkube will build resources which are present under `target/classes/META-INF/jkube/kubernetes`
