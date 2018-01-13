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
        this.color = color;
        possibleMoves = new ArrayList<>();
        if (color.equals("White")){
            this.possibleMoves.add(ChessMovement.moveNorthTwo);
            this.possibleMoves.add(ChessMovement.moveNorth);
        }
        else{
            this.possibleMoves.add(ChessMovement.moveSouth);
            this.possibleMoves.add(ChessMovement.moveSouthTwo);
        }
        
    }
    
    @Override
    public void UpdateMoves(ChessBoard board){
        this.possibleMoves = new ArrayList<>();        
        if (this.color.equals("White")){
            this.UpdateWhite(board);
        }
        else{
            this.UpdateBlack(board);
        }
    }
    
    private void UpdateBlack(ChessBoard board){
        if (this.startingPosition.equals(currentPosition) && board.canMove(ChessMovement.moveSouthTwo)){
            this.possibleMoves.add(ChessMovement.moveSouthTwo);
        }
        
        if (board.canMove(ChessMovement.moveSouth)){
            this.possibleMoves.add(ChessMovement.moveSouth);
        }
        if (board.canCapture(ChessMovement.moveSouthEast)){
            this.possibleMoves.add(ChessMovement.moveSouthEast);
        }
        
        if (board.canCapture(ChessMovement.moveSouthWest)){
            this.possibleMoves.add(ChessMovement.moveSouthWest);
        }
        
        if (board.EnPassantPawnLocation(this.currentPosition, this.color) != null){
            this.possibleMoves.add(board.EnPassantPawnLocation(this.currentPosition, this.color));
        } 
    }
    
    private void UpdateWhite(ChessBoard board){
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
        
        if (board.EnPassantPawnLocation(this.currentPosition, this.color) != null){
            this.possibleMoves.add(board.EnPassantPawnLocation(this.currentPosition, this.color));
        } 
    }
    
    private void PromotePawn(){
        
    }
    
    @Override
    public String toString(){
     return this.color.charAt(0) + "P";   
    }
}
