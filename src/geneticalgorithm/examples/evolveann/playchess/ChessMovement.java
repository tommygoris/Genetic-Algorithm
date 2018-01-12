/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.playchess;

import javafx.util.Pair;

/**
 *
 * @author TommyGoris
 */
public class ChessMovement {
    
    public static Pair<Integer, Integer> moveNorth = new Pair<Integer, Integer>(0,1);
    public static Pair<Integer, Integer> moveEast = new Pair<Integer, Integer>(1,0);
    public static Pair<Integer, Integer> moveSouth = new Pair<Integer, Integer>(0,-1);
    public static Pair<Integer, Integer> moveWest = new Pair<Integer, Integer>(-1,0);
    public static Pair<Integer, Integer> moveNorthEast = new Pair<Integer, Integer>(1,1);
    public static Pair<Integer, Integer> moveSouthEast = new Pair<Integer, Integer>(1,-1);
    public static Pair<Integer, Integer> moveSouthWest = new Pair<Integer, Integer>(-1,-1);
    public static Pair<Integer, Integer> moveNorthWest = new Pair<Integer, Integer>(-1,1);
    public static Pair<Integer, Integer> moveNorthTwo = new Pair<Integer, Integer>(0,2);
    public static Pair<Integer, Integer> moveSouthTwo = new Pair<Integer, Integer>(0,-2);
}
