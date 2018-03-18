/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann.recurrentnetworks;

import geneticalgorithm.examples.evolveann.DrawNeuralNetwork;
import geneticalgorithm.examples.evolveann.NeuralNetworkPanel;
import geneticalgorithm.examples.evolveann.NeuralNetworkUtilities;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.neuralnetwork.recurrentneuralnetworks.RecurrentNeuralNetwork;

/**
 *
 * @author TommyGoris
 */
public class EvolveRecurrentNetworkTest {
    
    public static void main(String[] args) throws InterruptedException{
        Double[] inputTrainData = new Double[]{1.0,0.0,0.0,1.0};
        NeuralNetwork net = new NeuralNetwork();
        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
        net = NeuralNetworkUtilities.addHiddenNodes(net, new int[]{1,1,1,1});
        RecurrentNeuralNetwork rNet = NeuralNetworkUtilities.InitRecurrentNodes(net);      
    }
}
