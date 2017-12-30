/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.examples.evolveann.NeuralNetworkFitnessFunction;
import geneticalgorithm.examples.evolveann.NeuralNetworkUtilities;
import geneticalgorithm.mutation.MutationInterface;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class DeleteNode implements MutationInterface {
    private final ProblemInterface fitnessFunction;
    private final double mutationRate;
    
    public DeleteNode(){
        this.fitnessFunction = new NeuralNetworkFitnessFunction(new double[]{1});
        this.mutationRate = 1;
    }
    public DeleteNode(ProblemInterface<NeuralNetwork> fitnessFunction, double mutationRate){
        this.fitnessFunction = fitnessFunction;
        this.mutationRate = mutationRate;
    }    
    
    public Individual[] mutate(Population pop){
        Individual[] newPop = new Individual[pop.population.length];
        
        for (int ind = 0; ind<pop.population.length; ind++){
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;

            // No hidden nodes to delete. Return the net.
            if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
                newPop[ind] = pop.population[ind];
                continue;
            }
            
            int layerOfNode = ThreadLocalRandom.current().nextInt(net.hiddenNodes.length);
            List<Node> removeNode = new LinkedList<Node>(Arrays.asList(net.hiddenNodes[layerOfNode]));

            if (removeNode.isEmpty()){
                newPop[ind] = pop.population[ind];
                continue;
            }
            
            int locationOfNode = ThreadLocalRandom.current().nextInt(removeNode.size());
            
            removeNode.remove(locationOfNode);
            
            if (removeNode.isEmpty()){
                Node[][] newLayer = null;
                if (net.hiddenNodes.length == 1){
                    
                    for (int i = 0; i<net.inputs.length; i++){
                        net.inputs[i].branch = new Node[net.outputs.length];
                        net.inputs[i].connection = new double[net.outputs.length];
                        for (int j=0; j<net.outputs.length; j++){
                            net.inputs[i].connection[j] = ThreadLocalRandom.current().nextGaussian();
                            net.inputs[i].branch[j] = net.outputs[j]; 
                        }
                    }
                }                
                else if (layerOfNode == 0 || net.hiddenNodes.length <= 2){
                    List<Node[]> removeLayer = new LinkedList<Node[]>(Arrays.asList(net.hiddenNodes));
                    removeLayer.remove(layerOfNode);
                    newLayer = new Node[removeLayer.size()][];
                    
                    for (int i = 0; i<newLayer.length; i++){
                        Node[] layer = removeLayer.get(i);
                        newLayer[i] = new Node[layer.length];
                        for (int x = 0; x<newLayer[i].length; x++){
                            newLayer[i][x] = layer[x];
                        }                                
                    }
                    
                    for (int i = 0; i<net.inputs.length; i++){
                        net.inputs[i].branch = new Node[newLayer[0].length];
                        net.inputs[i].connection = new double[newLayer[0].length];
                        for (int x = 0; x<newLayer[0].length; x++){
                            net.inputs[i].connection[x] = ThreadLocalRandom.current().nextGaussian();
                            net.inputs[i].branch[x] = newLayer[0][x];
                        }
                    }
                }
                else if (layerOfNode == net.hiddenNodes.length - 1){
                    List<Node[]> removeLayer = new LinkedList<Node[]>(Arrays.asList(net.hiddenNodes));
                    removeLayer.remove(layerOfNode);
                    newLayer = new Node[removeLayer.size()][];
                    
                    for (int i = 0; i<newLayer.length; i++){
                        Node[] layer = removeLayer.get(i);
                        newLayer[i] = new Node[layer.length];
                        for (int x = 0; x<newLayer[i].length; x++){
                            newLayer[i][x] = layer[x];
                        }                                
                    }
                    
                    for (int i = 0; i<newLayer[layerOfNode - 1].length; i++){
                        newLayer[layerOfNode - 1][i].branch = new Node[net.outputs.length];
                        newLayer[layerOfNode - 1][i].connection = new double[net.outputs.length];
                        for (int x = 0; x<net.outputs.length; x++){
                            newLayer[layerOfNode - 1][i].branch[x] = net.outputs[x];
                            newLayer[layerOfNode - 1][i].connection[x] = ThreadLocalRandom.current().nextGaussian();
                        }
                    }                  
                }
                else {
                    List<Node[]> removeLayer = new LinkedList<Node[]>(Arrays.asList(net.hiddenNodes));
                    removeLayer.remove(layerOfNode);
                    newLayer = new Node[removeLayer.size()][];
                    
                    for (int i = 0; i<newLayer.length; i++){
                        Node[] layer = removeLayer.get(i);
                        newLayer[i] = new Node[layer.length];
                        for (int x = 0; x<newLayer[i].length; x++){
                            newLayer[i][x] = layer[x];
                        }                                
                    }
                    
                    for (int i = 0; i<newLayer[layerOfNode - 1].length; i++){
                        newLayer[layerOfNode - 1][i].branch = new Node[newLayer[layerOfNode].length];
                        newLayer[layerOfNode - 1][i].connection = new double[newLayer[layerOfNode].length];
                        for (int x = 0; x<net.outputs.length; x++){
                            newLayer[layerOfNode - 1][i].branch[x] = newLayer[layerOfNode ][x];
                            newLayer[layerOfNode - 1][i].connection[x] = ThreadLocalRandom.current().nextGaussian();
                        }
                    }                             
                }
                net.hiddenNodes = newLayer;
                NeuralNetworkUtilities.redoBranches(net);
                net.createBias();
                double fitness = (double)this.fitnessFunction.fitnessFunction(net);
                Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
                newPop[ind] = newIndividual;
                continue;   
                
            }
            net.hiddenNodes[layerOfNode] = new Node[removeNode.size()];
            for (int x = 0; x<removeNode.size(); x++){
                net.hiddenNodes[layerOfNode][x] = removeNode.get(x);
            }
            
            NeuralNetworkUtilities.redoBranches(net);
            net.createBias();
            double fitness = (double)this.fitnessFunction.fitnessFunction(net);
            Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
            newPop[ind] = newIndividual;
        }
        return newPop;
    }
    
}
