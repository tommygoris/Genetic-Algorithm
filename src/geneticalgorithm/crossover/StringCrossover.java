/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover;

import geneticalgorithm.Individual;
import geneticalgorithm.problem.ProblemInterface;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class StringCrossover implements CrossoverInterface {
    private final int crossoverPoints;
    private final ProblemInterface problem;
    private final double crossoverRate;
    public StringCrossover(int crossoverPoints, ProblemInterface<String> problem, double crossoverRate){
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
        StringBuilder newIndividual = new StringBuilder();    
        String firstIndividual = (String)first.individual;
        String secondIndividual = (String)second.individual;
        int[] crossoverLocations;
        int problemLength = firstIndividual.length();
        
        if (problemLength <= crossoverPoints){
            throw new IllegalArgumentException("Please make your crossover points less than the problem length. "
                    + "A problem length of " + "" + problemLength + " should have a max of " + "" + (problemLength - 1) + " crossover points and etc");
        }
        
        crossoverLocations = CrossoverSelector.getCrossoverLocations(problemLength, this.crossoverPoints);
        int previous = 0;
        int evenOrOdd = 0;
        for (int i = 0; i < crossoverLocations.length; i++){
            String currentIteration = (evenOrOdd%2==0) ? firstIndividual.substring(previous, crossoverLocations[i]) : secondIndividual.substring(previous, crossoverLocations[i]);
            newIndividual.append(currentIteration);
            evenOrOdd+=1;
            previous = crossoverLocations[i];
        }
        return new Individual(newIndividual.toString(), (double)problem.fitnessFunction(newIndividual.toString()));
    }
    
        public Individual crossoverTest(String firstIndividual, String secondIndividual, int[] crossoverLocations){
        StringBuilder newIndividual = new StringBuilder();
        int previous = 0;
        int evenOrOdd = 0;
        for (int i = 0; i < crossoverLocations.length; i++){
            String currentIteration = (evenOrOdd%2==0) ? firstIndividual.substring(previous, crossoverLocations[i]) : secondIndividual.substring(previous, crossoverLocations[i]);
            newIndividual.append(currentIteration);
            evenOrOdd+=1;
            previous = crossoverLocations[i];
        }
        return new Individual(newIndividual.toString(), (double)problem.fitnessFunction(newIndividual.toString()));
    }
   
}
