/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover;

import geneticalgorithm.Individual;
import geneticalgorithm.problem.ProblemInterface;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class IntArrayCrossover implements CrossoverInterface {
    private final int crossoverPoints;
    private final ProblemInterface problem;
    private final double crossoverRate;
    public IntArrayCrossover(int crossoverPoints, ProblemInterface<int[]> problem, double crossoverRate){
        this.crossoverPoints = crossoverPoints;
        this.problem = problem;
        this.crossoverRate = crossoverRate;
    }

    @Override
    public Individual crossover(Individual first, Individual second) {   
        double crossoverCheck = ThreadLocalRandom.current().nextDouble();
        if (crossoverCheck > crossoverRate){
            return first.fitness > second.fitness ? first : second;
        }        
        int[] crossoverLocations;
        int[] firstIndividual = (int[])first.individual;
        int[] secondIndividual = (int[])second.individual;
        
        int problemLength = firstIndividual.length;  
        
        if (problemLength <= crossoverPoints){
            throw new IllegalArgumentException("Please make your crossover points less than the problem length. "
                    + "A problem length of " + "" + problemLength + " should have a max of " + "" + (problemLength - 1) + " crossover points and etc");
        }
        
        crossoverLocations = CrossoverSelector.getCrossoverLocations(problemLength, this.crossoverPoints);
        int[] newIndividual = new int[problemLength];

        int previousLocation = 0;
        int evenOrOdd = 0;
        for (int crossLocation : crossoverLocations){
            for (int i = previousLocation; i < crossLocation; i++){
                newIndividual[i] = (evenOrOdd%2==0) ? firstIndividual[i] : secondIndividual[i];               
            }
            evenOrOdd+=1;
            previousLocation = crossLocation;
        }
        
       
        return new Individual(newIndividual, (double)problem.fitnessFunction(newIndividual));
    }
    
    public Individual crossoverTest(int[] firstIndividual, int[] secondIndividual, int[] crossoverLocations){       
        int[] newIndividual = new int[firstIndividual.length];              
        Arrays.sort(crossoverLocations);
        int previousLocation = 0;
        int evenOrOdd = 0;
        for (int crossLocation : crossoverLocations){
            for (int i = previousLocation; i < crossLocation; i++){
                newIndividual[i] = (evenOrOdd%2==0) ? firstIndividual[i] : secondIndividual[i];               
            }
            evenOrOdd+=1;
            previousLocation = crossLocation;
        }
        System.out.println(Arrays.toString(newIndividual));
        return new Individual(newIndividual, (double)problem.fitnessFunction(newIndividual));
    }


    
}
