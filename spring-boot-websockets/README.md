# Spring Boot Angular Websocket

This project demos how to display users stored in the database on the frontend using the websockets.\
Frontend connects to backend using websocket and as soon as users are stored in the database,\
the message will be sent from backend to frontend and displayed.

## Steps to run the project
Clone the repository to any location.

Start the backend server first
```
cd websocket
./mvnw spring-boot:run
```

Once its running, start the frontend app
```
cd Angular6Websocket
npm install
ng serve
```

Navigate to your browser
```
http://localhost:4200
```

Click on `Connect` to establish a websocket connection to backend.

After that enter the user name in the input field and click `Send`
As soon as you press the button, the message will be sent to backend which will be stored in the db\
and sends back the response back to frontend

To display all the users stored in the database, click on `List All users`.

To save a new user in the database send a `POST` request to backend using
```
http post localhost:8080/api/user name=<YOUR NAME>
```

Where `http` is the command used by `HTTPie` package which can be installed using `apt get install httpie`\
You can also use `curl` to make the post request
