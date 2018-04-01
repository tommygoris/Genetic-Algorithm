/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.MLPNeuralNetwork.sinwave;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.crossover.neuralcrossover.NeuralNetworkCrossover;
import geneticalgorithm.examples.MLPNeuralNetwork.wordguess.EvolveNeuralNetworkTest;
import geneticalgorithm.examples.MLPNeuralNetwork.wordguess.NeuralNetworkFitnessFunction;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;
import geneticalgorithm.mutation.neuralmutation.AddNode;
import geneticalgorithm.mutation.neuralmutation.AlterWeights;
import geneticalgorithm.mutation.neuralmutation.DeleteNode;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.problem.ProblemUtility;
import geneticalgorithm.selections.TournamentSelection;
import geneticalgorithm.strategies.ElitismStrategy;
import geneticalgorithm.xml.XMLWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;

/**
 *
 * @author TommyGoris
 */
public class SinWaveNeuralNetwork{
    private static final int populationSize = 1000;
    private static final int inputSize = 50000;
    private static final int startingNodes = 1;
    private static final int outputNodes = 1;
    private static SinWaveFitnessFunction fitnessFunction = null;
    private static int solution = inputSize;
      
    public static void main(String[] args){
        Pair<List<Double[]>, List<Double>>  inputOutput = SinWaveNeuralNetwork.createSinData();
        List<Double[]> inputList = inputOutput.getKey();
        List<Double> outputList = inputOutput.getValue();
        fitnessFunction = new SinWaveFitnessFunction(outputList.toArray(new Double[0]), inputList.toArray(new Double[0][0]));
        TournamentSelection tournament = new TournamentSelection(7, .95);
        NeuralNetworkCrossover crossover = new NeuralNetworkCrossover(fitnessFunction, .95);
        AddNode addMutation = new AddNode(fitnessFunction, 0.005);
        DeleteNode deleteMutation = new DeleteNode(fitnessFunction, 0.005);
        SinWaveRandomPopulation randomPopulation = new SinWaveRandomPopulation(populationSize, startingNodes, 1, 1, fitnessFunction);
        Population pop = new Population(randomPopulation, crossover, 100);
        ElitismStrategy eliteStrategy = new ElitismStrategy(10);
        AlterWeights alterMutation = new AlterWeights(fitnessFunction, 0.025);
        int generation = 0;
        XMLWriter write = new XMLWriter();
        while(true){
            //pop = NeuralNetworkUtilities.cleanUpHiddenLayer(pop);
            eliteStrategy.Strategy(pop);
            pop.population = pop.crossover(tournament);   
            pop.population = pop.mutate(addMutation);  
            pop.population = pop.mutate(deleteMutation);
            pop.population = pop.mutate(alterMutation);
            pop.population = eliteStrategy.getbestPop(pop);
            pop.bestFitness = ProblemUtility.getBestFitnessMax(pop);
            write.writeNeuralNetwork((NeuralNetwork)pop.bestIndividual.individual,pop.bestFitness + "", "SinWave" );
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
    
    private static Pair<List<Double[]>, List<Double>> createSinData(){
        List<Double[]> inputList = new ArrayList<>();
        List<Double> outputList = new ArrayList<>();
        
        for (int i = 0; i<inputSize; i++){
            double x = ThreadLocalRandom.current().nextDouble(1000000000);
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
