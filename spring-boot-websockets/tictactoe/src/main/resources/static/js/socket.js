let url = 'http://localhost:8080';
let stompClient;
let gameId = null;
let login;
let sessionId = '';
let isFirstPlayer = false;

function connect(login, newgame) {
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({'username': login}, function (frame) {
        connected = true
        stompClient.subscribe("/topic/game-progress/" + login, function (response) {
            player = !player
            isFirstPlayer = !isFirstPlayer
            let data = JSON.parse(response.body);
            playerTick = data.currentTick
            console.log(data)
            displayBoardData(data)
        })
        stompClient.subscribe("/topic/game-over/" + login, function (response) {
            canPlay = false
            player = !player
            isFirstPlayer = !isFirstPlayer
            let data = JSON.parse(response.body);
            console.log(data.message)
            alert(data.message)
        })
        stompClient.subscribe("/topic/start-game/" + login, function (response) {
            player = true
            isFirstPlayer = true
            let data = JSON.parse(response.body)
            sessionId = data.player1SessionId
            playerTick = data.currentTick
            displayBoardData(data)
            // reset()
            console.log(data)
        })
        stompClient.subscribe("/topic/connect-random/player1/" + login, function (response) {
            isFirstPlayer = true
            canPlay = true
            let data = JSON.parse(response.body)
            console.log(data)
            alert('You are playing with ' + data.player2.login)
        })
        stompClient.subscribe('/topic/connect-random/player2/' + login, function (response) {
            isFirstPlayer = false
            player = false
            canPlay = true
            let data = JSON.parse(response.body)
            sessionId = data.player2SessionId
            alert('You are playing with ' + data.player1.login)
        })
        stompClient.subscribe('/topic/terminate-game/' + login, function (response) {
            canPlay = false
            let data = JSON.parse(response.body)
            disconnect()
            alert(data.message)
        })
        stompClient.subscribe('/topic/invalid-username/' + login, function (response) {
            let data = JSON.parse(response.body)
            if (sessionId === '') {
                alert(data.message)
            }
        })
        stompClient.subscribe('/topic/game-not-found/' + login, function (response) {
            let data = JSON.parse(response.body)
            alert(data.message)
            disconnect()
        })
        if (gameId !== null && gameId !== '' && newgame === false) {
            console.log('connecting to game id ' + gameId)
            stompClient.send('/app/connect/gameid', {}, JSON.stringify({ "login": login, "gameId": gameId }) )
        } else {
            if (newgame === true) {
                console.log('starting a new game')
                stompClient.send("/app/start", {}, JSON.stringify({"login": login}))
            } else {
                console.log('connecting to random game')
                stompClient.send("/app/connect/random", {}, JSON.stringify({"login": login}))
            }
        }
    },  function (parms) {
        alert('Connection disconnected')
    })
}

function create_game() {
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert('Please enter login name')
    } else {
        connect(login, true)

    }
}
function connectToRandomGame() {
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert('Please enter login name')
    } else {
        connect(login, false)
    }
}

function connectToSpecificGame() {
    login = document.getElementById("login").value;
    gameId = document.getElementById("game_id").value;
    if (gameId === null || gameId === '') {
        alert('Please enter game id')
    } else {
        console.log('game id is ' + gameId)
        connect(login, false)
    }
}

function disconnect()
{
    stompClient.disconnect(function () {
        stompClient.send("/app/disconnect/", {}, JSON.stringify({}))
    }, {})
}
