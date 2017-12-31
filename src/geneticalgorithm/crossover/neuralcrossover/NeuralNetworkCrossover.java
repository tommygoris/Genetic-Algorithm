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

        NeuralNetwork newNet = new NeuralNetwork();
        int sizeOfHidden = Math.max(firstNet.hiddenNodes.length, secondNet.hiddenNodes.length);
        newNet.hiddenNodes = new Node[sizeOfHidden][];
        if (firstNet.inputs[0].branch.length >= secondNet.inputs[0].branch.length){
            newNet.inputs = new Node[firstNet.inputs.length];
            for (int i = 0; i<firstNet.inputs.length; i++){
                newNet.inputs[i] = new Node(firstNet.inputs[i].val);
            }
            newNet.outputs = new Node[firstNet.outputs.length];
            for (int i = 0; i<firstNet.outputs.length; i++){
                newNet.outputs[i] = new Node(firstNet.outputs[i].val);
            }
        }
        else {
            newNet.inputs = new Node[secondNet.inputs.length];
            for (int i = 0; i<secondNet.inputs.length; i++){
                newNet.inputs[i] = new Node(secondNet.inputs[i].val);
            }
            
            newNet.outputs = new Node[secondNet.outputs.length];
            for (int i = 0; i<secondNet.outputs.length; i++){
                newNet.outputs[i] = new Node(secondNet.outputs[i].val);
            }
        }
        
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
                    newNet.hiddenNodes[i][x] = new Node(secondNet.hiddenNodes[i][x].val);
                }
                else if (i >= secondNet.hiddenNodes.length){
                    newNet.hiddenNodes[i][x] = new Node(firstNet.hiddenNodes[i][x].val);
                }
                // 50% chance to choose a node from either neural network.
                else if (x < firstNet.hiddenNodes[i].length && x < secondNet.hiddenNodes[i].length){
                    newNet.hiddenNodes[i][x] = (ThreadLocalRandom.current().nextDouble() > 0.5) 
                            ? new Node(firstNet.hiddenNodes[i][x].val) : new Node(secondNet.hiddenNodes[i][x].val);
                }
                else if (x < firstNet.hiddenNodes[i].length && x >= secondNet.hiddenNodes[i].length){
                    newNet.hiddenNodes[i][x] = new Node(firstNet.hiddenNodes[i][x].val);
                }
                else if (x >= firstNet.hiddenNodes[i].length && x < secondNet.hiddenNodes[i].length){
                    newNet.hiddenNodes[i][x] = new Node(secondNet.hiddenNodes[i][x].val);
                }      
            }
        }        
        firstNet = null;
        secondNet = null;
        
        if (newNet.hiddenNodes == null || newNet.hiddenNodes.length == 0){
            NeuralNetworkUtilities.reconnectLayer(newNet.inputs, newNet.outputs);
        }
        else{
            NeuralNetworkUtilities.reconnectLayer(newNet.inputs, newNet.hiddenNodes[0]);
            for (int i = 0; i<newNet.hiddenNodes.length - 1; i++){
                NeuralNetworkUtilities.reconnectLayer(newNet.hiddenNodes[i], newNet.hiddenNodes[i + 1]);
            }
            
            NeuralNetworkUtilities.reconnectLayer(newNet.hiddenNodes[newNet.hiddenNodes.length - 1], newNet.outputs);
        }
        newNet.createBias();
        return new Individual(newNet, (double)this.fitnessFunction.fitnessFunction(newNet));
    }
}
