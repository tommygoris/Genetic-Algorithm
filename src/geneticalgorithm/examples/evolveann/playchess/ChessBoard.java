/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

/**
 *
 * @author Tommy
 */
public class ChessBoard {
 
    public String[][] chessBoard = new String[8][8];
    
    public ChessBoard(){
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                chessBoard[i][j] = i + j + "";
            }
        }
    }
}
