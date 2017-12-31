/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
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
import java.util.stream.Collectors;

/**
 *
 * @author TommyGoris
 */
public class AddNode implements MutationInterface {
    private final ProblemInterface fitnessFunction;
    private final double mutationRate;
    public AddNode(ProblemInterface<NeuralNetwork> fitnessFunction, double mutationRate){
        this.fitnessFunction = fitnessFunction;
        this.mutationRate = mutationRate;
    }

    @Override
    public Individual[] mutate(Population pop) {
        Individual[] newPop = new Individual[pop.population.length];
        
        for (int ind = 0; ind < pop.population.length; ind++ ){
           
            NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
            
            if (this.mutationRate > ThreadLocalRandom.current().nextDouble()){
                newPop[ind] = pop.population[ind];
                continue;
            }
            
            boolean newLayer = (ThreadLocalRandom.current().nextDouble() > 0.5 || (net.hiddenNodes == null || net.hiddenNodes.length == 0));
            ArrayList<ArrayList<Node>> newHiddenNode = null;
            if (newLayer){
                if (net.hiddenNodes == null || net.hiddenNodes.length == 0){
                    Node[][] newHiddenNodes = new Node[1][1];
                    newHiddenNodes[0][0] = new Node(ThreadLocalRandom.current().nextGaussian());
                            
                    net.hiddenNodes = newHiddenNodes;
                    NeuralNetworkUtilities.redoBranches(net);
                    net.createBias();
                    double fitness = (double)this.fitnessFunction.fitnessFunction(net);
                    Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
                    newPop[ind] = newIndividual;
                    continue;
                }
                newHiddenNode = NeuralNetworkUtilities.toList(net.hiddenNodes);
                int layerOfNode = ThreadLocalRandom.current().nextInt(net.hiddenNodes.length + 1);                       
                ArrayList<Node> newLayerNode = new ArrayList<>();               
                newLayerNode.add(new Node(ThreadLocalRandom.current().nextGaussian()));
                newHiddenNode.add(layerOfNode, newLayerNode); 
                
                if (layerOfNode == 0){
                    NeuralNetworkUtilities.reconnectLayer(net.inputs, newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new));
                    NeuralNetworkUtilities.reconnectLayer(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode + 1).stream().toArray(Node[]::new));
                }
                
                else if (layerOfNode == net.hiddenNodes.length){
                    NeuralNetworkUtilities.reconnectLayer(newHiddenNode.get(layerOfNode - 1).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new));
                    NeuralNetworkUtilities.reconnectLayer(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), net.outputs);
                }
                
                else{
                    NeuralNetworkUtilities.reconnectLayer(newHiddenNode.get(layerOfNode - 1).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new));
                    NeuralNetworkUtilities.reconnectLayer(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode + 1).stream().toArray(Node[]::new));
                }
            }
            else {
                newHiddenNode = NeuralNetworkUtilities.toList(net.hiddenNodes);
                int layerOfNode = ThreadLocalRandom.current().nextInt(net.hiddenNodes.length);
                newHiddenNode.get(layerOfNode).add(new Node(ThreadLocalRandom.current().nextGaussian()));
                
                if (layerOfNode == 0){
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(net.inputs, newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new));
                    if (net.hiddenNodes.length == 1){
                        NeuralNetworkUtilities.reconnentLayerSaveWeights(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), net.outputs);
                    }
                    else{
                        NeuralNetworkUtilities.reconnentLayerSaveWeights(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode + 1).stream().toArray(Node[]::new));
                    } 
                }
                
                else if (layerOfNode == net.hiddenNodes.length - 1){
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(newHiddenNode.get(layerOfNode - 1).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new));
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), net.outputs);
                }
                
                else{
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(newHiddenNode.get(layerOfNode - 1).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new));
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(newHiddenNode.get(layerOfNode).stream().toArray(Node[]::new), newHiddenNode.get(layerOfNode + 1).stream().toArray(Node[]::new));
                }                
            }
            net.hiddenNodes = newHiddenNode.stream()
            .map(l -> l.stream().toArray(Node[]::new))
            .toArray(Node[][]::new);
            
            net.createBias();
            double fitness = (double)this.fitnessFunction.fitnessFunction(net);
            Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
            newPop[ind] = newIndividual;
        }
        return newPop;
    }

}
