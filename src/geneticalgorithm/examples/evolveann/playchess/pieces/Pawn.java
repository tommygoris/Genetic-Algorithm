/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import geneticalgorithm.examples.evolveann.playchess.ChessMovement;
import java.util.ArrayList;
import java.util.List;

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
        if (this.startingPosition.equals(currentPosition) && board.canMove(ChessMovement.moveSouthTwo, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveSouthTwo);
        }
        
        if (board.canMove(ChessMovement.moveSouth, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveSouth);
        }
        if (board.canCapture(ChessMovement.moveSouthEast, this.isTopSide, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveSouthEast);
        }
        
        if (board.canCapture(ChessMovement.moveSouthWest, this.isTopSide, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveSouthWest);
        }

        Pair<Integer, Integer> enPassantLocation = board.EnPassantPawnLocation(this.currentPosition, this.isTopSide);
        if (enPassantLocation != null)
        {
            this.possibleMoves.add(enPassantLocation);
        } 
    }
    
    private void UpdateBottomBoard(ChessBoard board){
        if (this.startingPosition.equals(currentPosition) && board.canMove(ChessMovement.moveNorthTwo, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveNorthTwo);
        }
        if (board.canMove(ChessMovement.moveNorth, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveNorth);
        }
        
        if (board.canCapture(ChessMovement.moveNorthEast, this.isTopSide, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveNorthEast);
        }
        
        if (board.canCapture(ChessMovement.moveNorthWest, this.isTopSide, this.currentPosition)){
            this.possibleMoves.add(ChessMovement.moveNorthWest);
        }

        Pair<Integer, Integer> enPassantLocation = board.EnPassantPawnLocation(this.currentPosition, this.isTopSide);
        if (enPassantLocation != null){
            this.possibleMoves.add(enPassantLocation);
        }
    }
    
    public List<AbstractChessPiece> PromotePawn(int x, int y, boolean isTopside, String color)
    {
        return new ArrayList<AbstractChessPiece>()
        {{
            add(new Queen(x, y, isTopSide, color));
            add(new Knight(x, y, isTopSide, color));
            add(new Rook(x, y, isTopSide, color));
            add(new Bishop(x, y, isTopSide, color));

        }};
    }
    
    @Override
    public String toString(){
        return "P" + "-" + this.color.charAt(0);
    }
}
