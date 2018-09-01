package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.Player.Player;
import geneticalgorithm.examples.evolveann.playchess.pieces.AbstractChessPiece;
import javafx.scene.text.Text;

public class ChessEngine {

    private Player playerOne;
    private Player playerTwo;
    public boolean isTopPlayersTurn = true;

    public ChessEngine(Player playerOne, Player playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void playerMove(ChessBoard gameBoard, int x, int y)
    {
        Player player = this.getCurrentPlayer();
        player.move(gameBoard, x, y);
        isTopPlayersTurn ^= false;
        this.updateGameState(gameBoard);
    }

    public void updateGameState(ChessBoard gameBoard)
    {
        for (ChessSquare[] chessSquareArray : gameBoard.chessBoard)
        {
            for (ChessSquare chessSquare : chessSquareArray)
            {
                if (chessSquare != null && chessSquare.chessPiece != null)
                {
                    chessSquare.chessPiece.UpdateMoves(gameBoard);
                }
            }
        }
    }

    public Player getCurrentPlayer()
    {
        return (isTopPlayersTurn) ? playerOne : playerTwo;
    }
}
