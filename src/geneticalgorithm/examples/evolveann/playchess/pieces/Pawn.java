/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessMovement;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class Pawn extends AbstractChessPiece {
    
    public Pawn(int x, int y, boolean isTopSide, String color){
        super(x, y, isTopSide, color);
    }
    
    @Override
    public void UpdateMoves(ChessBoard board){
        this.possibleMoves.clear();
        if (!isTopSide){
            this.UpdateTopBoard(board);
        }
        else{
            this.UpdateBottomBoard(board);
        }
    }
    
    private void UpdateTopBoard(ChessBoard board){
        if (this.startingPosition.equals(currentPosition) && board.canMove(ChessMovement.moveSouthTwo)){
            this.possibleMoves.add(ChessMovement.moveSouthTwo);
        }
        
        if (board.canMove(ChessMovement.moveSouth)){
            this.possibleMoves.add(ChessMovement.moveSouth);
        }
        if (board.canCapture(ChessMovement.moveSouthEast, this.isTopSide)){
            this.possibleMoves.add(ChessMovement.moveSouthEast);
        }
        
        if (board.canCapture(ChessMovement.moveSouthWest, this.isTopSide)){
            this.possibleMoves.add(ChessMovement.moveSouthWest);
        }

        Pair<Integer, Integer> enPassantLocation = board.EnPassantPawnLocation(this.currentPosition, this.isTopSide);
        if (enPassantLocation != null){
            this.possibleMoves.add(enPassantLocation);
        } 
    }
    
    private void UpdateBottomBoard(ChessBoard board){
        if (this.startingPosition.equals(currentPosition) && board.canMove(ChessMovement.moveNorthTwo)){
            this.possibleMoves.add(ChessMovement.moveNorthTwo);
        }
        if (board.canMove(ChessMovement.moveNorth)){
            this.possibleMoves.add(ChessMovement.moveNorth);
        }
        
        if (board.canCapture(ChessMovement.moveNorthEast, this.isTopSide)){
            this.possibleMoves.add(ChessMovement.moveNorthEast);
        }
        
        if (board.canCapture(ChessMovement.moveNorthWest, this.isTopSide)){
            this.possibleMoves.add(ChessMovement.moveNorthWest);
        }

        Pair<Integer, Integer> enPassantLocation = board.EnPassantPawnLocation(this.currentPosition, this.isTopSide);
        if (enPassantLocation != null){
            this.possibleMoves.add(enPassantLocation);
        }
    }
    
    private void PromotePawn(){
        
    }
    
    @Override
    public String toString(){
        return "P" + "-" + this.color.charAt(0);
    }
}
