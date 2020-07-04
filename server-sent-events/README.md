# server-sent-events
WebFlux Server using the Spring WebFlux framework and reactive Kafka which exposes a REST API for the clients to make secure HTTP requests. Once a secure connection is established between the client and the web flux server, it consumes messages from Kafka topics and pushes the data asynchronously without closing connection with the client unless required.

![Architectue](SSE-Webflux-Kafka.png)

Run the program and execute

```
curl --location --request GET 'localhost:8080/sse' --header 'Content-Type: text/event-stream;charset=UTF-8' --header 'Accept: text/event-stream;charset=UTF-8'
```

Start ServerGenerateEventsApplication.main
