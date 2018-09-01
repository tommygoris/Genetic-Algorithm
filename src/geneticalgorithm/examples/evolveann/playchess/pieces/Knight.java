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
        if (board.canMoveOrCapture(ChessMovement.TwoDownOneLeftKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoDownOneLeftKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoDownOneRightKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoDownOneRightKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoUpOneLeftKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoUpOneLeftKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoUpOneRightKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoUpOneRightKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoLeftOneDownKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoLeftOneDownKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoLeftOneUpKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoLeftOneUpKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoRightOneDownKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoRightOneDownKnightMove);
        }
        if (board.canMoveOrCapture(ChessMovement.TwoRightOneUpKnightMove, isTopSide, this.currentPosition))
        {
            this.possibleMoves.add(ChessMovement.TwoRightOneUpKnightMove);
        }
    }

    @Override
    public String toString(){
        return "KN" + "-" + this.color.charAt(0);
    }
}
