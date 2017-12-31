/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover.neuralcrossover;

import geneticalgorithm.Individual;
import geneticalgorithm.crossover.CrossoverInterface;
import geneticalgorithm.examples.evolveann.NeuralNetworkUtilities;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemInterface;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class NeuralNetworkCrossover implements CrossoverInterface {
    private final ProblemInterface fitnessFunction;
    private final double crossoverRate;
    public NeuralNetworkCrossover(ProblemInterface<NeuralNetwork> fitnessFunction, double crossoverRate){
        this.fitnessFunction = fitnessFunction;
        this.crossoverRate = crossoverRate;
    }
    
    
    public Individual crossover(Individual first, Individual second){
        double crossoverCheck = ThreadLocalRandom.current().nextDouble();
        if (crossoverRate <crossoverCheck){
            return first.fitness > second.fitness ? first : second;
        }
        
        NeuralNetwork firstNet = (NeuralNetwork)first.individual;
        NeuralNetwork secondNet = (NeuralNetwork)second.individual;
        
        // No point in doing a crossover if either net has no hidden nodes to crossover.
        if (firstNet.hiddenNodes == null || firstNet.hiddenNodes.length == 0){
            return second;
        }
        else if (secondNet.hiddenNodes == null || secondNet.hiddenNodes.length == 0){
            return first;
        }

        NeuralNetwork newNet = null;
        
        if (firstNet.inputs[0].branch.length >= secondNet.inputs[0].branch.length){
            newNet = new NeuralNetwork(firstNet.inputs, firstNet.outputs);
        }
        else {
            newNet = new NeuralNetwork(secondNet.inputs, secondNet.outputs);
        }
        
        int sizeOfHidden = Math.max(firstNet.hiddenNodes.length, secondNet.hiddenNodes.length);
        
        newNet.hiddenNodes = new Node[sizeOfHidden][];
        
        for (int i = 0; i<sizeOfHidden; i++){
            int maxSize = 0;
            if (i < firstNet.hiddenNodes.length &&  i < secondNet.hiddenNodes.length){
                maxSize = Math.max(firstNet.hiddenNodes[i].length, secondNet.hiddenNodes[i].length);
            }
            else if (i < firstNet.hiddenNodes.length && i >= secondNet.hiddenNodes.length){
                maxSize = firstNet.hiddenNodes[i].length;
            }
            else if (i >= firstNet.hiddenNodes.length && i <= secondNet.hiddenNodes.length){
                maxSize = secondNet.hiddenNodes[i].length;
            }            
            newNet.hiddenNodes[i] = new Node[maxSize];
            
            for (int x = 0; x<maxSize; x++){
                if (i >= firstNet.hiddenNodes.length){
                    newNet.hiddenNodes[i][x] = new Node(secondNet.hiddenNodes[i][x]);
                }
                else if (i >= secondNet.hiddenNodes.length){
                    newNet.hiddenNodes[i][x] = new Node(firstNet.hiddenNodes[i][x]);
                }
                // 50% chance to choose a node from either neural network.
                else if (x < firstNet.hiddenNodes[i].length && x < secondNet.hiddenNodes[i].length){
                    newNet.hiddenNodes[i][x] = (ThreadLocalRandom.current().nextDouble() > 0.5) 
                            ? new Node(firstNet.hiddenNodes[i][x]) : new Node(secondNet.hiddenNodes[i][x]);
                }
                else if (x < firstNet.hiddenNodes[i].length && x >= secondNet.hiddenNodes[i].length){
                    newNet.hiddenNodes[i][x] = new Node(firstNet.hiddenNodes[i][x]);
                }
                else if (x >= firstNet.hiddenNodes[i].length && x < secondNet.hiddenNodes[i].length){
                    newNet.hiddenNodes[i][x] = new Node(secondNet.hiddenNodes[i][x]);
                }      
            }
        }        
        NeuralNetworkUtilities.redoBranches(newNet);
        newNet.createBias();
        return new Individual(newNet, (double)this.fitnessFunction.fitnessFunction(newNet));
    }
}
