/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.sinwave;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.neuralcrossover.NeuralNetworkCrossover;
import geneticalgorithm.examples.evolveann.EvolveNeuralNetworkTest;
import geneticalgorithm.examples.evolveann.NeuralNetworkFitnessFunction;
import geneticalgorithm.examples.evolveann.NeuralNetworkUtilities;
import geneticalgorithm.mutation.neuralmutation.AddNode;
import geneticalgorithm.mutation.neuralmutation.DeleteNode;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.problem.ProblemUtility;
import geneticalgorithm.selections.TournamentSelection;
import geneticalgorithm.strategies.ElitismStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;

/**
 *
 * @author TommyGoris
 */
public class SinWaveNeuralNetwork{
    private static final int populationSize = 15;
    private static final int inputSize = 50;
    private static final int startingNodes = 1;
    private static final int outputNodes = 1;
    private static SinWaveFitnessFunction fitnessFunction = null;
    private static int solution = inputSize;
      
    public static void main(String[] args){
        Pair<List<Double[]>, List<Double>>  inputOutput = SinWaveNeuralNetwork.createSinData();
        List<Double[]> inputList = inputOutput.getKey();
        List<Double> outputList = inputOutput.getValue();
        fitnessFunction = new SinWaveFitnessFunction(outputList.toArray(new Double[0]), inputList.toArray(new Double[0][0]));
        TournamentSelection tournament = new TournamentSelection(7, .80);
        NeuralNetworkCrossover crossover = new NeuralNetworkCrossover(fitnessFunction, .80);
        AddNode addMutation = new AddNode(fitnessFunction, 0.25);
        DeleteNode deleteMutation = new DeleteNode(fitnessFunction, 0.1);
        Population pop = new Population(SinWaveNeuralNetwork.createRandomPopulation(inputOutput.getKey().toArray(new Double[0][0])), crossover);
        ElitismStrategy eliteStrategy = new ElitismStrategy(10);
        int generation = 0;
        while(true){
            //pop = NeuralNetworkUtilities.cleanUpHiddenLayer(pop);
            eliteStrategy.Strategy(pop);
            pop.population = pop.crossover(tournament);   
            pop.population = pop.mutate(addMutation);  
            pop.population = pop.mutate(deleteMutation);
            pop.population = eliteStrategy.getbestPop(pop);
            pop.bestFitness = ProblemUtility.getBestFitnessMax(pop);
            System.out.println(pop.bestFitness + " Generation: " + generation);
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
    
    private static Individual[] createRandomPopulation(Double[][] inputData){
        Individual[] randomPop = new Individual[SinWaveNeuralNetwork.populationSize];
        for (int i = 0; i<randomPop.length; i++){
            randomPop[i] = SinWaveNeuralNetwork.createRandomIndividual(inputData);
        }
        return randomPop;
    }
    
    private static Individual createRandomIndividual(Double[][] inputData){
        Individual ind = null;
        List<Integer> listPop = new ArrayList<>();
        NeuralNetwork net = new NeuralNetwork();
        net = NeuralNetworkUtilities.initTree(net, inputData[0], SinWaveNeuralNetwork.outputNodes);
        
        int numOfNodes = ThreadLocalRandom.current().nextInt(SinWaveNeuralNetwork.startingNodes);
        for (int i = 0; i<numOfNodes; i++){
            int nodes = ThreadLocalRandom.current().nextInt(1, SinWaveNeuralNetwork.startingNodes);
            listPop.add(nodes);
            numOfNodes -= nodes;            
        }    
        //System.out.println(Arrays.toString(listPop.stream().mapToInt(i->i).toArray()));
        net = NeuralNetworkUtilities.addHiddenNodes(net, listPop.stream().mapToInt(i->i).toArray());      
        
        net.createBias();
        
        return new Individual(net, (double)fitnessFunction.fitnessFunction(net));
    }
    
    private static Pair<List<Double[]>, List<Double>> createSinData(){
        List<Double[]> inputList = new ArrayList<>();
        List<Double> outputList = new ArrayList<>();
        
        for (int i = 0; i<inputSize; i++){
            double x = ThreadLocalRandom.current().nextDouble(100000000);
            double y = Math.sin(x);
            List<Double> interList = new ArrayList<>();
            Double[] interArray = new Double[1];
            interArray[0] = x;
            outputList.add(y);
            inputList.add(interArray);
        }
        return new Pair<>(inputList, outputList);
    }
}
