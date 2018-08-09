/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.examples.recurrentnetwork;

import geneticalgorithm.neuralnetwork.NeuralNetwork;
import geneticalgorithm.neuralnetwork.recurrentneuralnetworks.RecurrentNeuralNetwork;
import geneticalgorithm.neuralnetwork.utilities.DrawNeuralNetwork;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkPanel;
import geneticalgorithm.neuralnetwork.utilities.NeuralNetworkUtilities;

/**
 * @author TommyGoris
 */
public class EvolveRecurrentNetworkTest {

    public static void main(String[] args) throws InterruptedException {
        Double[] inputTrainData = new Double[]{1.0, 0.0, 0.0, 1.0};
        NeuralNetwork net = new NeuralNetwork();
        net = NeuralNetworkUtilities.initTree(net, inputTrainData, 1);
        net = NeuralNetworkUtilities.addHiddenNodes(net, new int[]{1, 1, 1, 1});
        RecurrentNeuralNetwork rNet = NeuralNetworkUtilities.InitRecurrentNodes(net, 1);

        DrawNeuralNetwork draw = new DrawNeuralNetwork(rNet);
        NeuralNetworkPanel panel = new NeuralNetworkPanel();
        panel.initPanel();
        panel.refreshPanel(draw);
    }

}
