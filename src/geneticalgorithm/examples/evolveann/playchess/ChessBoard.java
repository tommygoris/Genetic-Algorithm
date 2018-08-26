/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.pieces.Pawn;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class ChessBoard {

    private final int MAX_CHESSBOARD_SIZE = 8;
    public ChessSquare[][] chessBoard = new ChessSquare[MAX_CHESSBOARD_SIZE][MAX_CHESSBOARD_SIZE];
    
    public ChessBoard(){

    }
    
    public boolean canMove(int x, int y){
        return (x < MAX_CHESSBOARD_SIZE && y < MAX_CHESSBOARD_SIZE && x > 0 && y > 0) && !chessBoard[x][y].taken;
    }
    
    public boolean canMove(Pair<Integer, Integer> pair){
        int x = pair.getKey();
        int y = pair.getValue();
        return (x < MAX_CHESSBOARD_SIZE && y < MAX_CHESSBOARD_SIZE && x > 0 && y > 0) && !chessBoard[x][y].taken;
    }
    
    public boolean canCapture(int x, int y, boolean isTopSide){
        return (x < MAX_CHESSBOARD_SIZE && y < MAX_CHESSBOARD_SIZE && x > 0 && y > 0) &&
                chessBoard[x][y].taken && chessBoard[x][y].chessPiece.isTopSide != isTopSide;
    }
    
    public boolean canCapture(Pair<Integer, Integer> pair, boolean isTopSide){
        int x = pair.getKey();
        int y = pair.getValue();
        return (x < MAX_CHESSBOARD_SIZE && y < MAX_CHESSBOARD_SIZE && x > 0 && y > 0) && chessBoard[x][y].taken &&
                chessBoard[x][y].chessPiece.isTopSide != isTopSide;
    }

    public boolean canMoveOrCapture(Pair<Integer, Integer> pair, boolean isTopSide){
        return this.canCapture(pair, isTopSide) || this.canMove(pair);
    }

    public boolean canMoveOrCapture(int x, int y, boolean isTopSide){
        return this.canCapture(x,y,isTopSide) || this.canMove(x,y);
    }

    public Pair<Integer, Integer> EnPassantPawnLocation(Pair<Integer, Integer> currentPosition, boolean isTopSide){
        int x = currentPosition.getKey();
        int y = currentPosition.getValue();
        
        if (this.canCapture(x - 1, y, isTopSide) && this.chessBoard[x - 1][y].chessPiece instanceof Pawn){
            Pawn pawn = (Pawn)this.chessBoard[x - 1][y].chessPiece;
            if (pawn.pastMoves.size() == 1){
                    
                Pair<Integer, Integer> prevMove = pawn.pastMoves.get(0);                    
                int prevX = prevMove.getKey();                    
                int prevY = prevMove.getValue();
                if (pawn.isTopSide && prevX == 0 && prevY == -2){
                    return ChessMovement.moveNorthWest;
                }
                else if (!pawn.isTopSide && prevX == 0 && prevY == 2){
                    return ChessMovement.moveSouthWest;
                }
            }            
        }
        else if (this.canCapture(x + 1, y, isTopSide) && this.chessBoard[x + 1][y].chessPiece instanceof Pawn){
            Pawn pawn = (Pawn)this.chessBoard[x + 1][y].chessPiece;
            if (pawn.pastMoves.size() == 1){
                Pair<Integer, Integer> prevMove = pawn.pastMoves.get(0);
                int prevX = prevMove.getKey();
                int prevY = prevMove.getValue();
                if (pawn.isTopSide && prevX == 0 && prevY == -2){
                    return ChessMovement.moveNorthEast;
                }
                else if (!pawn.isTopSide && prevX == 0 && prevY == 2){
                    return ChessMovement.moveSouthEast;
                }
            }
        }
        return null;
    }
}