/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.neuralnetwork.utilities;

import geneticalgorithm.neuralnetwork.NeuralNetwork;

/**
 *
 * @author TommyGoris
 */
public interface RecurrentNeuralNetworkInterface extends NeuralNetworkInterface{
    
    public void setRecurrentLayer(NeuralNetwork net, int[] vals);   
}
