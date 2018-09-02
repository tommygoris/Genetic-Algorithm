package geneticalgorithm.examples.evolveann.playchess.Player;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.pieces.AbstractChessPiece;

public abstract class Player {

    public abstract void move(ChessBoard board, int x, int y);
}
