/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.nqueens;

import geneticalgorithm.problem.ProblemInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import javafx.util.Pair;

/**
 *
 * @author Tommy
 */
public class NQueensFitnessFunction implements ProblemInterface {

    private final HashMap<Pair<Integer, Integer>, HashSet<Pair<Integer, Integer>>> checkBoard = new HashMap<>();
    private final int length;
    public NQueensFitnessFunction(int length){
        this.length = length;
    }
        
    public void initializeMap(){
        Pair<Integer, Integer> checkPair;
        HashSet<Pair<Integer, Integer>> checkSet = new HashSet<>();
        for (int i = 0; i<length; i++){            
            for (int j = 0; j<length; j++){
                this.down(i, j, checkSet);
                this.up(i, j, checkSet);
                this.left(i, j, checkSet);
                this.right(i, j, checkSet);
                this.upLeft(i, j, checkSet);
                this.upRight(i, j, checkSet);
                this.downLeft(i, j, checkSet);
                this.downRight(i, j, checkSet);
                checkBoard.put(new Pair<>(i, j), checkSet);
                checkSet = new HashSet<>();
            }
        }
    }
    
    private void upLeft(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        upLeft(x-1, y+1, checkSet);
    }
    
    private void upRight(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        upRight(x+1, y+1, checkSet);
    }
    
    private void downLeft(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        downLeft(x-1, y-1, checkSet);
    }
        
    private void downRight(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        downRight(x+1, y-1, checkSet);
    }
    private void up(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        up(x, y+1, checkSet);
    }
    private void down(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        down(x, y-1, checkSet);
    }  
    private void left(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        left(x-1, y, checkSet);
    }    
    private void right(int x, int y, HashSet<Pair<Integer, Integer>> checkSet){
        if (x < 0 || x >= length || y >= length || y < 0) {
            return;
        }
        checkSet.add(new Pair(x,y));
        right(x+1, y, checkSet);
    }
    
    @Override
    public Object fitnessFunction(Object value) {
        int[] individual = (int[])value;
        double fitness = 0;
        for (int i = 0; i<length; i++){
            HashSet<Pair<Integer,Integer>> checkSet = checkBoard.get(new Pair<>(i, individual[i]));
            for (int j = 0; j<length; j++){
                Pair<Integer, Integer> checkPair = new Pair<>(j,individual[j]);
                if (!checkSet.contains(checkPair)){
                    fitness+=50;
                }
            }
            
        }
        return fitness;
    }
    
}
