package geneticalgorithm.examples.onemax;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.StringCrossover;
import geneticalgorithm.mutation.RandomStringMutation;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.problem.ProblemUtility;
import geneticalgorithm.selections.RankSelection;
import geneticalgorithm.selections.TournamentSelection;
import geneticalgorithm.strategies.ElitismStrategy;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tommy
 */
public class OneMaxProblem {
    private static final int lengthOfProblem = 1000;
    private static final int populationSize = 100;
    private static final OneMaxFitnessFunction fitnessFunction = new OneMaxFitnessFunction();
    
    public static void main(String[] args){
        ProblemUtility utilities = new ProblemUtility();        
        TournamentSelection tournament = new TournamentSelection(7, 0.95);
        StringCrossover crossover = new StringCrossover(199, fitnessFunction, 1);
        RandomStringMutation mutation = new RandomStringMutation(0.005, 0, 1, fitnessFunction);
        OneMaxPopulation randomPopulation = new OneMaxPopulation(populationSize, lengthOfProblem, fitnessFunction);
        Population pop = new Population(randomPopulation, crossover, 50);
        int generation = 0;
        ElitismStrategy eliteStrategy = new ElitismStrategy(20);
        while(true){
            eliteStrategy.Strategy(pop);
            pop.population = pop.crossover(tournament);
            pop.population = pop.mutate(mutation);
            pop.population = eliteStrategy.getbestPop(pop);
            pop.bestFitness = utilities.getBestFitnessMax(pop);
            System.out.println(pop.bestFitness);
            if (pop.bestFitness == lengthOfProblem){
                System.out.println("Solution found in generation: " + generation);
                break;
            }
            printPopulation(pop);
            try{
                System.out.println(generation + "   " + pop.bestFitness);
                generation+=1;
                Thread.sleep(10);
            }

            catch (InterruptedException e){
            }
        
      }
        
    }
    
    private static void printPopulation(Population pop){
        for (int i = 0; i<pop.population.length; i++){
            System.out.println(pop.population[i].individual);
        }
    }
}

