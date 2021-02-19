var turns = [ ["#","#","#"] , ["#","#","#"] , [ "#","#", "#"] ];
let player = true;
let canPlay = false;
let playerTick = '';
let winner = null;

function playerTurn (id) {
    let xy = id.split("_")
    let x = xy[0]
    let y = xy[1]
    stompClient.send('/app/play', {},
        JSON.stringify({"type": playerTick, "x": x, "y": y, gameId: sessionId, "player": opponent }))
}

function displayBoardData(data) {
    let board = data.board;
    let count = 0
    for (let i = 0; i < 3; i++) {
        for (let  j = 0; j < 3; j++) {
            if (board[i][j] === 1) {
                turns[i][j] = 'X'
                count++
            } else if (board[i][j] === 4) {
                turns[i][j] = 'O'
                count++
            } else {
                turns[i][j] = ''
            }
            let  id = i + "_" + j
            $('#' + id).text(turns[i][j])
        }
    }

    if (data.winner !== null) {
        alert("Winner is " + data.winner)
        winner = data.winner
        resetBoard()
    }
    if (count === 9) {
        alert('Match draw')
        resetBoard()
        stompClient.send('/app/draw-game', {}, JSON.stringify( { "login": login }))
    }
}

function resetBoard() {
    for (let i = 0; i < 3; i++) {
        for (let  j = 0; j < 3; j++) {
            turns[i][j] = ''
            let  id = i + "_" + j
            $('#' + id).text(turns[i][j])
        }
    }
}

$(".tic").click(function(){
    const slot = $(this).attr('id');
    if (currentPlayer === login && canPlay === true) {
        playerTurn(slot)
    }
});

function reset(){
    stompClient.send('/app/reset', {}, JSON.stringify( {"login": login }))
    resetBoard()
    canPlay = true
    isFirstPlayer = !isFirstPlayer
    player = !player
    turns = [["","",""], ["","",""] , ["","", ""]];
    $(".tic").text("");
}

$("#reset").click(function(){
    reset();
});
