/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.pieces.AbstractChessPiece;
import geneticalgorithm.examples.evolveann.playchess.pieces.Pawn;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class ChessBoard {

    public ChessSquare[][] chessBoard = new ChessSquare[ChessProperties.MAX_CHESSBOARD_SIZE][ChessProperties.MAX_CHESSBOARD_SIZE];
    public AbstractChessPiece selectedChessPiece;

    public ChessBoard() {
        for (int i = 2; i < ChessProperties.MAX_CHESSBOARD_SIZE; i++)
        {
            for (int j = 0; j < ChessProperties.MAX_CHESSBOARD_SIZE; j++)
            {
                chessBoard[i][j] = new ChessSquare();
            }
        }
    }

    public boolean canMove(int x, int y, int xCurrentPosition, int yCurrentPosition) {
        x += xCurrentPosition;
        y += yCurrentPosition;
        return (x < ChessProperties.MAX_CHESSBOARD_SIZE && y < ChessProperties.MAX_CHESSBOARD_SIZE && x >= 0 && y >= 0) && !chessBoard[y][x].taken;
    }

    public boolean canMove(Pair<Integer, Integer> pair, Pair<Integer, Integer> currentPosition) {
        int xCurrentPosition = currentPosition.getKey();
        int yCurrentPosition = currentPosition.getValue();
        int x = pair.getKey() + xCurrentPosition;
        int y = pair.getValue() + yCurrentPosition;
        return (x < ChessProperties.MAX_CHESSBOARD_SIZE && y < ChessProperties.MAX_CHESSBOARD_SIZE && x >= 0 && y >= 0) && !chessBoard[y][x].taken;
    }

    public boolean canCapture(int x, int y, boolean isTopSide, int xCurrentPosition, int yCurrentPosition) {
        x += xCurrentPosition;
        y += yCurrentPosition;
        return (x < ChessProperties.MAX_CHESSBOARD_SIZE && y < ChessProperties.MAX_CHESSBOARD_SIZE && x >= 0 && y >= 0) &&
                chessBoard[y][x].taken && chessBoard[y][x].chessPiece.isTopSide != isTopSide;
    }

    public boolean canCapture(Pair<Integer, Integer> pair, boolean isTopSide, Pair<Integer, Integer> currentPosition) {
        int xCurrentPosition = currentPosition.getKey();
        int yCurrentPosition = currentPosition.getValue();
        int x = pair.getKey() + xCurrentPosition;
        int y = pair.getValue() + yCurrentPosition;
        return (x < ChessProperties.MAX_CHESSBOARD_SIZE && y < ChessProperties.MAX_CHESSBOARD_SIZE && x >= 0 && y >= 0) && chessBoard[y][x].taken &&
                chessBoard[y][x].chessPiece.isTopSide != isTopSide;
    }

    public boolean canMoveOrCapture(Pair<Integer, Integer> pair, boolean isTopSide, Pair<Integer, Integer> currentPosition) {
        return this.canCapture(pair, isTopSide, currentPosition) || this.canMove(pair, currentPosition);
    }

    public boolean canMoveOrCapture(int x, int y, boolean isTopSide, int xCurrentPosition, int yCurrentPosition) {
        return this.canCapture(x, y, isTopSide, xCurrentPosition, yCurrentPosition) || this.canMove(x, y, xCurrentPosition, yCurrentPosition);
    }

    public Pair<Integer, Integer> EnPassantPawnLocation(Pair<Integer, Integer> currentPosition, boolean isTopSide) {
        int x = currentPosition.getKey();
        int y = currentPosition.getValue();
        Pawn currentPawn = (Pawn)chessBoard[y][x].chessPiece;
        if (currentPawn.isTopSide)
        {
            if (this.canMove(ChessMovement.moveSouthWest, currentPosition) && chessBoard[y][x - 1].chessPiece instanceof Pawn)
            {
                Pawn nextToPawn = (Pawn)chessBoard[y][x - 1].chessPiece;
                if ((nextToPawn.pastMoves.size() == 1) && (!nextToPawn.isTopSide))
                {
                    Pair<Integer, Integer> lastMove = nextToPawn.pastMoves.get(0);
                    int xPastMove = lastMove.getKey();
                    int yPastMove = lastMove.getValue();
                    if (xPastMove == 0 && yPastMove == 2)
                    {
                        return ChessMovement.moveSouthWest;
                    }
                }
            }
            else if (this.canMove(ChessMovement.moveSouthEast, currentPosition) && chessBoard[y][x + 1].chessPiece instanceof Pawn)
            {
                Pawn nextToPawn = (Pawn)chessBoard[y][x + 1].chessPiece;
                if ((nextToPawn.pastMoves.size() == 1) && (!nextToPawn.isTopSide))
                {
                    Pair<Integer, Integer> lastMove = nextToPawn.pastMoves.get(0);
                    int xPastMove = lastMove.getKey();
                    int yPastMove = lastMove.getValue();
                    if (xPastMove == 0 && yPastMove == 2)
                    {
                        return ChessMovement.moveSouthEast;
                    }
                }
            }
        }
        else
        {
            if (this.canMove(ChessMovement.moveNorthWest, currentPosition) && chessBoard[y][x - 1].chessPiece instanceof Pawn)
            {
                Pawn nextToPawn = (Pawn)chessBoard[y][x - 1].chessPiece;
                if ((nextToPawn.pastMoves.size() == 1) && (nextToPawn.isTopSide))
                {
                    Pair<Integer, Integer> lastMove = nextToPawn.pastMoves.get(0);
                    int xPastMove = lastMove.getKey();
                    int yPastMove = lastMove.getValue();
                    if (xPastMove == 0 && yPastMove == -2)
                    {
                        return ChessMovement.moveNorthEast;
                    }
                }
            }
            else if (this.canMove(ChessMovement.moveNorthEast, currentPosition) && chessBoard[y][x + 1].chessPiece instanceof Pawn)
            {
                Pawn nextToPawn = (Pawn)chessBoard[y][x + 1].chessPiece;
                if ((nextToPawn.pastMoves.size() == 1) && (nextToPawn.isTopSide))
                {
                    Pair<Integer, Integer> lastMove = nextToPawn.pastMoves.get(0);
                    int xPastMove = lastMove.getKey();
                    int yPastMove = lastMove.getValue();
                    if (xPastMove == 0 && yPastMove == -2)
                    {
                        return ChessMovement.moveNorthWest;
                    }
                }
            }
        }

        return null;
    }
}