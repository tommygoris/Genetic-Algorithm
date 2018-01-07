/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.neuralnetwork.recurrentneuralnetworks;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import java.util.ArrayList;

/**
 *
 * @author TommyGoris
 */
public class RecurrentNeuralNetwork extends NeuralNetwork {
    public Node[][] recurrentLayer;
    
    public RecurrentNeuralNetwork(Node[][] recurrentLayer){
        super();
        this.recurrentLayer = recurrentLayer;
    }
}
