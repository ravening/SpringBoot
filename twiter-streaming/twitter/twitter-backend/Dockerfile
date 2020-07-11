FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Rakesh Venkatesh
ARG JAR_FILE=target/*.jar
WORKDIR  /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]