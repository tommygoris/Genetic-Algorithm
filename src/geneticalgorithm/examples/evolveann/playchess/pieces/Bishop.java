/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess.pieces;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class Bishop {
    public Pair<Integer, Integer> currentPosition;
    public ArrayList<Pair<Integer, Integer>> possibleMoves;
    public String color = "";
    
    public Bishop(int x, int y, String color){
        this.currentPosition = new Pair<>(x, y);
        this.possibleMoves = new ArrayList<>();
        this.color = color;
    }
}
