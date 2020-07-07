if username/password is configured in ~/.m2/settings.xml use the below command to build docker image and to push it

<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
 <servers>
   <server>
     <id>docker.io</id>
     <username><your docker username></username>
     <password>password</password>
   </server>
 </servers>
</settings>


mvn compile jib:build

If you dont want to use jib then use the below command to build docker image and for that docker needs to be running

mvn k8s:build


Generate kubernetes manifests using


mvn k8s:build

files will be in target/classes/META-INF/jkube
