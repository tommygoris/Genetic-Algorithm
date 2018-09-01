package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessMovement;
import javafx.util.Pair;

import java.util.ArrayList;

public class Rook extends AbstractChessPiece {

    public Rook(int x, int y, boolean isTopSide, String color) {
        super(x, y, isTopSide, color);
    }

    @Override
    public void UpdateMoves(ChessBoard board) {
        this.possibleMoves.clear();
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesNorth(board, this.isTopSide, this.currentPosition));
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesSouth(board, this.isTopSide, this.currentPosition));
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesEast(board, this.isTopSide, this.currentPosition));
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesWest(board, this.isTopSide, this.currentPosition));
    }

    @Override
    public String toString(){
        return "R" + "-" + this.color.charAt(0);
    }
}
