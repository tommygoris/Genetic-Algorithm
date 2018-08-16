/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import geneticalgorithm.examples.evolveann.playchess.pieces.ChessPieceInterface;

/**
 *
 * @author TommyGoris
 */
public class ChessSquare {
    
    public boolean taken = false;
    public ChessPieceInterface chessPiece = null;
    public ChessSquare(){
        
    }
    
    public ChessSquare(ChessPieceInterface chessPiece){
        this.chessPiece = chessPiece;
        this.taken = true;
    }
}
