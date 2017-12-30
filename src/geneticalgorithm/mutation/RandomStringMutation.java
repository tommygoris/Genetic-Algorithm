/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.problem.ProblemInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class RandomStringMutation implements MutationInterface{
    
    private final double mutationRate;
    private final int lowerBound;
    private final int upperBound;
    private final ProblemInterface fitnessFunction;
    public RandomStringMutation(double mutationRate, int lowerBound, int upperBound, ProblemInterface<String> fitnessFunction){
        this.mutationRate = mutationRate;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.fitnessFunction = fitnessFunction;
    }
    @Override
    public Individual[] mutate(Population pop) {
        Individual[] newPop = new Individual[pop.population.length];
        StringBuilder newIndv = new StringBuilder();
        for (int i = 0; i < pop.population.length; i++){
            String currentIndividual = (String)pop.population[i].individual;
            for (int j = 0; j < currentIndividual.length(); j++){
                newIndv.append((ThreadLocalRandom.current().nextDouble() < mutationRate) ? 
                        ThreadLocalRandom.current().nextInt(this.lowerBound, this.upperBound + 1) : currentIndividual.charAt(j) - '0');
            }
            System.out.println(newIndv.toString());
            double fitness = (double)this.fitnessFunction.fitnessFunction(newIndv.toString());
            newPop[i] = new Individual<>(newIndv.toString(), fitness);          
            newIndv.setLength(0);
        }
        return newPop;
    }
   
    
}
