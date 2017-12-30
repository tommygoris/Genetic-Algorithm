/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.IntArrayCrossover;
import geneticalgorithm.crossover.neuralcrossover.NeuralNetworkCrossover;
import geneticalgorithm.examples.nqueens.NQueensProblem;
import geneticalgorithm.mutation.RandomIntArrayMutation;
import geneticalgorithm.mutation.neuralmutation.AddNode;
import geneticalgorithm.mutation.neuralmutation.DeleteNode;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemUtility;
import geneticalgorithm.selections.TournamentSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class EvolveNeuralNetwork {
    //private static final int lengthOfProblem = 10;
    private static final int populationSize = 10000;
    private static final int startingNodes = 3;
    private static final int outputNodes = 1;
    //private static final NQueensFitnessFunction fitnessFunction = new NQueensFitnessFunction(lengthOfProblem);
    private static final double[] inputData = new double[]{0,0};
    private static final double[] resultsData = new double[]{1};
    private static final NeuralNetworkFitnessFunction fitnessFunction = new NeuralNetworkFitnessFunction(resultsData);
    private static final int solution = resultsData.length;
    
    public static void main(String[] args){ 
        TournamentSelection tournament = new TournamentSelection(7, 0.75);
        NeuralNetworkCrossover crossover = new NeuralNetworkCrossover(fitnessFunction, 0.75);
        AddNode addMutation = new AddNode(fitnessFunction, 0.75);
        DeleteNode deleteMutation = new DeleteNode(fitnessFunction, 0.25);
        Population pop = new Population(EvolveNeuralNetwork.createRandomPopulation(), crossover);
        check(pop, "0");
        int generation = 0;
        
        while(true){
        NeuralNetwork net = (NeuralNetwork)pop.population[0].individual;
        check(pop, "1");
        pop = EvolveNeuralNetwork.cleanUpHiddenLayer(pop);
        check(pop, "2");
        pop.population = pop.crossover(tournament);
        check(pop, "3");       
        pop.population = pop.mutate(addMutation);
        check(pop, "4");      
   //     pop.population = pop.mutate(deleteMutation);
        check(pop, "5");
        pop.bestFitness = ProblemUtility.getBestFitnessMax(pop);
        //System.out.println(pop.bestFitness);
        if (pop.bestFitness == solution){
            System.out.println("Solution found in generation: " + generation);
            System.out.print("The best Individual is ");
            break;
        }
        try{
            //System.out.println(generation + "   " + pop.bestFitness + "  " + solution);
            generation+=1;
            Thread.sleep(10);
        }
        
        catch (InterruptedException e){
        }
        
      }
        
        
    }
    
    private static Individual[] createRandomPopulation(){
        Individual[] randomPop = new Individual[EvolveNeuralNetwork.populationSize];
        for (int i = 0; i<randomPop.length; i++){
            randomPop[i] = EvolveNeuralNetwork.createRandomIndividual();
        }
        return randomPop;
    }
    
    private static Individual createRandomIndividual(){
        Individual ind = null;
        List<Integer> listPop = new ArrayList<>();
        NeuralNetwork net = new NeuralNetwork();
        net = NeuralNetworkUtilities.initTree(net, EvolveNeuralNetwork.inputData, EvolveNeuralNetwork.outputNodes);
        
        int numOfNodes = ThreadLocalRandom.current().nextInt(EvolveNeuralNetwork.startingNodes);
        for (int i = 0; i<numOfNodes; i++){
            int nodes = ThreadLocalRandom.current().nextInt(1, EvolveNeuralNetwork.startingNodes);
            listPop.add(nodes);
            numOfNodes -= nodes;            
        }    
        //System.out.println(Arrays.toString(listPop.stream().mapToInt(i->i).toArray()));
        net = NeuralNetworkUtilities.addHiddenNodes(net, listPop.stream().mapToInt(i->i).toArray());      
        
        net.createBias();
        
        if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
            if (net.hiddenNodes[0].length != net.inputs[0].branch.length){
                System.out.println("problem when creating individual 2");
            }
        }
        return new Individual(net, (double)fitnessFunction.fitnessFunction(net));
    }
    
    private static Population cleanUpHiddenLayer(Population pop){
        for (int ind = 0; ind<pop.population.length; ind++){
            int actualSize = 0;
            Node[][] newHiddenLayer = null;
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
            
            if (net.hiddenNodes == null){
                continue;
            }
            for (int i = 0; i<net.hiddenNodes.length; i++){
                if (net.hiddenNodes[i] != null || net.hiddenNodes[i].length > 0){
                    actualSize += 1;
                }
            }
            int x = 0;
            newHiddenLayer = new Node[actualSize][];
            for (int i = 0; i<net.hiddenNodes.length; i++){
                if (net.hiddenNodes[i] != null || net.hiddenNodes[i].length > 0){
                    newHiddenLayer[i] = new Node[net.hiddenNodes.length];              
                    newHiddenLayer[x++] = net.hiddenNodes[i];
                }
            }
            
            net.hiddenNodes = newHiddenLayer;
            pop.population[ind].individual = net;
        }
        return pop;
    }
    
    public static void check(Population pop, String location){
        for (int ind = 0; ind<pop.population.length; ind++)
        {
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
            if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
                return;
            }
            if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
                if (net.hiddenNodes[0].length != net.inputs[0].branch.length){
                    System.out.println("problem before" + location);
                }
            }

            for (int i = 0; i<net.hiddenNodes.length - 1; i++){
                    if (net.hiddenNodes[i][0].branch.length != net.hiddenNodes[i + 1].length){
                        System.out.println("problem before" + location);
                    }
            }

            if (net.hiddenNodes != null && net.hiddenNodes.length > 0){
                if (net.hiddenNodes[net.hiddenNodes.length - 1][0].branch.length != net.outputs.length){
                    System.out.println("problem before" + location);
                }
            } 
        }
    }
}
