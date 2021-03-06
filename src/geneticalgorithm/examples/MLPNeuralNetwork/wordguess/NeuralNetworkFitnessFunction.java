/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.MLPNeuralNetwork.wordguess;

import static geneticalgorithm.examples.MLPNeuralNetwork.wordguess.EvolveNeuralNetworkTest.check;
import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemInterface;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class NeuralNetworkFitnessFunction implements ProblemInterface {
    private final Double[] actual;
    private final Double[][] input;
    public NeuralNetworkFitnessFunction(Double[] actual, Double[][] input){
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
                 net.outputs = sig(net.outputs);
                 
                if (actual[inputs] > 0.5 && net.outputs[0].val > 0.5){
                fitness += 1;
                }
                if (actual[inputs] < 0.5 && net.outputs[0].val < 0.5) {
                fitness += 1;
                }
                 continue;
            }
            propagateOneStep(net.inputs, net.hiddenNodes[0], net.bias[0]);
            net.hiddenNodes[0] = sig(net.hiddenNodes[0]);
            for (int i = 0; i<net.hiddenNodes.length - 1; i++){
                propagateOneStep(net.hiddenNodes[i], net.hiddenNodes[i + 1], net.bias[i + 1]);
                net.hiddenNodes[i + 1] = sig(net.hiddenNodes[i + 1]);
            }
            propagateOneStep(net.hiddenNodes[net.hiddenNodes.length - 1], net.outputs, net.bias[net.bias.length - 1]);
            net.outputs = sig(net.outputs);
            //System.out.println(actual[inputs] + "  " + net.outputs[0].val);
            /// only one output node.
            if (actual[inputs] > 0.5 && net.outputs[0].val > 0.5){
                fitness += 1;
            }
            if ((actual[inputs] < 0.5 && net.outputs[0].val < 0.5)) {
                fitness += 1;
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

    private double dtanh(double num) {
        //return 1;
        return (1 - (num * num));
        // for the sigmoid
        //final double val = sig(num);
        //return (val*(1-val));
    }    
}
