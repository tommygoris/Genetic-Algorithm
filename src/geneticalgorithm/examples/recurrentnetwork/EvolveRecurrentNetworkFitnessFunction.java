/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.recurrentnetwork;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.Node;
import geneticalgorithm.neuralnetwork.recurrentneuralnetworks.RecurrentNeuralNetwork;
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
        RecurrentNeuralNetwork network = (RecurrentNeuralNetwork)value;
        return this.propagate(network);
    }

    @Override
    public double propagate(NeuralNetwork net) {
        RecurrentNeuralNetwork rNet = (RecurrentNeuralNetwork)net;

        for (int inputs = 0; inputs<this.input.length; inputs++) {
            for (int inputsLength = 0; inputsLength < this.input[inputs].length; inputsLength++) {
                rNet.inputs[inputsLength].val = this.input[inputs][inputsLength];
            }

            //propagateOneStep(rNet.inputs, rNet.hiddenNodes[0], rNet.bias[0], rNet.recurrentLayer[0]);
            rNet.hiddenNodes[0] = tanh(rNet.hiddenNodes[0]);
            this.setRecurrentLayer(rNet, rNet.hiddenNodes[0], 0);
            for (int i = 0; i<rNet.hiddenNodes.length - 1; i++){
                //propagateOneStep(rNet.hiddenNodes[i], rNet.hiddenNodes[i + 1], rNet.bias[i + 1], rNet.recurrentLayer[i]);
                rNet.hiddenNodes[i + 1] = tanh(rNet.hiddenNodes[i + 1]);
                this.setRecurrentLayer(rNet, rNet.hiddenNodes[i + 1], i + 1);
            }
            //propagateOneStep(rNet.hiddenNodes[rNet.hiddenNodes.length - 1], rNet.outputs, rNet.bias[rNet.bias.length - 1], rNet.recurrentLayer[rNet.recurrentLayer.length - 1]);
            rNet.outputs = tanh(rNet.outputs);

        }
        return 0.0;
    }

    private void propagateOneStep(Node[] fromLayer, Node[] toLayer, Node bias, Node[] recurrentLayer) {
        double[] newVals = new double[toLayer.length];
        for (int from = 0; from < fromLayer.length; from++) {
            for (int to = 0; to < toLayer.length; to++) {
                double fromVar = fromLayer[from].connection[to];
                double fromVal = fromLayer[from].val;
                double rNetVal = recurrentLayer[from].connection[to]*recurrentLayer[from].val;
                newVals[to] += fromVal * fromVar * rNetVal + bias.val*bias.connection[to];
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

    @Override
    public void setRecurrentLayer(RecurrentNeuralNetwork net, Node[] vals, int layer) {
        for (int i = 0; i<net.recurrentLayer[layer].length; i++){
            //net.recurrentLayer[layer][i].val = vals[i].val;
        }
    }
    private Node[] tanh(Node[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i].val = Math.tanh(array[i].val);
        }
        return array;
    }

    
}
