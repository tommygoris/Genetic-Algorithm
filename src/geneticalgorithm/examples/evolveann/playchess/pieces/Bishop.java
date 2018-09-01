/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess.pieces;

import java.util.ArrayList;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessMovement;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class Bishop extends AbstractChessPiece {
    
    public Bishop(int x, int y, boolean isTopSide, String color){
        super(x, y, isTopSide, color);
    }

    @Override
    public void UpdateMoves(ChessBoard board) {
        this.possibleMoves.clear();
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesNorthWestDiagonol(board, this.isTopSide, this.currentPosition));
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesNorthEastDiagonol(board, this.isTopSide, this.currentPosition));
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesSouthWestDiagonol(board, this.isTopSide, this.currentPosition));
        this.possibleMoves.addAll(ChessMovement.GetAllPossibleMovesSouthEastDiagonol(board, this.isTopSide, this.currentPosition));
    }

    @Override
    public String toString(){
        return "B" + "-" + this.color.charAt(0);
    }
}
