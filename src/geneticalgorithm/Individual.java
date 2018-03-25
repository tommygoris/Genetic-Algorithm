/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import java.util.Arrays;

/**
 *
 * @author Tommy
 */
public class Individual<Generic> implements Comparable<Generic> {
    public Generic individual;
    public double fitness;
    
    public Individual(Generic individual, double fitness){
        this.individual = individual;
        this.fitness = fitness;
    }
    
    @Override
    public boolean equals(Object obj){
        Individual o = (Individual)obj;
        if (o.individual instanceof int[] && this.individual instanceof int[]){
            int[] compare = (int[])o.individual;
            int[] thisCompare = (int[])this.individual;
            if (compare.length > 0 && thisCompare.length > 0){
                return Arrays.equals(thisCompare, compare);
            }
        }
        else if (o.individual instanceof String && this.individual instanceof String) {
            String compare = (String)o.individual;
            String thisCompare = (String)this.individual;
            if (compare.length() > 0 && thisCompare.length() > 0){
                return compare.equals(thisCompare);
            }
        }
        else if (o.individual instanceof NeuralNetwork && this.individual instanceof NeuralNetwork){
            NeuralNetwork compare = (NeuralNetwork)o.individual;
            NeuralNetwork thisCompare = (NeuralNetwork)this.individual;   
            
            return NeuralNetworkUtilities.deepCheckNet(compare, thisCompare);
        }
        return false;
    }

    @Override
    public int compareTo(Object obj) {
           Individual o = (Individual)obj;
           return Double.compare(this.fitness, o.fitness);
           
    }
    
    @Override
    public String toString(){
        return this.fitness + " ";
    }
    
}
