/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.onemax;

import geneticalgorithm.Individual;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.problem.RandomPopulationInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class OneMaxPopulation implements RandomPopulationInterface {
    private int populationSize;
    private int lengthOfProblem;
    private ProblemInterface fitnessFunction;
    public OneMaxPopulation(int populationSize, int lengthOfProblem, ProblemInterface fitnessFunction){
        this.populationSize = populationSize;
        this.lengthOfProblem = lengthOfProblem;
        this.fitnessFunction = fitnessFunction;
    }
    
    
    public Individual[] createRandomPopulation(){
        Individual[] population = new Individual[populationSize];
        for (int i = 0; i <populationSize; i++){
            population[i] = createRandomIndividual();    
        }
        return population;
    }
    
    public Individual createRandomIndividual(){
        StringBuilder oneMax = new StringBuilder();
        for (int i = 0; i<lengthOfProblem; i++){
            oneMax.append(ThreadLocalRandom.current().nextDouble() > 0.5 ? 1 : 0);
        }
        double fitness = (double) fitnessFunction.fitnessFunction(oneMax.toString());
        return new Individual(oneMax.toString(), fitness);
    }
}
