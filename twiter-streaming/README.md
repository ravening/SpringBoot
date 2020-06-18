# springboot-websockets with Twitter

## What it is

Spring boot project used to stream tweets and display on browser using websockets

For now it contains:

1. A backend java app which fetches the tweets
2. A frontend react app which displays the stream of tweets

## Getting started with the project

###First build the backend app

```
cd twitter-backend
mvn clean package -DskipTests
```

Now run the backend application

```
java -jar target/twitter-stream.jar
```

This will run on port 8080 and start streaming tweets on topic `java`

### Now build the frontend app

```
cd twitter-frontend
npm start
```

Navigate to `http://localhost:3000`

By default it streams all tweets containing keyword `java`.

To change the keyword, hit the below endpoint and enter your keyword

```
http://localhost:8080/twitter/stream/<YOUR KEYWORD>
```

Now the tweets for new keyword will automatically stream on port 3000


## Other endpoints

1. To display the timeline of the current user
```
http://localhost:8080/twitter/timeline
```

2. To search for tweets containing a keyword
```
http://localhost:8080/twitter/search/<KEYWORD>
```

