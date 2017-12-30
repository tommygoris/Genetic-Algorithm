/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.evolveann;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.problem.ProblemInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tommy
 */
public class NeuralNetworkFitnessFunction implements ProblemInterface {
    private final double[] actual;
    public NeuralNetworkFitnessFunction(double[] actual){
        this.actual = actual;
    }
    
    private final int bias = 1;
    @Override
    public Object fitnessFunction(Object value) {
        NeuralNetwork network = (NeuralNetwork)value;
        double fitness = 0;
        this.propagate(network);
        for (int i = 0; i < actual.length; i++){
            //System.out.println("The output Val is " + network.outputs[i].val);
            fitness = network.outputs[i].val - actual[i];
        }
        return fitness;
    }    
    
    public NeuralNetwork propagate(NeuralNetwork net) {
        if (net.hiddenNodes == null || net.hiddenNodes.length == 0 || (net.hiddenNodes.length == 1 && net.hiddenNodes[0].length == 0)){
             propagateOneStep(net.inputs, net.outputs, net.bias[0]);
             net.outputs = tanh(net.outputs);
             return net;
        }
        boolean one = net.inputs == null;
        boolean two = net.hiddenNodes[0] == null;
        boolean three = net.bias[0] == null;
                
        propagateOneStep(net.inputs, net.hiddenNodes[0], net.bias[0]);
        net.hiddenNodes[0] = tanh(net.hiddenNodes[0]);
        for (int i = 0; i<net.hiddenNodes.length - 1; i++){
            propagateOneStep(net.hiddenNodes[i], net.hiddenNodes[i + 1], net.bias[i + 1]);
            net.hiddenNodes[i + 1] = tanh(net.hiddenNodes[i + 1]);
        }
        propagateOneStep(net.hiddenNodes[net.hiddenNodes.length - 1], net.outputs, net.bias[net.bias.length - 1]);
        net.outputs = tanh(net.outputs);
        return net;

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
    
    private double sig(double val) {
        return 1.0d / (1.0d + Math.exp(-val));
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
