# Reactive elasticsearch

Spring boot application to perform CRUD operations in a reactive way\
using elasticsearch

## Running application
```
./mvnw spring-boot:run
```

## Fetching the documents
```
curl -X GET "localhost:9200/sample/_search?pretty"
```

## Delting the documents
```
curl -XDELETE http://localhost:9200/sample
```
