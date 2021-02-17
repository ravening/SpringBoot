let url = 'http://localhost:8080'
let stompClient;
let gameId;
let login;

function connect(login, newgame, gameid) {
    console.log("creating new game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to " + frame);
        connected = true
        stompClient.subscribe("/topic/game-progress/", function (response) {
            let data = JSON.parse(response.body);
            console.log(data)
        })
        stompClient.subscribe("/topic/start-game/" + login, function (response) {
            let data = JSON.parse(response.body)
            reset()
            console.log(data)
        })
        stompClient.subscribe("/topic/connect-random/" + login, function (response) {
            let data = JSON.parse(response.body)
            console.log(data)
        })
        stompClient.subscribe('/topic/terminate-game/' + login, function (response) {
            let data = JSON.parse(response.body)
            console.log(data)
        })
        if (gameId !== null) {

        }

        if (newgame === true) {
            stompClient.send("/app/start", {}, JSON.stringify({ "login": login }))
        } else {
            stompClient.send("/app/connect/random", {}, JSON.stringify({ "login": login }))
        }
    },  function (parms) {
        console.log('in here')
    })
    stompClient.disconnect(function () {
        // stompClient.send("/app/disconnect", {}, JSON.stringify({ "login": login }))
        console.log('==============================')
    }, {})
}

function create_game() {
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert('Please enter login name')
    } else {
        connect(login, true, null)

    }
}
function connectToRandomGame() {
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert('Please enter login name')
    } else {
        connect(login, false, null)
    }
}

function connectToSpecificGame() {

}

function disconnect()
{
    stompClient.disconnect(function () {
        // stompClient.send("/app/disconnect", {}, JSON.stringify({ "login": login }))
        console.log('==============================')
    }, {})
}
