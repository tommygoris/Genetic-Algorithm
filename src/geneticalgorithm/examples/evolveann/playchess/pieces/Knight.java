package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessMovement;
import javafx.util.Pair;

import java.util.ArrayList;

public class Knight extends AbstractChessPiece {

    public Knight(int x, int y, boolean isTopSide, String color){
        super(x, y, isTopSide, color);
    }

    @Override
    public void UpdateMoves(ChessBoard board) {
        this.possibleMoves.clear();
        if (board.canMove(ChessMovement.TwoDownOneLeftKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoDownOneLeftKnightMove);
        }
        if (board.canMove(ChessMovement.TwoDownOneRightKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoDownOneRightKnightMove);
        }
        if (board.canMove(ChessMovement.TwoUpOneLeftKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoUpOneLeftKnightMove);
        }
        if (board.canMove(ChessMovement.TwoUpOneRightKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoUpOneRightKnightMove);
        }
        if (board.canMove(ChessMovement.TwoLeftOneDownKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoLeftOneDownKnightMove);
        }
        if (board.canMove(ChessMovement.TwoLeftOneUpKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoLeftOneUpKnightMove);
        }
        if (board.canMove(ChessMovement.TwoRightOneDownKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoRightOneDownKnightMove);
        }
        if (board.canMove(ChessMovement.TwoRightOneUpKnightMove))
        {
            this.possibleMoves.add(ChessMovement.TwoRightOneUpKnightMove);
        }
    }
}
