let url = 'http://95.168.184.69:8080';
let stompClient;
let gameId = null;
let login;
let sessionId = '';
let isFirstPlayer = false;
let internalGameId = '';
let currentPlayer = '';
let opponent = '';

function connect(login, newgame) {
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({'username': login}, function (frame) {
        connected = true
        stompClient.subscribe("/topic/game-progress/" + login, function (response) {
            player = !player
            isFirstPlayer = !isFirstPlayer
            let data = JSON.parse(response.body);
            currentPlayer = data.currentPlayer;
            // console.log(data)
            displayBoardData(data)
            $("#playerturn").html('Its ' + currentPlayer + ' turn')
        })
        stompClient.subscribe("/topic/game-over/" + login, function (response) {
            player = !player
            isFirstPlayer = !isFirstPlayer
            let data = JSON.parse(response.body);
            currentPlayer = data.game.currentPlayer
            displayBoardData(data.game)
            $("#playerturn").html('Its ' + currentPlayer + ' turn')
        })
        stompClient.subscribe("/topic/start-game/" + login, function (response) {
            player = true
            isFirstPlayer = true
            currentPlayer = login
            let data = JSON.parse(response.body)
            sessionId = data.player1SessionId
            internalGameId = data.gameId
            playerTick = 'X'
            displayBoardData(data)
            // console.log(data)
            alert('Created new game with id ' + internalGameId)
        })
        stompClient.subscribe("/topic/connect-random/player1/" + login, function (response) {
            isFirstPlayer = true
            canPlay = true
            let data = JSON.parse(response.body)
            // console.log(data)
            opponent = data.player2.login
            currentPlayer = data.currentPlayer
            alert('You are playing with ' + data.player2.login)
            $("#playerturn").html('Its ' + currentPlayer + ' turn')
            $("#gameinfo").html('You are playing with ' + data.player2.login)
        })
        stompClient.subscribe('/topic/connect-random/player2/' + login, function (response) {
            isFirstPlayer = false
            player = false
            canPlay = true
            let data = JSON.parse(response.body)
            sessionId = data.player2SessionId
            currentPlayer = data.currentPlayer
            opponent = data.player1.login
            playerTick = 'O'
            alert('You are playing with ' + data.player1.login)
            $("#playerturn").html('Its ' + currentPlayer + ' turn')
            $("#gameinfo").html('You are playing with ' + data.player1.login)
        })
        stompClient.subscribe('/topic/terminate-game/' + login, function (response) {
            let data = JSON.parse(response.body)
            resetBoard()
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
            stompClient.send('/app/connect/gameid', {}, JSON.stringify({ "login": login, "gameId": gameId }) )
        } else {
            if (newgame === true) {
                stompClient.send("/app/start", {}, JSON.stringify({"login": login}))
            } else {
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
    if (login === null || login === '') {
        alert('Please enter login name')
    } else
    if (gameId === null || gameId === '') {
        alert('Please enter game id')
    } else {
        connect(login, false)
    }
}

function disconnect()
{
    stompClient.disconnect(function () {
        stompClient.send("/app/disconnect/", {}, JSON.stringify({}))
    }, {})
}
