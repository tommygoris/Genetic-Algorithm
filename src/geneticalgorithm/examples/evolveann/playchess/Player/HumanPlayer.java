package geneticalgorithm.examples.evolveann.playchess.Player;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessSquare;
import geneticalgorithm.examples.evolveann.playchess.pieces.AbstractChessPiece;
import javafx.util.Pair;

public class HumanPlayer extends Player {
    @Override
    public void move(ChessBoard board, int x, int y) {
        int xCurrentLocation = board.selectedChessPiece.currentPosition.getKey();
        int yCurrentLocation = board.selectedChessPiece.currentPosition.getValue();

        board.chessBoard[y][x] = new ChessSquare(board.selectedChessPiece);
        board.chessBoard[yCurrentLocation][xCurrentLocation] = new ChessSquare();
        this.updatePieceState(board.selectedChessPiece, x, y, xCurrentLocation, yCurrentLocation);
    }

    public void updatePieceState(AbstractChessPiece selectedPiece, int newX, int newY, int oldX, int oldY)
    {
        selectedPiece.currentPosition = new Pair<>(newX, newY);
        selectedPiece.pastMoves.add(new Pair<>(oldX - newX, oldY - newY));
    }
}
