/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.problem.ProblemInterface;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class RandomIntArrayMutation implements MutationInterface{

    private final double mutationRate;
    private final int lowerBound;
    private final int upperBound;
    private final ProblemInterface fitnessFunction;
    public RandomIntArrayMutation(double mutationRate, int lowerBound, int upperBound, ProblemInterface<int[]> fitnessFunction){
        this.mutationRate = mutationRate;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.fitnessFunction = fitnessFunction;
    }
    @Override
    public Individual[] mutate(Population pop) {
        Individual[] newPop = new Individual[pop.population.length];
        for (int i = 0; i < pop.population.length; i++){
            int[] currentIndividual = (int[])pop.population[i].individual;
            for (int j = 0; j < currentIndividual.length; j++){
                currentIndividual[j] = (ThreadLocalRandom.current().nextDouble() < this.mutationRate) ? 
                        ThreadLocalRandom.current().nextInt(this.lowerBound, this.upperBound + 1) : currentIndividual[j];
            }
            double fitness = (double)this.fitnessFunction.fitnessFunction(currentIndividual);
            Individual<int[]> newIndividual = new Individual<>(currentIndividual, fitness);
            newPop[i] = newIndividual;
        }
        return newPop;
    }
    
}
