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
public class Pawn implements ChessPieceInterface{
    public ArrayList<Pair<Integer, Integer>> pastMoves = new ArrayList<>();
    public Pair<Integer, Integer> startingPosition;
    public Pair<Integer, Integer> currentPosition;
    public ArrayList<Pair<Integer, Integer>> possibleMoves;
    public String color = "";
    
    public Pawn(int x, int y, String color){
        startingPosition = new Pair<>(x,y);
        currentPosition = new Pair<>(x,y);
        
        this.possibleMoves = new ArrayList<>();
        this.possibleMoves.add(new Pair<>(0,2));
        this.possibleMoves.add(new Pair<>(0,1));
        this.color = color;
        
    }
    
    @Override
    public void UpdateMoves(ChessBoard board){
        if (this.color.equals("White")){
            this.UpdateWhite(board);
        }
        else{
            this.UpdateBlack(board);
        }
    }
    
    private void UpdateBlack(ChessBoard board){
        
    }
    
    private void UpdateWhite(ChessBoard board){
        int currentX = this.currentPosition.getKey();
        int currentY = this.currentPosition.getValue();
        
        this.possibleMoves = new ArrayList<>();
        
        if (this.startingPosition.equals(currentPosition) && board.canMove(ChessMovement.moveNorthTwo)){
            this.possibleMoves.add(ChessMovement.moveNorthTwo);
        }
        if (board.canMove(ChessMovement.moveNorth)){
            this.possibleMoves.add(ChessMovement.moveNorth);
        }
        
        if (board.canCapture(ChessMovement.moveNorthEast)){
            this.possibleMoves.add(ChessMovement.moveNorthEast);
        }
        
        if (board.canCapture(ChessMovement.moveNorthWest)){
            this.possibleMoves.add(ChessMovement.moveNorthWest);
        }
        
        if (board.canEnPassantPawn(this.currentPosition, this.color)){

        } 
    }
    
    @Override
    public String toString(){
     return this.color.charAt(0) + "P";   
    }
}
