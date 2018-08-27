package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.Player.Player;

public class ChessEngine {

    public ChessBoard gameBoard;
    public Player playerOne;
    public Player playerTwo;

    public ChessEngine(ChessBoard gameBoard, Player playerOne, Player playerTwo)
    {
        this.gameBoard = gameBoard;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void playerMove()
    {
        playerOne.move();
        playerTwo.move();
    }
}
