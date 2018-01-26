/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.nqueens;

import geneticalgorithm.Individual;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.problem.RandomPopulationInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class NQueensPopulation implements RandomPopulationInterface {
    private int populationSize;
    private int lengthOfProblem;
    private ProblemInterface fitnessFunction;
    public NQueensPopulation(int populationSize, int lengthOfProblem, ProblemInterface fitnessFunction){
        this.populationSize = populationSize;
        this.lengthOfProblem = lengthOfProblem;
        this.fitnessFunction = fitnessFunction;
    }
    
    @Override
    public Individual[] createRandomPopulation(){
        Individual[] population = new Individual[populationSize];
        for (int i = 0; i <populationSize; i++){
            population[i] = createRandomIndividual();    
        }
        return population;
    }
    
    @Override
    public Individual createRandomIndividual(){
        int[] newIndividual = new int[lengthOfProblem];
        for (int i = 0; i<lengthOfProblem; i++){
            newIndividual[i] = ThreadLocalRandom.current().nextInt(0, this.lengthOfProblem);
        }
        double fitness = (double) fitnessFunction.fitnessFunction(newIndividual);
        return new Individual(newIndividual, fitness);
    }
}
