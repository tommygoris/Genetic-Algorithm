package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessMovement;

public class King extends AbstractChessPiece {

    public King(int x, int y, boolean isTopSide, String color){
        super(x, y, isTopSide, color);
    }

    @Override
    void UpdateMoves(ChessBoard board) {
        if (board.canMoveOrCapture(ChessMovement.moveSouth, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveSouth);
        }
        if (board.canMoveOrCapture(ChessMovement.moveNorth, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveNorth);
        }
        if (board.canMoveOrCapture(ChessMovement.moveEast, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveEast);
        }
        if (board.canMoveOrCapture(ChessMovement.moveWest, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveWest);
        }
        if (board.canMoveOrCapture(ChessMovement.moveNorthEast, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveNorthEast);
        }
        if (board.canMoveOrCapture(ChessMovement.moveNorthWest, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveNorthWest);
        }
        if (board.canMoveOrCapture(ChessMovement.moveSouthEast, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveSouthEast);
        }
        if (board.canMoveOrCapture(ChessMovement.moveSouthWest, isTopSide))
        {
            this.possibleMoves.add(ChessMovement.moveSouthWest);
        }
    }

    @Override
    public String toString(){
        return "KI" + "-" + this.color.charAt(0);
    }
}
