/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mutation.neuralmutation;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;
import geneticalgorithm.examples.MLPNeuralNetwork.wordguess.NeuralNetworkFitnessFunction;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;
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
 * @author TommyGoris
 */
public class DeleteNode implements MutationInterface{
 private final ProblemInterface fitnessFunction;
    private final double mutationRate;
    
    public DeleteNode(ProblemInterface fitnessFunction, double mutationRate){
        this.fitnessFunction = fitnessFunction;
        this.mutationRate = mutationRate;
    }    
    
     public Individual[] mutate(Population pop){
         Individual[] newPop = new Individual[pop.population.length];
         
         for (int ind = 0; ind<pop.population.length; ind++){
             NeuralNetwork net = (NeuralNetwork)pop.population[ind].individual;
             
             // No hidden nodes to delete. Return the net.
            if (net.hiddenNodes == null || net.hiddenNodes.length == 0 || this.mutationRate < ThreadLocalRandom.current().nextDouble()){
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
                ArrayList<ArrayList<Node>> removeLayer = NeuralNetworkUtilities.toList(net.hiddenNodes);
                removeLayer.remove(layerOfNode);
                
                if (net.hiddenNodes.length == 1){
                    net.hiddenNodes = null;
                    NeuralNetworkUtilities.reconnectLayer(net.inputs, net.outputs);
                }  
                else if (layerOfNode == 0){       
                    NeuralNetworkUtilities.reconnectLayer(net.inputs,  removeLayer.get(0).stream().toArray(Node[]::new));
                }
                else if (layerOfNode == net.hiddenNodes.length - 1){
                    NeuralNetworkUtilities.reconnectLayer(removeLayer.get(layerOfNode - 1).stream().toArray(Node[]::new),  net.outputs);
                }
                else{
                    NeuralNetworkUtilities.reconnectLayer(removeLayer.get(layerOfNode - 1).stream().toArray(Node[]::new),  removeLayer.get(layerOfNode).stream().toArray(Node[]::new));
                }
                net.hiddenNodes = removeLayer.stream()
                .map(l -> l.stream().toArray(Node[]::new))
                .toArray(Node[][]::new);
            }
            else{
                net.hiddenNodes[layerOfNode] = removeNode.toArray(new Node[0]);
                
                if (layerOfNode == 0){
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(net.inputs,  net.hiddenNodes[layerOfNode]);
                }
                else {
                    NeuralNetworkUtilities.reconnentLayerSaveWeights(net.hiddenNodes[layerOfNode - 1],  net.hiddenNodes[layerOfNode]);
                }
         }
            net.createBias();
            double fitness = (double)this.fitnessFunction.fitnessFunction(net);
            Individual<NeuralNetwork> newIndividual = new Individual<>(net, fitness);
            newPop[ind] = newIndividual;     
        }  
          return newPop;
     }
}