package geneticalgorithm.examples.nqueens;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.IntArrayCrossover;
import geneticalgorithm.examples.onemax.OneMaxFitnessFunction;
import geneticalgorithm.examples.onemax.OneMaxProblem;
import geneticalgorithm.mutation.RandomIntArrayMutation;
import geneticalgorithm.mutation.RandomStringMutation;
import geneticalgorithm.problem.ProblemUtility;
import geneticalgorithm.selections.RankSelection;
import geneticalgorithm.selections.TournamentSelection;
import geneticalgorithm.strategies.ElitismStrategy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Tommy
 */
public class NQueensProblem {

    private static final int lengthOfProblem = 20;
    private static final int populationSize = 5000;
    private static final NQueensFitnessFunction fitnessFunction = new NQueensFitnessFunction(lengthOfProblem);
    private static final int solution = (lengthOfProblem)*(lengthOfProblem-1) * 50;
    
    public static void main(String[] args) {
        fitnessFunction.initializeMap();
        ProblemUtility utilities = new ProblemUtility();  
        TournamentSelection tournament = new TournamentSelection(7, 0.80);
        //RankSelection rankSelection = new RankSelection();
        IntArrayCrossover crossover = new IntArrayCrossover(2, fitnessFunction, 0.90);
        NQueensPopulation randomPopulation = new NQueensPopulation(populationSize, lengthOfProblem, fitnessFunction);
        RandomIntArrayMutation mutation = new RandomIntArrayMutation(0.01, 0, lengthOfProblem, fitnessFunction);
        Population pop = new Population(randomPopulation, crossover, 500);
        ElitismStrategy eliteStrategy = new ElitismStrategy(10);
        int generation = 0;
        
        while(true){
            eliteStrategy.Strategy(pop);
            pop.population = pop.crossover(tournament);
            pop.population = pop.mutate(mutation);
            pop.population = eliteStrategy.getbestPop(pop);
            pop.bestFitness = utilities.getBestFitnessMax(pop);
            
            System.out.println(pop.bestFitness);
            if (pop.bestFitness == solution){
                System.out.println("Solution found in generation: " + generation);
                System.out.print("The best Individual is ");
                System.out.println(Arrays.toString((int[])pop.bestIndividual.individual));
                printBoard((int[])pop.bestIndividual.individual);
                break;
            }
            printPopulation(pop);
            try{
                System.out.println(generation + "   " + pop.bestFitness + "  " + solution);
                generation+=1;
                Thread.sleep(10);
            }

            catch (InterruptedException e){
            }

          }
    }
    
    private static void printPopulation(Population pop){
        for (int i = 0; i<pop.population.length; i++){
            System.out.println(Arrays.toString((int[])pop.population[i].individual));
        }
    }
    
    private static void printBoard(int[] bestIndividual){
        char[][] queenBoard = new char[lengthOfProblem][lengthOfProblem];
        
        for (int i = 0; i<lengthOfProblem; i++){
            for (int j = 0; j<lengthOfProblem; j++){
                if (j==bestIndividual[i]){
                    queenBoard[j][i] = 'X';
                }
                else{
                    queenBoard[j][i] = '_';
                }
            }
        }
        for (int i = 0; i<lengthOfProblem; i++){
            for (int j = 0; j<lengthOfProblem; j++){
                System.out.print(queenBoard[i][j]+ " ");
            }
            System.out.println();
        }        
    }
}
