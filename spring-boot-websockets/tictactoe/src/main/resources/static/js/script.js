    var turns = ["#","#","#","#","#","#","+","#"];
    var computerTurn = "";
    var turn = "";
    var gameOn = false;
    var count = 0;


    function playerTurn (turn, id){
        var spotTaken = $("#"+id).text();
        if (spotTaken ==="#"){
            count++;
            turns[id] = turn;
            $("#"+id).text(turn);
            winCondition(turns,turn);
            if (gameOn === false){
                computersTurn();
                $("#message").html("It's " + turn +"'s turn.");
                winCondition(turns, computerTurn);
            }
        }
    }

    $(".tic").click(function(){
        var slot = $(this).attr('id');
        playerTurn(turn,slot);
    });

    function reset(){
        turns = ["#","#","#","#","#","#","+","#"];
        count = 0;
        $(".tic").text("#");
        gameOn = true;
    }

    $("#reset").click(function(){
        reset();
    });
