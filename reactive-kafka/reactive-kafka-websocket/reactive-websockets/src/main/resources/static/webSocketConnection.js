var webSocket;

function logMessage(msg) {
    document.getElementById("result").appendChild(document.createTextNode(msg + "\n"));
}

function openWebSocket() {
    webSocket = new WebSocket("ws://localhost:8080/stringConverter");

    webSocket.onopen = function() { logMessage("Opened the Websocket Connection!");};

    webSocket.onmessage = function(event) { logMessage("Result: " + event.data); }
}

function sendString() {
    var input = document.getElementById("input").value;
    webSocket.send(input);
}