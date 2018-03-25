/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.MLPNeuralNetwork.sinwave;

import geneticalgorithm.examples.MLPNeuralNetwork.wordguess.NeuralNetworkFitnessFunction;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkInterface;
import geneticalgorithm.problem.ProblemInterface;
import java.util.ArrayList;

/**
 *
 * @author TommyGoris
 */
public class SinWaveFitnessFunction implements ProblemInterface, NeuralNetworkInterface {
    
    private final Double[] actual;
    private final Double[][] input;
    
    public SinWaveFitnessFunction(Double[] actual, Double[][] input){
        this.actual = actual;
        this.input = input;
    }
    @Override
    public Object fitnessFunction(Object value) {
        NeuralNetwork network = (NeuralNetwork)value;
        return this.propagate(network);
    }
    
    public double propagate(NeuralNetwork net) {
        double fitness = 0;
        for (int inputs = 0; inputs<this.input.length; inputs++){
            for (int inputsLength = 0; inputsLength<this.input[inputs].length; inputsLength++){
                net.inputs[inputsLength].val = this.input[inputs][inputsLength];
            }
            if (net.hiddenNodes == null || net.hiddenNodes.length == 0 || (net.hiddenNodes.length == 1 && net.hiddenNodes[0].length == 0)){
                 propagateOneStep(net.inputs, net.outputs, net.bias[0]);
                 net.outputs = tanh(net.outputs);
                 
            if (actual[inputs] < 0 && net.outputs[0].val < 0){
                if ((Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) < 0.1 && (Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) > -0.1){
                    fitness += 1;
                }
            }
            if (actual[inputs] > 0 && net.outputs[0].val > 0){
                if ((Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) < 0.1 && (Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) > -0.1){
                    fitness += 1;
                }
            }
                 continue;
            }
            propagateOneStep(net.inputs, net.hiddenNodes[0], net.bias[0]);
            net.hiddenNodes[0] = tanh(net.hiddenNodes[0]);
            for (int i = 0; i<net.hiddenNodes.length - 1; i++){
                propagateOneStep(net.hiddenNodes[i], net.hiddenNodes[i + 1], net.bias[i + 1]);
                net.hiddenNodes[i + 1] = tanh(net.hiddenNodes[i + 1]);
            }
            propagateOneStep(net.hiddenNodes[net.hiddenNodes.length - 1], net.outputs, net.bias[net.bias.length - 1]);
            net.outputs = tanh(net.outputs);
            /// only one output node.
            if (actual[inputs] < 0 && net.outputs[0].val < 0){
                if ((Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) < 0.1 && (Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) > -0.1){
                    fitness += 1;
                }
            }
            if (actual[inputs] > 0 && net.outputs[0].val > 0){
                if ((Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) < 0.1 && (Math.abs(actual[inputs]) - Math.abs(net.outputs[0].val)) > -0.1){
                    fitness += 1;
                }
            }
        }
        return fitness;
    }

    private void propagateOneStep(Node[] fromLayer, Node[] toLayer, Node bias) {
        double[] newVals = new double[toLayer.length];
        for (int from = 0; from < fromLayer.length; from++) {
            for (int to = 0; to < toLayer.length; to++) {
                double fromVar = fromLayer[from].connection[to];
                double fromVal = fromLayer[from].val;       
                newVals[to] += fromVal * fromVar + bias.val*bias.connection[to];
            }
        }
        
        for (int i = 0; i<toLayer.length; i++){
            if (toLayer[i]==null){
                toLayer[i] = new Node(newVals[i]);
            }
            else{
               toLayer[i].val = newVals[i]; 
            }
        }
    }
    
    private Node[] sig(Node[] array) {
        for (int i = 0; i<array.length; i++){
            array[i].val = 1.0d / (1.0d + Math.exp(-array[i].val));
        }
        return array;
    }
    
    private Node[] tanh(Node[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i].val = Math.tanh(array[i].val);
        }
        return array;
    }
}
