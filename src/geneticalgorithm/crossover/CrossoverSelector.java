/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover;

import geneticalgorithm.Individual;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class CrossoverSelector {
    public final CrossoverInterface crossoverPop;
    /// Handles any type of crossover (single, two, three, etc) as long as the number
    /// of crossover points does not exceed the length of your problem space.
    public CrossoverSelector(CrossoverInterface crossoverPop){
        this.crossoverPop = crossoverPop;
    }
    
    public Individual crossover(Individual firstIndividual, Individual secondIndividual){
        return crossoverPop.crossover(firstIndividual, secondIndividual);
    }
    
    public static int[] getCrossoverLocations(int lengthOfProblem, int crossoverPoints){
        HashSet<Integer> setLocations = new HashSet<>();        
        while(setLocations.size() < crossoverPoints){
            int location = ThreadLocalRandom.current().nextInt(1, lengthOfProblem);
            if (!setLocations.contains(location)){
                setLocations.add(location);               
            }
        }
        setLocations.add(lengthOfProblem);
        int[] crossoverLocations = setLocations.stream().mapToInt(Number::intValue).toArray();
        Arrays.sort(crossoverLocations);
        return crossoverLocations;
    }
}
