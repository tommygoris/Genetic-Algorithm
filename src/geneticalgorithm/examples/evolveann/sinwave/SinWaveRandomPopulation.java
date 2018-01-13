/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.sinwave;

import geneticalgorithm.examples.evolveann.*;
import geneticalgorithm.Individual;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.problem.ProblemInterface;
import geneticalgorithm.problem.RandomPopulationInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class SinWaveRandomPopulation implements RandomPopulationInterface {

    private int populationSize;
    private int startingNodes;
    private int inputNodes;
    private int outputNodes;
    private ProblemInterface fitnessFunction;
    
    public SinWaveRandomPopulation(int populationSize, int startingNodes, int inputNodes, int outputNodes, ProblemInterface fitnessFunction){
        this.populationSize = populationSize;
        this.startingNodes = startingNodes;
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;
        this.fitnessFunction = fitnessFunction;
    }
    
    public Individual[] createRandomPopulation(){
        Individual[] randomPop = new Individual[populationSize];
        for (int i = 0; i<randomPop.length; i++){
            randomPop[i] = createRandomIndividual();
        }
        return randomPop;
    }
    
    public Individual createRandomIndividual(){
        List<Integer> listPop = new ArrayList<>();
        NeuralNetwork net = new NeuralNetwork();
        Double[] input = new Double[inputNodes];
        Arrays.fill(input, 0.0);
        net = NeuralNetworkUtilities.initTree(net, input, outputNodes);
        
        int numOfNodes = ThreadLocalRandom.current().nextInt(startingNodes);
        for (int i = 0; i<numOfNodes; i++){
            int nodes = ThreadLocalRandom.current().nextInt(1, startingNodes);
            listPop.add(nodes);
            numOfNodes -= nodes;            
        }    
        net = NeuralNetworkUtilities.addHiddenNodes(net, listPop.stream().mapToInt(i->i).toArray());      
        
        net.createBias();
        
        return new Individual(net, (double)fitnessFunction.fitnessFunction(net));
    }
}
