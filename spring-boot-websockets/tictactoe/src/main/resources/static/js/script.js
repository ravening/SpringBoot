var turns = [ ["#","#","#"] , ["#","#","#"] , [ "#","#", "#"] ];
let player = true;
let canPlay = false;
let playerTick = ''

function playerTurn (id) {
    let xy = id.split("_")
    let x = xy[0]
    let y = xy[1]
    stompClient.send('/app/play', {}, JSON.stringify({"type": playerTick, "x": x, "y": y, gameId: sessionId}))
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
        reset()
    }
    console.log('count is ' + count)
    if (count === 9) {
        alert('Match draw')
        reset()
    }
}

$(".tic").click(function(){
    const slot = $(this).attr('id');
    if (player === true && canPlay === true) {
        playerTurn(slot);
    }
});

function reset(){
    canPlay = true
    isFirstPlayer = !isFirstPlayer
    turns = [["#","#","#"], ["#","#","#"] , ["#","#", "#"]];
    $(".tic").text("");
}

$("#reset").click(function(){
    reset();
});
