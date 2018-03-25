/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.recurrentnetwork;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkInterface;
import geneticalgorithm.neuralnetwork.utilities.RecurrentNeuralNetworkInterface;
import geneticalgorithm.problem.ProblemInterface;

/**
 *
 * @author TommyGoris
 */
public class EvolveRecurrentNetworkFitnessFunction implements ProblemInterface, RecurrentNeuralNetworkInterface {
    private final Double[] actual;
    private final Double[][] input;
    
    public EvolveRecurrentNetworkFitnessFunction(Double[] actual, Double[][] input){
        this.actual = actual;
        this.input = input;
    }
    
    @Override
    public Object fitnessFunction(Object value) {
        NeuralNetwork network = (NeuralNetwork)value;
        return this.propagate(network);
    }

    @Override
    public double propagate(NeuralNetwork net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRecurrentLayer(NeuralNetwork net, int[] vals) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
