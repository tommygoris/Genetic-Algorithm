/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess.pieces;

import geneticalgorithm.examples.evolveann.playchess.ChessBoard;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class Pawn {
    public Pair<Integer, Integer> startingPosition;
    public Pair<Integer, Integer> currentPosition;
    public ArrayList<Pair<Integer, Integer>> possibleMoves;
    
    public Pawn(int x, int y){
        startingPosition = new Pair<>(x,y);
        currentPosition = new Pair<>(x,y);
        this.InitialMoves();
    }
    
    private void InitialMoves(){
        this.possibleMoves = new ArrayList<>();
        this.possibleMoves.add(new Pair<>(0,2));
        this.possibleMoves.add(new Pair<>(0,1));
    }
    
    public void UpdateMoves(ChessBoard board){
        int currentX = this.currentPosition.getKey();
        int currentY = this.currentPosition.getValue();
        
        this.possibleMoves = new ArrayList<>();
        
        if (this.startingPosition.equals(currentPosition)){
            this.possibleMoves.add(new Pair<>(0,2));
        }
        this.possibleMoves.add(new Pair<>(0,1));
        
        if (board.chessBoard.length > currentX && 
            board.chessBoard.length > currentY && 
            board.chessBoard[currentX][currentY].length() > 0){
            
            this.possibleMoves.add(new Pair<>(1,1));
        }
    }
}
