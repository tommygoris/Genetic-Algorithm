/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.problem;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;

/**
 *
 * @author Tommy
 */
public class ProblemUtility {
    
    public static double getBestFitnessMax(Population pop){
        double fitness = Double.MIN_VALUE;
        for (int i = 0; i<pop.population.length; i++){
            if (pop.population[i].fitness > fitness){
                fitness = pop.population[i].fitness;
                ProblemUtility.getBestIndividual(pop, pop.population[i]);
            }
        }
        return fitness;
    }
    
    public static double getBestFitnessMin(Population pop){
        double fitness = Double.MAX_VALUE;
        for (int i = 0; i<pop.population.length; i++){
            if (pop.population[i].fitness < fitness){
                fitness = pop.population[i].fitness;
                ProblemUtility.getBestIndividual(pop, pop.population[i]);
            }
        }
        return fitness;
    }
        
    private static void getBestIndividual(Population pop, Individual bestIndv){
        pop.bestIndividual = bestIndv;
    }
}
