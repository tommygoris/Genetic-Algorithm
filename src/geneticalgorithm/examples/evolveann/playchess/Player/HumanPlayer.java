package geneticalgorithm.examples.evolveann.playchess.Player;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessSquare;
import geneticalgorithm.examples.evolveann.playchess.pieces.AbstractChessPiece;
import geneticalgorithm.examples.evolveann.playchess.pieces.Pawn;
import javafx.util.Pair;

import java.util.List;

public class HumanPlayer extends Player {
    @Override
    public void move(ChessBoard board, int x, int y) {
        int xCurrentLocation = board.selectedChessPiece.currentPosition.getKey();
        int yCurrentLocation = board.selectedChessPiece.currentPosition.getValue();

        board.chessBoard[y][x] = new ChessSquare(board.selectedChessPiece);
        board.chessBoard[yCurrentLocation][xCurrentLocation] = new ChessSquare();
        this.updatePieceState(board.selectedChessPiece, x, y, xCurrentLocation, yCurrentLocation);
        this.checkPawnPromotion(board);
    }

    public void updatePieceState(AbstractChessPiece selectedPiece, int newX, int newY, int oldX, int oldY)
    {
        selectedPiece.currentPosition = new Pair<>(newX, newY);
        selectedPiece.pastMoves.add(new Pair<>(oldX - newX, oldY - newY));
    }

    public void checkPawnPromotion(ChessBoard board)
    {
        AbstractChessPiece selectedPiece = board.selectedChessPiece;
        if (selectedPiece instanceof Pawn)
        {
            if ((selectedPiece.isTopSide && selectedPiece.currentPosition.getValue() == 7)
                    || !selectedPiece.isTopSide && selectedPiece.currentPosition.getValue() == 0)
            {
                this.doPromotion(selectedPiece, board);
            }
        }
    }

    public void doPromotion(AbstractChessPiece selectedPiece, ChessBoard board)
    {
        Pawn pawnSelectedPiece = (Pawn)selectedPiece;
        int xLocation = pawnSelectedPiece.currentPosition.getKey();
        int yLocation = pawnSelectedPiece.currentPosition.getValue();
        boolean isTopSide = pawnSelectedPiece.isTopSide;
        String color = pawnSelectedPiece.color;
        List<AbstractChessPiece> possiblePromotions  = pawnSelectedPiece.PromotePawn(xLocation, yLocation, isTopSide, color);
        AbstractChessPiece promotedPiece = possiblePromotions.get(0);
        promotedPiece.pastMoves = pawnSelectedPiece.pastMoves;
//        board.selectedChessPiece = promotedPiece;
        board.chessBoard[yLocation][xLocation].chessPiece = promotedPiece;
    }
}
