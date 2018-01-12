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
 
    public ChessSquare[][] chessBoard = new ChessSquare[8][8];
    
    public ChessBoard(){

    }
    
    public boolean canMove(int x, int y){
        return (x < 8 && y < 8 && x > 0 && y > 0) && !chessBoard[x][y].taken;
    }
    
    public boolean canMove(Pair<Integer, Integer> pair){
        int x = pair.getKey();
        int y = pair.getValue();
        return (x < 8 && y < 8 && x > 0 && y > 0) && !chessBoard[x][y].taken;
    }
    
    public boolean canCapture(Pair<Integer, Integer> pair){
        int x = pair.getKey();
        int y = pair.getValue();
        return (x < 8 && y < 8 && x > 0 && y > 0) && chessBoard[x][y].taken;
    }
    
    public boolean canEnPassantPawn(Pair<Integer, Integer> currentPosition, String color){
        int x = currentPosition.getKey();
        int y = currentPosition.getValue();
        
        if (this.chessBoard[x - 1][y].chessPiece instanceof Pawn){
            Pawn pawn = (Pawn)this.chessBoard[x - 1][y].chessPiece;
            if (pawn.pastMoves.size() == 1){
                Pair<Integer, Integer> prevMove = pawn.pastMoves.get(0);
                int prevX = prevMove.getKey();
                int prevY = prevMove.getValue();
                if (prevX == 0 && prevY == 2){
                    
                }
            }
        }
        return false;
    }
}